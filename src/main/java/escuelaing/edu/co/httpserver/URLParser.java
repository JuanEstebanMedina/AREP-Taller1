package escuelaing.edu.co.httpserver;

import java.net.*;

/**
 *
 * @author juan.medina-r
 */
public class URLParser {
    public static void main(String[] args) throws MalformedURLException{
        URL myurl = new URL("http://arep.curioso.escuelaing.edu.co:80/respuestasexamenes/parcial1.html?val=3&color=rojo#publicaciones");
        System.out.println("Hello World!");
        System.out.println("Protocolo: " + myurl.getProtocol());
        System.out.println("Authority: " + myurl.getAuthority());
        System.out.println("Host: " + myurl.getHost());
        System.out.println("Port: " + myurl.getPort());
        System.out.println("Path: " + myurl.getPath());
        System.out.println("Query: " + myurl.getQuery());
        System.out.println("File: " + myurl.getFile());
        System.out.println("Ref: " + myurl.getRef());
    }
}
