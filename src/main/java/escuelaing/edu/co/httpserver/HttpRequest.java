package escuelaing.edu.co.httpserver;

import java.net.URI;

/**
 *
 * @author juan.medina-r
 */
public class HttpRequest {
    
    URI requri = null;
    
    HttpRequest(URI requesturi){
        requri = requesturi;
    }
    
    public String getValues(String paramName){
        String paramValue = requri.getQuery().split("&")[0].split("=")[1]; //name=jhon&color=blue
        return paramValue;
    }
}
