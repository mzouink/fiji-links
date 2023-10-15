package sc.fiji.links.example;

import org.scijava.io.IOService;
import org.scijava.io.location.BytesLocation;
import org.scijava.links.AbstractLinkHandler;
import org.scijava.links.LinkHandler;
import org.scijava.links.Links;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;

@Plugin(type = LinkHandler.class)
public class ShowImage extends AbstractLinkHandler {

    @Parameter
    IOService ioService;

    @Parameter
    UIService uiService;

    @Override
    public void handle(URI uri) {
        if (!supports(uri)) throw new UnsupportedOperationException("" + uri);

//        if (!uiService.isVisible()) uiService.showUI();
        Map<String, String> query = Links.query(uri);
        try {
            if (query.containsKey("bytes")) {
                uiService.showDialog("Show bytes: " + query.get("bytes"));
                String stImage = query.get("bytes");
                byte[] bytes = Base64.getUrlDecoder().decode(stImage);
                Object dataset = ioService.open(new BytesLocation(bytes));
                uiService.show(dataset);
                return;
            }
            if (query.containsKey("path")) {
                uiService.showDialog("Show path: " + query.get("path"));
                String path = query.get("path");
//                path = URLDecoder.decode(path, "UTF-8");
                Object dataset = ioService.open(path);
                uiService.show(dataset);
                return;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean supports(final URI uri) {
        return super.supports(uri) && "show".equals(Links.operation(uri));
    }

    public static void main(String[] args) throws IOException {
        String imgPath = "/Users/zouinkhim/Desktop/brno_work/link/Hello/src/main/resources/imgT.jpg";

        String str = URLEncoder.encode(imgPath, "UTF-8");
        System.out.println(str);
//        String base64Encoded = Base64.getUrlEncoder().encodeToString(bytes);
//        // Load the image
//        BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
//
//        // Convert the BufferedImage to byte array
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        String base64Encoded = Base64.getUrlEncoder().encodeToString(bytes);
//        System.out.println(base64Encoded);

    }
}
