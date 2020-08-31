package swsec;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import swsec.config.ApplicationProperties;

public class ApiITest {

	private static final String EXAMPLE_MESSAGE = "Bienvenidos a Fans de las Aves Chilenas. Soy el administrador.";
	private String jwtToken;
	
	@Before
	public void setUp() throws IOException {
		jwtToken = ApplicationProperties.INSTANCE.usesJWT() ? getJWTToken() : null;
	}
	
	public static String getJWTToken() throws IOException {
		HttpPost request = new HttpPost("http://127.0.0.1:8080/api/auth/login");
		StringEntity rawData = new StringEntity("{ \"username\": \"" + ApplicationProperties.INSTANCE.testUser() + "\", \"password\": \"" + ApplicationProperties.INSTANCE.testPassword() + "\"}");
		request.addHeader("Content-Type", "application/json");
		request.setEntity(rawData);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		return response.getFirstHeader("Authorization").getValue();
	}

	@Test
	public void checkUser() throws IOException {
		HttpGet request = new HttpGet("http://127.0.0.1:8080/api/users/get/2");
		if (jwtToken != null) request.addHeader("Authorization", jwtToken);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		assertTrue(responseString.contains(ApplicationProperties.INSTANCE.testUser()));
	}
	
	@Test
	public void testPost() throws IOException {
		HttpPost request = new HttpPost("http://127.0.0.1:8080/api/posts/add");
		if (jwtToken != null) request.addHeader("Authorization", jwtToken);
		StringEntity rawData = new StringEntity("{ \"message\": \"" + EXAMPLE_MESSAGE + "\" }");
		request.addHeader("Content-Type", "application/json");
		request.setEntity(rawData);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testIsPostCreated() throws IOException {
		HttpGet request = new HttpGet("http://127.0.0.1:8080/api/posts/get");
		if (jwtToken != null) request.setHeader("Authorization", jwtToken);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		assertTrue(responseString.contains(EXAMPLE_MESSAGE));
	}

}
