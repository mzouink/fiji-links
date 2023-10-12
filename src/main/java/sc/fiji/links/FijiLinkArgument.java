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

import org.scijava.Priority;
import org.scijava.console.AbstractConsoleArgument;
import org.scijava.console.ConsoleArgument;
import org.scijava.log.LogService;
import org.scijava.log.Logger;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.regex.Matcher;

/**
 * A {@link ConsoleArgument} plugin to handle {@code fiji:...} URIs passed to the application via the command line.
 *
 * @author Curtis Rueden
 */
@Plugin(type = ConsoleArgument.class, priority = Priority.VERY_HIGH)
public class FijiLinkArgument extends AbstractConsoleArgument {

    @Parameter(required = false)
    private FijiLinkService fijiLinkService;

    // -- ConsoleArgument methods --

    @Override
    public void handle(final LinkedList<String> args) {
        if (fijiLinkService == null) return; // no service to handle links
        if (args.isEmpty()) return; // no argument to check
        final URI uri = fijiLink(args.getFirst());
        if (uri == null) return; // not a fiji URL
        fijiLinkService.handle(uri);
    }

    // -- Typed methods --

    @Override
    public boolean supports(final LinkedList<String> args) {
        return !args.isEmpty() && fijiLink(args.getFirst()) != null;
    }

    /**
     * Parses a string into a URI of scheme {@code fiji:}, or null if the string
     * does not constitute such a link.
     *
     * @param s The string to parse.
     * @return The URI, or null.
     */
    private URI fijiLink(final String s) {
        try {
            final URI uri = new URI(s);
            return "fiji".equals(uri.getScheme()) ? uri : null;
        }
        catch (final URISyntaxException e) {
            final Logger log = log();
            if (log != null) log.debug(e);
            return null;
        }
    }
}
