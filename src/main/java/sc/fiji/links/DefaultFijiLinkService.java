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

import org.scijava.plugin.AbstractHandlerService;
import org.scijava.plugin.Plugin;
import org.scijava.service.Service;

import java.net.URI;

/**
 * Default implementation of {@link FijiLinkService}.
 *
 * @author Curtis Rueden
 */
@Plugin(type = Service.class)
public class DefaultFijiLinkService extends AbstractHandlerService<URI, FijiLinkHandler> implements FijiLinkService {
    // NB: No implementation needed.
}
