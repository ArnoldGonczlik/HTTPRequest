import java.io.IOException;
import java.net.Socket;

public class HTTPClient {

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
        }
    }

    public static void main(String[] args) throws IOException {
    }

    public int getStatusCode() {
        return 0;
    }
}
