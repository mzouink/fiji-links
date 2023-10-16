package sc.fiji.links.example.bdv.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.imglib2.type.numeric.ARGBType;
import org.scijava.links.Links;
import sc.fiji.links.UriGenerator;
import sc.fiji.links.example.bdv.ColorStream;
import sc.fiji.links.example.bdv.OpenBDVLink;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateBDVLink {
    public static void main(String... args) throws URISyntaxException, JsonProcessingException {
        String link = "s3://janelia-cosem-datasets/jrc_macrophage-2/jrc_macrophage-2.n5";
        String[] sources = new String[]{"em/fibsem-uint16/s4", "labels/ecs_seg/s4", "labels/endo_seg/s4"};

//        String link = "s3://janelia-cosem-datasets/jrc_dauer-larva/jrc_dauer-larva.n5";
//        String[] sources = new String[]{"em/tem-uint8/s6", "labels/nucleus_seg/s4"};

        URI uri = generateBDVUri(link, sources);
        System.out.println(uri);
    }

    private static URI generateBDVUri(String link, String[] sources) throws JsonProcessingException, URISyntaxException {
        BDVRequestQuery bdvRequestQuery = new BDVRequestQuery();
        bdvRequestQuery.sources = new ArrayList<>();
        Iterator<ARGBType> colorProvider = ColorStream.iterator();
        for (String s : sources) {
            Source source = new Source();
            source.data = new Data();
            source.data.dataset = s;
            source.data.link = link;
            ViewerState viewerState = new ViewerState();
            if (!s.contains("em/")) {
                viewerState.color = colorProvider.next();
                viewerState.range = List.of(0, 100);
            } else {
                viewerState.range = List.of(1000, 40000);
            }
            source.viewerState = viewerState;
            bdvRequestQuery.sources.add(source);
        }

        String serializedData = bdvRequestQuery.serialize();
        return UriGenerator.generateUri(OpenBDVLink.PLUGIN_NAME, null, serializedData);
    }
}
