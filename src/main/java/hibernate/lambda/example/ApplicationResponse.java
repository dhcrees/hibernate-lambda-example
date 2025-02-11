package hibernate.lambda.example;

import org.apache.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ApplicationResponse implements ContainerResponseFilter {

    private static final Logger LOGGER = Logger.getLogger(ApplicationResponse.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        LOGGER.info(requestContext.getUriInfo().getRequestUri().toString());
    }
}
