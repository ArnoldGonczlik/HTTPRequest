import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPClientTest {
    @Test
    void dummyTest() {
        HTTPClient test = new HTTPClient();
        assertEquals(200, test.getResponse());
    }
}
