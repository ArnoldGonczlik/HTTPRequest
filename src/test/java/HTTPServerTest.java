import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerTest {

    @Test
    void shouldGetSuccessfulResponseCode() throws IOException {
        HTTPServer server = new HTTPServer(8081);
        HTTPClient client = new HTTPClient("localhost", 8081, "/definitelynotanurl");

        assertEquals(404, client.getStatusCode());
    }
}
