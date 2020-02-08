package hibernate.lambda.example.services;

import hibernate.lambda.example.model.VersionResponse;
import hibernate.lambda.example.rest.resources.VersionResource;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class VersionService {
    public VersionResponse getVersion() throws IOException {

        Properties properties = new Properties();
        VersionResponse response = new VersionResponse();

        URL url = VersionResource.class.getResource("/maven.properties");
        String version = null;
        String buildDateTime = null;

        if (url != null) {
            properties.load(url.openStream());
            version = properties.getProperty("version");
            buildDateTime = properties.getProperty("buildDateTime");
        }

        response.setName("Hibernate Lambda Example API");
        response.setVersion(version);
        response.setBuildDateTime(buildDateTime);

        return response;
    }
}
