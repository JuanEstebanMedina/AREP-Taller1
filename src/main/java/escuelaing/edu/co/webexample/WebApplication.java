package escuelaing.edu.co.webexample;

import static escuelaing.edu.co.httpserver.HttpServer.startServer;
import static escuelaing.edu.co.httpserver.HttpServer.get;
import static escuelaing.edu.co.httpserver.HttpServer.staticfiles;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author juan.medina-r
 */
public class WebApplication {

    public static void main(String[] args) throws IOException, URISyntaxException {
        staticfiles("/public");
        get("/hello", (req, resp) -> "Hello " + req.getValue("name"));
        //get("/hello", (req, resp) -> helloService());
        get("/pi", (req, resp) -> {
            return String.valueOf(Math.PI);
        });

        startServer(35000);
    }

    private static String generalService(String contentType) {
        String response = "HTTP/1.1 200 OK\n\r"
                + "content-type: " + contentType + "\n\r"
                + "\n\r";
        return response;
    }

    private static String helloService(URI requesturi) {
        String response = generalService("application/json");

        String query = requesturi.getQuery();
        String name = null;
        if (query.contains("=")) {
            name = query.split("=")[1]; //name=John
        } else {
            name = "Mundo";
        }
        System.out.println("name: " + name);

        response = response + "{\"mensaje\": \"Hola " + name + "\"}";
        return response;
    }
}
