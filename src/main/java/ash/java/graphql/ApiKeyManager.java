package ash.java.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ApiKeyManager {
    private static ApiKeyManager ourInstance = new ApiKeyManager();
    private static String apiKeyString;
    private Logger log = LoggerFactory.getLogger(ApiKeyManager.class);
    private Properties props = new Properties();

    private ApiKeyManager() {
        try {
            log.info("Attempting to load API key from properties file...");

            props.load(this.getClass().getClassLoader().getResourceAsStream("apiKey.properties"));
            apiKeyString = props.getProperty("apikey");
        } catch (IOException e) {
            log.error("Could not load properties file!  Attempting to load from environment variable...", e);

            apiKeyString = System.getenv("TMDB_API_KEY");

            if(apiKeyString == null) {
                throw new TmdbGqlException("Could not load API key from environment variable!");
            }
        }
    }

    public static String getKey() {
        return apiKeyString;
    }
}
