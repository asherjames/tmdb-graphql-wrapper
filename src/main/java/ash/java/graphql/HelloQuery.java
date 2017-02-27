package ash.java.graphql;

import ash.java.graphql.schema.MovieObjectTypes;

public class HelloQuery {

    public static Object executeHello(String query) {
        return MovieObjectTypes.movieGraphQl.execute(query).getData();
    }
}
