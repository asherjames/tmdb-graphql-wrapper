package ash.java.graphql;

import java.util.Map;

public class HelloQuery {

    public static Object executeHello(String query) {
        return HelloSchema.graphQL.execute(query).getData();
    }
}
