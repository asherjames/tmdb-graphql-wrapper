package ash.java.graphql;

import ash.java.graphql.schema.MovieSchema;

public class HelloQuery {

    public static Object executeHello(String query) {
        return MovieSchema.graphQL.execute(query).getData();
    }
}
