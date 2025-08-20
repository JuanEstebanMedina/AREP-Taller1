package escuelaing.edu.co.httpserver;

import java.net.URI;

/**
 *
 * @author juan.medina-r
 */
public class HttpResponse {

    URI requri = null;

    public HttpResponse(URI requri) {
        this.requri = requri;
    }

    public String getValue(String name) {
        return "";
    }
    
    private static String generalService(String contentType) {
        String response = "HTTP/1.1 200 OK\n\r"
                + "content-type: " + contentType + "\n\r"
                + "\n\r";
        return response;
    }
}
