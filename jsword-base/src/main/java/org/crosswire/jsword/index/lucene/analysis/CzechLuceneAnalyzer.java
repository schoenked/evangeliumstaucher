/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 or later
 * as published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * <p>
 * The License is available on the internet at:
 * http://www.gnu.org/copyleft/lgpl.html
 * or by writing to:
 * Free Software Foundation, Inc.
 * 59 Temple Place - Suite 330
 * Boston, MA 02111-1307, USA
 * <p>
 * © CrossWire Bible Society, 2007 - 2016
 */
package org.crosswire.jsword.index.lucene.analysis;

import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cz.CzechAnalyzer;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;

/**
 * An Analyzer whose {@link TokenStream} is built from a
 * {@link LowerCaseTokenizer} filtered with {@link StopFilter} (optional).
 * Stemming not implemented yet
 *
 * @see gnu.lgpl.License The GNU Lesser General Public License for details.
 * @author Sijo Cherian
 * @author DM SMITH
 */
public class CzechLuceneAnalyzer extends AbstractBookAnalyzer {
    private final Version matchVersion = Version.LUCENE_29;

    public CzechLuceneAnalyzer() {
        stopSet = CzechAnalyzer.getDefaultStopSet();
    }

    /* (non-Javadoc)
     * @see org.apache.lucene.analysis.Analyzer#tokenStream(java.lang.String, java.io.Reader)
     */
    @Override
    public final TokenStream tokenStream(String fieldName, Reader reader) {
        TokenStream result = new LowerCaseTokenizer(reader);

        if (doStopWords && stopSet != null) {
            result = new StopFilter(false, result, stopSet);
        }

        return result;
    }

    /* (non-Javadoc)
     * @see org.apache.lucene.analysis.Analyzer#reusableTokenStream(java.lang.String, java.io.Reader)
     */
    @Override
    public TokenStream reusableTokenStream(String fieldName, Reader reader) throws IOException {
        SavedStreams streams = (SavedStreams) getPreviousTokenStream();
        if (streams == null) {
            streams = new SavedStreams(new LowerCaseTokenizer(reader));
            if (doStopWords && stopSet != null) {
                streams.setResult(new StopFilter(StopFilter.getEnablePositionIncrementsVersionDefault(matchVersion), streams.getResult(), stopSet));
            }

            setPreviousTokenStream(streams);
        } else {
            streams.getSource().reset(reader);
        }
        return streams.getResult();
    }
}
