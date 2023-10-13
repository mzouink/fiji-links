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

import net.imagej.ImageJService;
import org.scijava.log.Logger;
import org.scijava.plugin.HandlerService;

import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

/**
 * Service interface for handling {@code fiji:} URIs.
 *
 * @author Curtis Rueden
 * @see FijiLinkHandler
 */
public interface FijiLinkService extends HandlerService<URI, FijiLinkHandler>, ImageJService {

    default void handle(final URI uri) {
        System.out.println("Open URI: " + uri); //TEMP
        // Find the highest-priority link handler plugin which matches, if any.
        final Optional<FijiLinkHandler> match = getInstances().stream() //
            .filter(handler -> handler.supports(uri)) //
            .findFirst();
        if (match.isEmpty()) {
            // No appropriate link handler plugin was found.
            final Logger log = log();
            if (log != null) log.debug("No handler for URI: " + uri);
            return; // no handler for this URI
        }
        // Handle the URI using the matching link handler.
        match.get().handle(uri);
    }

    // -- PTService methods --

    @Override
    default Class<FijiLinkHandler> getPluginType() {
        return FijiLinkHandler.class;
    }

    // -- Service methods --

    @Override
    default void initialize() {
        HandlerService.super.initialize();
    }

    // -- Typed methods --

    @Override
    default Class<URI> getType() {
        return URI.class;
    }
}
