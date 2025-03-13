package sc.fiji.links.example;

import org.scijava.links.AbstractLinkHandler;
import org.scijava.links.LinkHandler;
import org.scijava.links.Links;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.script.ScriptService;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Plugin(type = LinkHandler.class)
public class MacroScripting extends AbstractLinkHandler {
    public static final String PLUGIN_NAME = "macro";

    @Parameter
    private ScriptService scriptService;

    @Override
    public void handle(URI uri) {
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);
//        if (!uiService.isVisible()) uiService.showUI();

        System.out.println(uri.getQuery());
        System.out.println(URLDecoder.decode(uri.getQuery(), java.nio.charset.StandardCharsets.UTF_8));
        Map<String, String> query = Links.query(uri);
        String steps = query.get("steps");
        steps = URLDecoder.decode(steps, java.nio.charset.StandardCharsets.UTF_8);
        System.out.println(steps
        );
        System.out.println(steps);
        // execute macro and wait until it completes.
        scriptService.run(".ijm", steps, true);

    }

    @Override
    public boolean supports(URI uri) {
        return super.supports(uri) && PLUGIN_NAME.equals(Links.operation(uri));
    }

    public static void main(String[] args) {



        String[] steps = new String[]{
                "path = File.openDialog(\"Choose an Image\");",
                "open(path);",
//                "selectImage(\"imgT.jpg\");",
                "run(\"Split Channels\");",
//                "selectImage(\"imgT.jpg (green)\");",
                "setAutoThreshold(\"Default no-reset\");",
                "run(\"Threshold...\");",
                "setThreshold(0, 51);",
                "setOption(\"BlackBackground\",true);",
        "run(\"Convert to Mask\");"
        };
        String st = String.join("\n", steps);
        System.out.println(st);
        String encoded = URLEncoder.encode(st, java.nio.charset.StandardCharsets.UTF_8);
        System.out.println(encoded);
    }


}
