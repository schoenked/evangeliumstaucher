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
 * © CrossWire Bible Society, 2014 - 2016
 */
package org.crosswire.common.compress;

import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * GZip manages the compression and uncompression of GZip data.
 *
 * @see gnu.lgpl.License The GNU Lesser General Public License for details.
 * @author DM Smith
 */
public class Gzip extends AbstractCompressor {
    /**
     * Create a GZip that is capable of transforming the input.
     *
     * @param input
     *            to compress or uncompress.
     */
    public Gzip(InputStream input) {
        super(input);
    }

    /* (non-Javadoc)
     * @see org.crosswire.common.compress.Compressor#compress()
     */
    public ByteArrayOutputStream compress() throws IOException {
        BufferedInputStream in = new BufferedInputStream(input);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        CompressorOutputStream out = new GzipCompressorOutputStream(bos);
        IOUtils.copy(in, out);
        in.close();
        out.flush();
        out.close();
        return bos;
    }

    /* (non-Javadoc)
     * @see org.crosswire.common.compress.Compressor#uncompress()
     */
    public ByteArrayOutputStream uncompress() throws IOException {
        return uncompress(BUF_SIZE);
    }

    /* (non-Javadoc)
     * @see org.crosswire.common.compress.Compressor#uncompress(int)
     */
    public ByteArrayOutputStream uncompress(int expectedLength) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(expectedLength);
        CompressorInputStream in = new GzipCompressorInputStream(input);
        IOUtils.copy(in, out);
        in.close();
        out.flush();
        out.close();
        return out;
    }

}
