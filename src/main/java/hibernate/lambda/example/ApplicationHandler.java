package hibernate.lambda.example;

import com.amazonaws.serverless.proxy.jersey.JerseyLambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ApplicationHandler implements RequestStreamHandler {

    private static final ResourceConfig jerseyApplication = new ResourceConfig()
            .packages("dvsa.serverless")
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true)
            .register(JacksonFeature.class)
            .register(ApplicationResponse.class);

    private static final JerseyLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler
            = JerseyLambdaContainerHandler.getAwsProxyHandler(jerseyApplication);

    /**
     * This lambda function doesn't call any methods it instead calls the jersey config which determines which methods will be called.
     *
     * @param inputStream InputStream
     * @param outputStream OutputStream
     * @param context Context
     * @throws IOException exception
     */
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}
