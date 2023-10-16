package sc.fiji.links.example;

import org.scijava.links.AbstractLinkHandler;
import org.scijava.links.LinkHandler;
import org.scijava.links.Links;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

import java.net.URI;
import java.util.Map;

@Plugin(type = LinkHandler.class)
public class ExamplePlugin extends AbstractLinkHandler {
    public static final String PLUGIN_NAME = "tuto";

    @Parameter
    private UIService uiService;

    @Override
    public void handle(URI uri) {
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);
        String st = "Hello ";
        Map<String, String> query = Links.query(uri);
        if(query.containsKey("msg"))
            st+= query.get("msg");
        uiService.showDialog(st);
    }

    @Override
    public boolean supports(URI uri) {
        return super.supports(uri) && PLUGIN_NAME.equals(Links.operation(uri));
    }
}
