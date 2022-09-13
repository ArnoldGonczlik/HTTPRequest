import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPClientTest {

    @Test
    void shouldGetSuccessfulResponseCode() throws IOException {
        HTTPClient client = new HTTPClient("httpbin.org", 80, "/html");
        assertEquals(200, client.getStatusCode());
    }
}
