package escuelaing.edu.co.httpserver;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author juan.medina-r
 */
public class URLReader {

    public static void main(String[] args) throws Exception {
        String site = "http://www.google.com/";
        // Crea el objeto que representa una URL
        URL siteURL = new URL(site);

        // Crea el objeto que URLConnection
        URLConnection urlConnection = siteURL.openConnection();

        // Obtiene los campos del encabezado y los almacena en una estructura Map
        Map<String, List<String>> headers = urlConnection.getHeaderFields();

        // Obtiene una vista del mapa como conjunto de pares <K,V> para poder navegarlo
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

        // Recorre la lista de campos e imprime los valores
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();

            // Si el nombre es nulo, significa que es la línea de estado
            if (headerName != null) {
                System.out.print(headerName + ":");
            }

            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print(value);
            }
            System.out.println("");
        }

        System.out.println("-------message-body------");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(siteURL.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
