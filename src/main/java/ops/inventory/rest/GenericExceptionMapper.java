package ops.inventory.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            logger.info("Web Application Exception: " + exception);
            return ((WebApplicationException) exception).getResponse();
        }
        logger.error("Internal Server Error: " , exception);
        return Response.serverError().build();
    }
}
