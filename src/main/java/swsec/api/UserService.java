package swsec.api;

import swsec.Helpers;
import swsec.api.mappings.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("/users")
public class UserService {

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserJSON(@PathParam("id") String id) {

        User user = new User();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection(Helpers.getSqliteUrl());
            Statement statement = conexion.createStatement();
            String query = "SELECT * FROM usuarios WHERE id=" + id;
            ResultSet resultado = statement.executeQuery(query);
            resultado.next();
            user.setType(resultado.getInt("type"));
            user.setUsername(resultado.getString("username"));
            statement.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
