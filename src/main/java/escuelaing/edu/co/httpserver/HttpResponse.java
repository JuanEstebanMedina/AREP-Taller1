package escuelaing.edu.co.httpserver;

import java.net.URI;

/**
 *
 * @author juan.medina-r
 */
public class HttpResponse {
    URI requri = null;
    
    public HttpResponse(URI requri){
        this.requri = requri;
    }
    
    public String getValue(String name){
        return "";
    }
}
