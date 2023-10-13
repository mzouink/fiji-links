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

import bdv.util.Bdv;
import bdv.util.BdvFunctions;
import bdv.util.BdvOptions;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import org.janelia.saalfeldlab.n5.N5Reader;
import org.janelia.saalfeldlab.n5.imglib2.N5Utils;
import org.janelia.saalfeldlab.n5.universe.N5Factory;
import org.scijava.links.AbstractLinkHandler;
import org.scijava.links.LinkHandler;
import org.scijava.links.Links;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Plugin(type = LinkHandler.class)
public class OpenBDVLink extends AbstractLinkHandler {
    Bdv bdv;

    @Parameter
    private UIService uiService;

    @Override
    public void handle(final URI uri) {
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);
        // START HERE: implement actual behavior.
        System.out.println("URI: " + uri);
//        uiService.showDialog("OpenBDV: path=" + Links.subPath(uri) + ", query=" + Links.query(uri)+ ", task=" + Links.query(uri).get("task"));
        Map<String, String> queries = Links.query(uri);
        String task = queries.get("task");

        switch (task) {
            case "OPEN":
                bdv = open(queries.get("path"), queries.get("dataset"), false);
                break;
            case "ADD":
                bdv = open(queries.get("path"), queries.get("dataset"), true);
                break;
            case "ROTATE":
                rotate(bdv, 10);
                break;
        }

//        throw new RuntimeException("OpenBDV: path=" + Links.subPath(uri) + ", query=" + Links.query(uri));
    }

    private void rotate(Bdv bdv, int i) {
    }


    private Bdv open(String path, String dataset, boolean addToBdv) {


        // Visualize with BigDataViewer
        try {
            N5Reader n5Reader = new N5Factory().openReader(path);
            final UnsignedByteType type = new UnsignedByteType();
            final RandomAccessibleInterval<UnsignedByteType> data;
            data = N5Utils.open(n5Reader, dataset);
            if (addToBdv && bdv != null) {
                return BdvFunctions.show(data, "S3 Dataset", BdvOptions.options().addTo(bdv));
            } else {
                return BdvFunctions.show(data, "S3 Dataset", BdvOptions.options());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean supports(final URI uri) {
        return super.supports(uri) && "OpenBDV".equals(Links.operation(uri));
    }
}
