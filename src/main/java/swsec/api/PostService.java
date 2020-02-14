package swsec.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import swsec.Helpers;
import swsec.api.helpers.ResponseBuilder;
import swsec.api.helpers.TokenSecurity;
import swsec.mappings.Post;
import swsec.config.ApplicationProperties;

@Path("/posts")
public class PostService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostJSON(@HeaderParam("Authorization") String authorization) {
		List<Post> postList = new ArrayList<>();
		try {
			if (ApplicationProperties.INSTANCE.usesJWT()) {
				if (!TokenSecurity.isTokenValid(authorization))
					return ResponseBuilder.createResponse(Status.UNAUTHORIZED);
			}
			List<String> posts = Helpers.getPosts();
			for (String post : posts)
				postList.add(new Post(post));

		} catch (Exception e) {
			return ResponseBuilder.createResponse(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.status(Status.OK).entity(postList).build();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(String json, @HeaderParam("Authorization") String authorization) {
		try {
			if (ApplicationProperties.INSTANCE.usesJWT()) {
				if (!TokenSecurity.isTokenValid(authorization))
					return ResponseBuilder.createResponse(Status.UNAUTHORIZED);
			}
			ObjectMapper mapper = new ObjectMapper();
			Post post = mapper.readValue(json, Post.class);
			Helpers.insertPost(post.getMessage());
			return ResponseBuilder.createResponse(Response.Status.OK);
		} catch (Exception e) {
			return ResponseBuilder.createResponse(Status.INTERNAL_SERVER_ERROR);
		}
	}

}
