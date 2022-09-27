import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    private final ServerSocket serverSocket;


    public HTTPServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        start();
    }

    private void start() {
        new Thread(() -> {
            try {
                Socket socket = serverSocket.accept();

                socket.getOutputStream().write(("HTTP/1.1 404 OK\r\n" +
                        // "HTTP/1.1 .... Hello world" is a single string broken up over several lines
                        "Content-length: 12\r\n" +
                        // The HTTP protocol (RFC7230) requires each line to end with
                        //  carriage return (\r) and linefeed (\n)
                        "Content-type: text/plain\r\n" +
                        // An HTTP header. Header name and header value are separated by ":"
                        "\r\n" + // A newline separates the headers from the content body
                        "Hello World! \r\n").getBytes());
                // body should be the same number of bytes as the content-length header
                //socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
