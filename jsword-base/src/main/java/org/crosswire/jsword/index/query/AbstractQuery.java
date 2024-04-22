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
 * © CrossWire Bible Society, 2005 - 2016
 */
package org.crosswire.jsword.index.query;

/**
 * A base query is the smallest unit of search that the index can perform.
 *
 * @see gnu.lgpl.License The GNU Lesser General Public License for details.
 * @author DM Smith
 */
public abstract class AbstractQuery implements Query {

    private final String query;

    /**
     * Construct a query from a string.
     *
     * @param theQuery
     */
    public AbstractQuery(String theQuery) {
        query = theQuery;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }
}
