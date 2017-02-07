package ash.java.graphql;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config extends ResourceConfig {
    public Config() {
        register(Endpoints.class);
    }
}
