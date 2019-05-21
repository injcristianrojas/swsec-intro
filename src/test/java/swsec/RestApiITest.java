package swsec;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class RestApiITest {

	public String getJWTToken() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("http://127.0.0.1:8080/api/auth/login");
		StringEntity rawData = new StringEntity("{ \"username\": \"" + Vars.DEFAULT_USER + "\", \"password\": \"" + Vars.DEFAULT_PASSWORD + "\"}");
		request.addHeader("content-type", "application/json");
		request.setEntity(rawData);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String token = response.getFirstHeader("Authorization").getValue();
		return token;
	}

	@Test
	public void checkUser() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://127.0.0.1:8080/api/users/get/1");
		String token = null;
		token = getJWTToken();
		request.setHeader("Authorization", token);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		assertTrue(responseString.contains("jperez"));
	}

}
