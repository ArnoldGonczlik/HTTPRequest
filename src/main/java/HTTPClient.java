import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class HTTPClient {

    String response;

    public HTTPClient(String hostname, int port, String path) throws IOException {
        Socket socket = new Socket(hostname, port);
        String request =
                        "GET " + path + " HTTP/1.1\r\n" +
                        "Host: " + hostname + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n";
        socket.getOutputStream().write(request.getBytes());
        
        String response = getHeaders(socket);

        var test = socket.getInputStream().read();

        int c;
        while ((c = socket.getInputStream().read()) != -1){
            System.out.print((char)c);
            response += (char)c;
        }
        System.out.println("Done writing");
    }

    private static String getHeaders(Socket socket) throws IOException {
        ByteArrayInputStream buffer = new ByteArrayInputStream(socket.getInputStream().readAllBytes());
        int c;
        String s = buffer.toString(StandardCharsets.UTF_8);
        while ((c = socket.getInputStream().read()) != '\r')) {
            buffer
        }

        return "";
    }

    public int getStatusCode() {
        String firstLineOfResponse = response.split("\r\n")[0];
        String responseCode = firstLineOfResponse.split(" ")[1];

        return Integer.parseInt(responseCode);
    }

    public String getHTML() {
        return response.split("\\r\\n\\r\\n")[1];
    }

    public String getResponseHeader(String header) {
        String allHeaders = response.split("\\r\\n\\r\\n")[0];

        for (String line: allHeaders.split("\\r\\n")
             ) {
            if (line.split(": ?")[0].toLowerCase(Locale.ROOT).equals(header.toLowerCase(Locale.ROOT))) {
                return line.split(": ?")[1];
            }
        }
        throw new RuntimeException("Header not found");
    }
}
