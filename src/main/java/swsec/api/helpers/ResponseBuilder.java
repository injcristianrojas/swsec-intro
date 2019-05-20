package swsec.api.helpers;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ResponseBuilder {
	public static Response createResponse( Response.Status status  ) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put( "message", status.toString() );
		}
		catch( JSONException e ) {
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( Response.Status.INTERNAL_SERVER_ERROR ).build();
		}
		return Response.status( status ).entity( jsonObject.toString() ).build();
	}
	
	public static Response createResponse( Response.Status status, Map<String,Object> map ) {
		JSONObject jsonObject = new JSONObject();
		
		try {
			for( Map.Entry<String,Object> entry : map.entrySet() ) {
				jsonObject.put( entry.getKey(), entry.getValue() );
			}
		}
		catch( JSONException e ) {
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( Response.Status.INTERNAL_SERVER_ERROR ).build();
		}
		
		return Response.status( status ).entity( jsonObject.toString() ).build();
	}
	
	public static Response createResponse( Response.Status status, String token ) {
		return Response.status( status ).header("Authorization", "Bearer " + token).build();
	}
}
