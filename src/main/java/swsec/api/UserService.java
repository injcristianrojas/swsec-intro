package swsec.api;

import swsec.Helpers;
import swsec.api.helpers.ResponseBuilder;
import swsec.api.helpers.TokenSecurity;
import swsec.api.mappings.User;
import swsec.config.ApplicationProperties;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.sql.*;

@Path("/users")
public class UserService {

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserJSON(@PathParam("id") String id, @HeaderParam("Authorization") String authorization) {

        User user = new User();
        try {
        	if (ApplicationProperties.INSTANCE.usesJWT())
        		TokenSecurity.validateJwtTokenSHA(authorization);
        	Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection(Helpers.getSqliteUrl());
            Statement statement = conexion.createStatement();
            String query = "SELECT * FROM usuarios WHERE id = " + id;
            ResultSet resultado = statement.executeQuery(query);
            resultado.next();
            user.setType(resultado.getInt("type"));
            user.setUsername(resultado.getString("username"));
            statement.close();
            conexion.close();
        } catch (Exception e) {
            return ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED );
        }
        return Response.status(Status.OK).entity(user).build();
    }


}
