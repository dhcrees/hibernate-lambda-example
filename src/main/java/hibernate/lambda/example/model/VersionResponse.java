package hibernate.lambda.example.model;

public class VersionResponse {

    private String name;
    private String buildDateTime;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildDateTime() {
        return buildDateTime;
    }

    public void setBuildDateTime(String buildDateTime) {
        this.buildDateTime = buildDateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
