package sc.fiji.links.linux;

import org.scijava.links.AbstractLinkHandler;
import org.scijava.links.LinkHandler;
import org.scijava.links.Links;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

import java.net.URI;
import java.util.Map;

@Plugin(type = LinkHandler.class)
public class LinuxPlugin extends AbstractLinkHandler {
    public static final String PLUGIN_NAME = "test";

    @Parameter
    private UIService uiService;

    @Override
    public void handle(URI uri) {
        System.out.println("LinuxPlugin : got handle URI " + uri);
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);
        String st = "Hello ";
        Map<String, String> query = Links.query(uri);
        if(query.containsKey("msg"))
            st+= query.get("msg");
        uiService.showDialog(st);
    }

    @Override
    public boolean supports(URI uri) {
        System.out.println("LinuxPlugin : check supports URI " + uri);

        return super.supports(uri) && PLUGIN_NAME.equals(Links.operation(uri));
    }
}
