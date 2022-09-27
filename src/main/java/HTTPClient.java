import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HTTPClient {
    String allHeaders;

    Socket socket;

    public HTTPClient(String hostname, int port, String path) throws IOException {
        socket = new Socket(hostname, port);
        String request =
                        "GET " + path + " HTTP/1.1\r\n" +
                        "Host: " + hostname + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n";

        socket.getOutputStream().write(request.getBytes());

        //Parses the data to only get the header parts
        allHeaders = getResponse(socket);
    }

    //Read until there is \r\n\r\n in string and return properly UTF formatted string of headers
    public String getResponse(Socket socket) throws IOException {
        String newlineCheck = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;

        while (!(newlineCheck.contains("\r\n\r\n"))) {
            c = socket.getInputStream().read();
            newlineCheck += (char)(c);
            baos.write(c);
        }

        return baos.toString(StandardCharsets.UTF_8);
    }

    private static HashMap<String, String> getOnlyHeaders(String text) {
        var headerStore = new HashMap<String, String>();

        for (String line: new ArrayList<>(Arrays.asList(text.split("\\r\\n")))
             ) {
            if (line.matches(".+:(\\s)?.+")) {
                var lineSplit = line.split(":(\\s)?", 2);
                headerStore.put(lineSplit[0].toLowerCase(), lineSplit[1].toLowerCase());
            }
        }
        return headerStore;
    }

    public String getBody() throws IOException {
        return getBody(socket);
    }

    public String getSpecificHeader(String specifiedHeader) {
        var headerStore = getOnlyHeaders(allHeaders);

        try {
            return headerStore.get(specifiedHeader.toLowerCase());
        } catch (Exception e){
            return "Not found";
        }
    }

    public int getStatusCode() {
        String firstLineOfResponse = allHeaders.split("\\r\\n")[0];
        String responseCode = firstLineOfResponse.split(" ")[1];

        return Integer.parseInt(responseCode);
    }

    public String getBody(Socket socket) throws IOException {
        int contentLength = Integer.parseInt(getSpecificHeader("Content-Length"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;

        for (int i = 0; i <contentLength; i++) {
            c = socket.getInputStream().read();
            baos.write(c);
        }
        var test = baos.toString(StandardCharsets.UTF_8);
        return baos.toString(StandardCharsets.UTF_8);
    }

}
