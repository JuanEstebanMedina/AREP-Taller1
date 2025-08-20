package escuelaing.edu.co.httpserver;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juan.medina-r
 */
public class HttpRequest {

    private final URI requri;
    private Map<String, String> query;

    HttpRequest(URI requesturi) {
        this.requri = requesturi;
    }

    private void parseQuery() {
        query = new HashMap<>();
        String raw = requri.getQuery();
        if (raw == null || raw.isEmpty()) {
            return;
        }
        for (String pair : raw.split("&")) {  //name=jhon&color=blue
            String[] parts = pair.split("=", 2);
            String k = parts[0];
            String v = parts[1];
            query.put(k, v);
        }
    }

    public String getValue(String paramName) {
        String paramValue = query.get(paramName);
        return (paramValue == null || paramValue.isEmpty()) ? null : paramValue;
    }
}
