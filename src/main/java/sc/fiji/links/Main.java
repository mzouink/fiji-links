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
        String link = "fiji:/OpenBDV?%7B%22sources%22:[%7B%22data%22:%7B%22link%22:%22s3://janelia-cosem-datasets/jrc_macrophage-2/jrc_macrophage-2.n5%22,%22dataset%22:%22em/fibsem-uint16/s4%22%7D,%22viewer_state%22:%7B%7D%7D,%7B%22data%22:%7B%22link%22:%22s3://janelia-cosem-datasets/jrc_macrophage-2/jrc_macrophage-2.n5%22,%22dataset%22:%22labels/ecs_seg/s4%22%7D,%22viewer_state%22:%7B%22color%22:-4849920%7D%7D,%7B%22data%22:%7B%22link%22:%22s3://janelia-cosem-datasets/jrc_macrophage-2/jrc_macrophage-2.n5%22,%22dataset%22:%22labels/endo_seg/s4%22%7D,%22viewer_state%22:%7B%22color%22:-130817%7D%7D]%7D";
        final Context ctx = new Context();
        final LinkService fijiLinkService = ctx.service(LinkService.class);

        final URI showBDV = new URI(link);
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
