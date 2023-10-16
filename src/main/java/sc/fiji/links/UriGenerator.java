package sc.fiji.links;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public class UriGenerator {
    public static final String protocol = "fiji";

    public static URI generateUri(String pluginName, String path, String query) throws URISyntaxException {
        return new URI(protocol, pluginName, path, query, null);
    }

    //    TODO this should be part of Links scijava library
    public static String mapToQuery(Map<String, String> params) {
        return params.entrySet().stream()
                .map(entry -> {
                    try {
                        return URLEncoder.encode(entry.getKey(), "UTF-8") + "=" +
                                URLEncoder.encode(entry.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining("&"));
    }
}
