import java.io.IOException;
import java.net.Socket;

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

        int c;
        while ((c = socket.getInputStream().read()) != -1){
            System.out.print((char)c);
            response += (char)c;
        }
    }

    public int getStatusCode() {
        String firstLineOfResponse = response.split("\r\n")[0];
        String responseCode = firstLineOfResponse.split(" ")[1];

        return Integer.parseInt(responseCode);
    }

    public static void main(String[] args) throws IOException {
    }
}
