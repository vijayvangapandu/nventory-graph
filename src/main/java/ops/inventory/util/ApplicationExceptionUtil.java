package ops.inventory.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ApplicationExceptionUtil {

	private static void throwWebApplicationException(Exception e, Response.Status status, String message) {

		if (e != null) {
			if (e instanceof WebApplicationException) {
				throw (WebApplicationException) e;
			} else {
				throw new WebApplicationException(message, e, status);
			}

		} else {
			Map<String, String> messageMap = new HashMap<String, String>();
			messageMap.put("message", message);
			throw new WebApplicationException(Response.status(status).entity(messageMap).build());
		}

	}
	
	public static void throwWebApplicationException(Response.Status status, String message) {
		throwWebApplicationException(null, status, message);
	}
	
	public static void throwWebApplicationException(Exception e) {
		throwWebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR, "");
	}

}
