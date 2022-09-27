import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPClientTest {

    @Test
    void shouldGetSuccessfulResponseCode() throws IOException {
        HTTPClient client = new HTTPClient("httpbin.org", 80, "/html");
        assertEquals(200, client.getStatusCode());
    }

    @Test
    void shouldGetNotFoundResponseCode() throws IOException {
        HTTPClient client = new HTTPClient("httpbin.org", 80, "/thisisdefinitelynotapath");
        assertEquals(404, client.getStatusCode());
    }

    @Test
    void shouldReadResponseHeaders() throws IOException {
        HTTPClient client = new HTTPClient("httpbin.org", 80, "/html");
        assertEquals("text/html; charset=utf-8", client.getSpecificHeader("Content-Type"));
    }
}
