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
package sc.fiji.links.example.bdv;

import bdv.util.Bdv;
import bdv.util.BdvFunctions;
import bdv.util.BdvOptions;
import bdv.util.BdvStackSource;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.array.ArrayImgs;
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
import sc.fiji.links.example.bdv.query.BDVRequestQuery;
import sc.fiji.links.example.bdv.query.Source;
import sc.fiji.links.example.bdv.query.ViewerState;

import javax.swing.*;
import java.net.URI;

@Plugin(type = LinkHandler.class)
public class OpenBDVLink extends AbstractLinkHandler {

    public static final String PLUGIN_NAME = "OpenBDV";
    @Parameter
    private UIService uiService;

    @Override
    public void handle(final URI uri) {
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);
        String query = uri.getQuery();
        try {
            BDVRequestQuery bdvQuery = BDVRequestQuery.fromQuery(query, BDVRequestQuery.class);
            // For now, I am using N5Factory URLs manager. It does support locals, S3, etc. but I am not if it is the best option.
            // TODO use N5Viewer instead to support multi resolution
            System.out.println(bdvQuery);
//            if(bdv == null) bdv = openEmptyBDV();
            Bdv bdv = null;
            for (Source source : bdvQuery.sources) {
                System.out.println(source);
                N5Reader n5Reader = new N5Factory().openReader(source.data.link);
                final RandomAccessibleInterval<UnsignedByteType> data = N5Utils.open(n5Reader, source.data.dataset);
                BdvStackSource<?> bdvSource = null;
                if(bdv == null){
                    bdvSource = BdvFunctions.show(data, source.data.dataset, BdvOptions.options());
                    bdv = bdvSource.getBdvHandle();
                }else
                    bdvSource = BdvFunctions.show(data, source.data.dataset, BdvOptions.options().addTo(bdv));
                if (source.viewerState == null) continue;
                ViewerState state = source.viewerState;
                if (state.color != null) bdvSource.setColor(state.color);
                if (state.range != null) bdvSource.setDisplayRange(state.range.get(0), state.range.get(1));
            }
            if (bdvQuery.viewerTransformation != null)
                bdv.getBdvHandle().getViewerPanel().state().setViewerTransform(bdvQuery.viewerTransformation);
//            refresh a view
            bdv.getBdvHandle().getViewerPanel().requestRepaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
//            throw new RuntimeException(e);
        }
    }

    private Bdv openEmptyBDV() {
        final UnsignedByteType t = new UnsignedByteType();
        final BdvStackSource<?> bdv = BdvFunctions.show(ArrayImgs.unsignedBytes(1, 1, 1), "dummy", BdvOptions.options());
//        bdv.removeFromBdv();
        return bdv;
    }

    @Override
    public boolean supports(final URI uri) {
        return super.supports(uri) && PLUGIN_NAME.equals(Links.operation(uri));
    }


}
