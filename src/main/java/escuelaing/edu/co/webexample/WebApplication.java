package escuelaing.edu.co.webexample;

import static escuelaing.edu.co.httpserver.HttpServer.startServer;
import static escuelaing.edu.co.httpserver.HttpServer.get;
import static escuelaing.edu.co.httpserver.HttpServer.staticfiles;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author juan.medina-r
 */
public class WebApplication {
    public static void main(String[] args) throws IOException, URISyntaxException {
        staticfiles("/public");
        get("/hello", (req, resp) -> "Hello " + req.getValues("name"));
        //get("/hello", (req, resp) -> helloService());
        get("/pi", (req, resp) -> {
            return String.valueOf(Math.PI); 
        });
        
        startServer(args);
    }
}
