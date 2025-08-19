package escuelaing.edu.co.httpserver;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 *
 * @author juan.medina-r
 */
public class HttpServer {

    private static final Path ROOT = Paths.get("public").toAbsolutePath().normalize();

    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
            System.out.println("Ready on: http://localhost:35000");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;

        boolean running = true;
        while (running == true) {

            try {
                System.out.println("Ready to listen ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            OutputStream rawOut = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(rawOut, true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean isFirstLine = true;
            URI requesturi = null;

            while ((inputLine = in.readLine()) != null) {
                if (isFirstLine) {
                    requesturi = new URI(inputLine.split(" ")[1]);
                    System.out.println("Path: " + requesturi.getPath());
                    isFirstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if (requesturi.getPath().startsWith("/api/hello")) {
                outputLine = helloService(requesturi);
                out.println(outputLine);
            } else {
                Path file = mapToPublic(requesturi.getPath());
                System.out.println(file.getFileName());
                System.out.println(file.getParent());
                System.out.println(file.getRoot());
                System.out.println("Files.exists: " + Files.exists(file));
                System.out.println("Files.isDirectory: " + Files.isDirectory(file));
                if (Files.exists(file) && !Files.isDirectory(file)) {
                    String contentType = detectContentType(file);
                    if (isTextContentType(contentType)) {
                        outputLine = fileRequestService(file);
                        out.println(outputLine);
                    } else {
                        serveBinaryFile(rawOut, file, contentType);
                    }
                } else {
                    outputLine = fileNotFoundService();
                    out.println(outputLine);
                }
            }

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static Path mapToPublic(String path) {
        if (path == null || path.equals("/")) {
            path = "/index.html";
        }
        String normalized = path.replace("..", "");
        return ROOT.resolve(normalized.substring(1)).normalize();
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

    private static String fileRequestService(Path file) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet.");
        String response = generalService(detectContentType(file));
        response = response + Files.readString(file, StandardCharsets.UTF_8);
        return response;

    }

    private static void serveBinaryFile(OutputStream rawOut, Path file, String contentType) throws IOException {
        byte[] fileBytes = Files.readAllBytes(file);
        String header = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + fileBytes.length + "\r\n"
                + "\r\n";
        rawOut.write(header.getBytes(StandardCharsets.UTF_8));
        rawOut.write(fileBytes);
        rawOut.flush();
    }

    private static String fileNotFoundService() {
        //throw new UnsupportedOperationException("Not supported yet.");
        String response = "HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html; charset=utf-8\r\n"
                + "\r\n"
                + "<html><body><h1>404 - File Not Found</h1></body></html>";
        return response;
    }

    private static String detectContentType(Path file) {
        String name = file.getFileName().toString().toLowerCase();
        if (name.endsWith(".html")) {
            return "text/html; charset=utf-8";
        }
        if (name.endsWith(".css")) {
            return "text/css; charset=utf-8";
        }
        if (name.endsWith(".js")) {
            return "application/javascript; charset=utf-8";
        }
        if (name.endsWith(".txt")) {
            return "text/plain; charset=utf-8";
        }
        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if (name.endsWith(".png")) {
            return "image/png";
        }
        if (name.endsWith(".gif")) {
            return "image/gif";
        }
        if (name.endsWith(".ico")) {
            return "image/vnd.microsoft.icon";
        }
        return "application/octet-stream";
    }
    
    private static boolean isTextContentType(String contentType) {
        return contentType.startsWith("text/") || contentType.startsWith("application/javascript") || contentType.startsWith("application/json");
    }
}
