package sirma.employee.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class EmployeeProperties {
    private final String location = "upload-dir";

    public String getLocation() {
        return location;
    }

}
