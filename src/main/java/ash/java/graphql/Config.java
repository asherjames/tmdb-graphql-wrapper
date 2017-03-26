package ash.java.graphql;

import ash.java.graphql.api.Endpoints;
import ash.java.graphql.data.TmdbSearcher;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config extends ResourceConfig {
    public Config() {
        register(Endpoints.class);
    }
}
