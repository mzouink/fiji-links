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
import org.scijava.links.LinkService;

import java.net.URI;

public class Main {
    public static void main(final String... args) throws Exception {
        final Context ctx = new Context();
        final LinkService fijiLinkService = ctx.service(LinkService.class);

        final URI showBDV = new URI("fiji:/showBDV/myDataset?myArg1=foo&myArg2=bar");
        fijiLinkService.handle(showBDV);

        final URI unsupported = new URI("fiji:/noPluginDefined/whoCares");
        fijiLinkService.handle(unsupported);

        // CTR START HERE: convert the above to a unit test class.
        // - down the middle
        // - fiji:
        // - fiji:thing
        // - notfiji:stuff
        // - notfiji:/showBDV
        // - other edge cases
    }
}
