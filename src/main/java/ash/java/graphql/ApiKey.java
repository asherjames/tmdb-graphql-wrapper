package ash.java.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ApiKey {
    private static ApiKey ourInstance = new ApiKey();
    private static String apiKeyString;
    private Logger log = LoggerFactory.getLogger(ApiKey.class);
    private Properties props = new Properties();

    private ApiKey() {
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("apiKey.properties"));
            apiKeyString = props.getProperty("apiKey");
        } catch (IOException e) {
            log.error("Could not load properties file!  Attempting to load from environment variable...", e);

            apiKeyString = System.getenv("TMDB_API_KEY");

            if(apiKeyString.isEmpty()) {
                throw new TmdbGqlException("Could not load API key from environment variable!");
            }
        }
    }

    public static String getKey() {
        return apiKeyString;
    }
}
