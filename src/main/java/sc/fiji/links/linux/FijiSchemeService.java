package sc.fiji.links.linux;

import org.scijava.InstantiableException;

import java.net.URI;

public class FijiSchemeService {
    final URI uri;

    private FijiSchemeService(URI uri) {
        this.uri = uri;
    }

    public static FijiSchemeService process(URI uri) throws InstantiableException {
        System.out.println("FijiSchemeService : got URI " + uri);
        if (uri == null || !uri.getScheme().equals("fiji")) {
            return null;
        }
        System.out.println("Detected Fiji URI scheme!");
        FijiSchemeService service = new FijiSchemeService(uri);
//        get the right operation
        URLOperation operation = SchemeOperationService.getOperation(uri.getAuthority());
//        Map<String, String> queries = AbstractOperationQueryObject.getQueryMap(uri.getQuery());
        operation.run(uri.getPath(), null);

        return service;
    }
}