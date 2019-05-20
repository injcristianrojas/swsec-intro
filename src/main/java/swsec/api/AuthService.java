package swsec.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import swsec.api.helpers.ResponseBuilder;
import swsec.api.helpers.TokenSecurity;

@Path("/auth")
public class AuthService {
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response authenticate(@PathParam("username") String username, @PathParam("password") String password) {
		try {
			String token = TokenSecurity.generateJwtToken("jperez");
			return ResponseBuilder.createResponse( Response.Status.OK, token );
		} catch (Exception e) {
			return ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED );
		}
	}
	
}
