package swsec.api;

import swsec.Helpers;
import swsec.api.helpers.ResponseBuilder;
import swsec.api.helpers.TokenSecurity;
import swsec.mappings.User;
import swsec.config.ApplicationProperties;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import java.sql.*;

@Path("/users")
public class UserService {

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserJSON(@PathParam("id") String id, @HeaderParam("Authorization") String authorization) {

        User user = new User();
        try {
            if (ApplicationProperties.INSTANCE.usesJWT()) {
                if (!TokenSecurity.isTokenValid(authorization))
                    return ResponseBuilder.createResponse(Status.UNAUTHORIZED);
            }
        	Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection(Helpers.SQLITE_URL);
            Statement statement = conexion.createStatement();
            String query = "SELECT * FROM usuarios WHERE id = " + id;
            ResultSet resultado = statement.executeQuery(query);
            resultado.next();
            user.setType(resultado.getInt("type"));
            user.setUsername(resultado.getString("username"));
            statement.close();
            conexion.close();
        } catch (Exception e) {
            return ResponseBuilder.createResponse( Status.INTERNAL_SERVER_ERROR );
        }
        return Response.status(Status.OK).entity(user).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(String json, @HeaderParam("Authorization") String authorization) {
		try {
			if (ApplicationProperties.INSTANCE.usesJWT()) {
				if (!TokenSecurity.isTokenValid(authorization))
					return ResponseBuilder.createResponse(Status.UNAUTHORIZED);
			}
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(json, User.class);
			Helpers.insertUser(user.getUsername(), user.getPassword());
			return ResponseBuilder.createResponse(Response.Status.OK);
		} catch (Exception e) {
			return ResponseBuilder.createResponse(Status.INTERNAL_SERVER_ERROR);
		}
	}

    @DELETE
    @Path("/delete/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username, @HeaderParam("Authorization") String authorization) {
        try {
            if (ApplicationProperties.INSTANCE.usesJWT()) {
                if (!TokenSecurity.isTokenValid(authorization))
                    return ResponseBuilder.createResponse(Status.UNAUTHORIZED);
            }
            Helpers.deleteUser(username);
        } catch (Exception e) {
            return ResponseBuilder.createResponse( Status.INTERNAL_SERVER_ERROR );
        }
        return Response.status(Status.OK).build();
    }

}
