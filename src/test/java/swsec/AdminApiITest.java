package swsec;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import swsec.config.ApplicationProperties;

public class AdminApiITest {

	private String jwtToken;

	private static final String TEST_USER = "testuser";
	private static final String TEST_PASSWORD = "testpass";
	
	@Before
	public void setUp() throws IOException {
		jwtToken = ApplicationProperties.INSTANCE.usesJWT() ? getJWTToken() : null;
	}
	
	private static String getJWTToken() throws IOException {
		HttpPost request = new HttpPost("http://127.0.0.1:8080/api/auth/login");
		StringEntity rawData = new StringEntity("{\"username\": \"" + ApplicationProperties.INSTANCE.testUser() + "\", \"password\": \"" + ApplicationProperties.INSTANCE.testPassword() + "\"}");
		request.addHeader("Content-Type", "application/json");
		request.setEntity(rawData);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		return response.getFirstHeader("Authorization").getValue();
	}

	@Test
	public void createUser() throws IOException {
		HttpPost request = new HttpPost("http://127.0.0.1:8080/api/users/add");
		if (jwtToken != null) request.addHeader("Authorization", jwtToken);
		StringEntity rawData = new StringEntity("{\"username\": \"" + TEST_USER + "\", \"password\": \"" + TEST_PASSWORD + "\", \"type\": \"1\"}");
		request.addHeader("Content-Type", "application/json");
		request.setEntity(rawData);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void deleteUser() throws IOException {
		HttpDelete request = new HttpDelete("http://127.0.0.1:8080/api/users/delete/" + TEST_USER);
		if (jwtToken != null) request.addHeader("Authorization", jwtToken);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusLine().getStatusCode());
	}

}
