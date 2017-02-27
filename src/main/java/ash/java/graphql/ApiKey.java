package ash.java.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiKey {
    private static ApiKey ourInstance = new ApiKey();
    private Logger log = LoggerFactory.getLogger(ApiKey.class);
    private Properties props = new Properties();

    private ApiKey() {
        try {
            props.load(new FileInputStream("apiKey.properties"));
        } catch (IOException e) {
            log.error("Could not load properties file!", e);
        }
    }

    public static String getKey() {
        return ourInstance.props.getProperty("apikey");
    }
}
