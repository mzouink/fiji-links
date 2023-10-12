/*-
 * #%L
 * Custom scheme handling
 * %%
 * Copyright (C) 2023 Fiji developers.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package sc.fiji.links;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Utility class for working with {@link URI} objects.
 *
 * @author Curtis Rueden
 */
public final class Links {
    private Links() {
        // NB: Prevent instantiation of utility class.
    }

    public static String path(final URI uri) {
        final String path = uri.getPath();
        if (path == null) return null;
        return path.startsWith("/") ? path.substring(1) : path;
    }

    public static String operation(final URI uri) {
        final String path = path(uri);
        if (path == null) return null;
        final int slash = path.indexOf("/");
        return slash < 0 ? path : path.substring(0, slash);
    }

    public static String[] pathFragments(final URI uri) {
        final String path = path(uri);
        if (path == null) return null;
        return path.isEmpty() ? new String[0] : path.split("/");
    }

    public static String subPath(final URI uri) {
        final String path = path(uri);
        if (path == null) return null;
        final int slash = path.indexOf("/");
        return slash < 0 ? "" : path.substring(slash + 1);
    }

    public static Map<String, String> query(final URI uri) {
        final LinkedHashMap<String, String> map = new LinkedHashMap<>();
        final String query = uri.getQuery();
        final String[] tokens = query == null ? new String[0] : query.split("&");
        for (final String token : tokens) {
            final String[] kv = token.split("=", 2);
            final String k = kv[0];
            final String v = kv.length > 1 ? kv[1] : null;
            map.put(k, v);
        }
        return map;
    }
}
