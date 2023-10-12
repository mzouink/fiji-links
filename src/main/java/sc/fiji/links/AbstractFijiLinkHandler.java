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

import org.scijava.Context;
import org.scijava.plugin.AbstractHandlerPlugin;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.PluginInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

public abstract class AbstractFijiLinkHandler extends AbstractHandlerPlugin<URI>
    implements FijiLinkHandler
{
    // NB: No implementation needed.
}
