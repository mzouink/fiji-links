package sc.fiji.links.example;

import org.scijava.io.IOService;
import org.scijava.io.location.BytesLocation;
import org.scijava.links.AbstractLinkHandler;
import org.scijava.links.LinkHandler;
import org.scijava.links.Links;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;

@Plugin(type = LinkHandler.class)
public class ShowImage extends AbstractLinkHandler {

    public static final String PLUGIN_NAME = "show";
    @Parameter
    IOService ioService;

    @Parameter
    UIService uiService;

    @Override
    public void handle(URI uri) {
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);
//        if (!uiService.isVisible()) uiService.showUI();
        String task = Links.subPath(uri);
        System.out.println(task);
        Map<String, String> query = Links.query(uri);
        switch (task) {
            case "image":
                showImage(query);
                break;
            case "bytes":
                showBytes(query);
                break;
            default:
                throw new UnsupportedOperationException("Unknown task: " + task);
        }
    }

    private void showBytes(Map<String, String> query) {
            try {
                String stImage = query.get("data");
                byte[] bytes = Base64.getUrlDecoder().decode(stImage);
                Object dataset = ioService.open(new BytesLocation(bytes));
                uiService.show(dataset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private void showImage( Map<String, String> query) {
    try{
        String path = query.get("path");
        Object dataset = ioService.open(path);
        uiService.show(dataset);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }

    @Override
    public boolean supports(final URI uri) {
        return super.supports(uri) && PLUGIN_NAME.equals(Links.operation(uri));
    }
}
