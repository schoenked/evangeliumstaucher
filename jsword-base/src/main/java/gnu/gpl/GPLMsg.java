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
package gnu.gpl;

import org.crosswire.common.util.MsgBase;

/**
 * Compile safe Msg resource settings.
 *
 * @see gnu.gpl.License for license details.
 * @author DM Smith
 */
final class GPLMsg extends MsgBase {
    private static final MsgBase msg = new GPLMsg();

    /**
     * Get the internationalized text, but return key if key is unknown.
     * The text requires one or more parameters to be passed.
     *
     * @param key the formatted key to internationalize
     * @param params the parameters to format
     * @return the formatted, internationalized text
     */
    public static String gettext(String key, Object... params) {
        return msg.lookup(key, params);
    }
}
