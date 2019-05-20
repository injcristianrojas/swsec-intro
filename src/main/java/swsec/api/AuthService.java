package swsec.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import swsec.api.helpers.ResponseBuilder;
import swsec.api.helpers.TokenSecurity;

@Path("/auth")
public class AuthService {
	
	@POST
	@Path("/login")
	@Produces("application/json")
	@Consumes("application/json")
	public Response authenticate(@PathParam("username") String username, @PathParam("password") String password) {
		try {
			String token = TokenSecurity.generateJwtToken("jperez");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("x-access-token", token);
			
			return ResponseBuilder.createResponse( Response.Status.OK, map );
		} catch (Exception e) {
			return ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED );
		}
	}
	
}
