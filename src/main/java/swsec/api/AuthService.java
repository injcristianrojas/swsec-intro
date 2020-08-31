package swsec.api;

import static swsec.Helpers.checkUserLogin;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import swsec.api.helpers.ResponseBuilder;
import swsec.api.helpers.TokenSecurity;
import swsec.mappings.User;

@Path("/auth")
public class AuthService {
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response authenticate(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			if (checkUserLogin(user.getUsername(), user.getPassword())) {
				String token = TokenSecurity.generateJwtTokenSHA(user.getUsername());
				return ResponseBuilder.createResponse( Response.Status.OK, token );
			} else
				throw new Exception();
		} catch (Exception e) {
			return ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED );
		}
	}
	
}
