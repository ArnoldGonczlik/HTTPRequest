import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class HTTPClientTest {
    @Test
    void dummyTest() {
        HTTPClient test = new HTTPClient();
        assertEquals(200, test.getResponse());
    }
}
