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
package sc.fiji.links.example;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;
import sc.fiji.links.AbstractFijiLinkHandler;
import sc.fiji.links.FijiLinkHandler;
import sc.fiji.links.Links;

import java.net.URI;

@Plugin(type = FijiLinkHandler.class)
public class OpenBDVLink extends AbstractFijiLinkHandler {

    @Parameter
    private UIService uiService;

    @Override
    public void handle(final URI uri) {
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);
        // START HERE: implement actual behavior.
        uiService.showDialog("OpenBDV: path=" + Links.subPath(uri) + ", query=" + Links.query(uri));
        throw new RuntimeException("OpenBDV: path=" + Links.subPath(uri) + ", query=" + Links.query(uri));
    }

    @Override
    public boolean supports(final URI uri) {
        return super.supports(uri) && "OpenBDV".equals(Links.operation(uri));
    }
}
