package hibernate.lambda.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

class JettySettings extends ResourceConfig {

    public JettySettings() {
        packages("hibernate.lambda.example");
        // allows the validation errors to be sent to the client.
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        // avoid errors from the @ValidateOnExecution in subclasses
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }
}
