package swsec;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class RestApiITest {

    @Test
    public void checkUser() {
        try {
            HttpUriRequest request = new HttpGet("http://127.0.0.1:8080/api/users/get/1");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            assertTrue(responseString.contains("jperez"));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
