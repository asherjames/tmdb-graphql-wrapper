package ash.java.graphql.schema;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import graphql.GraphQL;
import graphql.schema.*;
import org.json.JSONObject;

import java.util.Map;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import static ash.java.graphql.data.TmdbSearcher.*;

public class GenreObjectTypes {

    private static GraphQLObjectType genreObjectType = newObject()
            .name("genre")
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(env -> {
                        JSONObject object = (JSONObject) env.getSource();
                        return object.get("id");
                    }))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name")
                    .dataFetcher(env -> {
                        JSONObject object = (JSONObject) env.getSource();
                        return object.get("name");
                    }))
            .build();

    private static GraphQLObjectType genreListObjectType = newObject()
            .name("genre_list")
            .field(newFieldDefinition()
                    .type(new GraphQLList(genreObjectType))
                    .name("genres")
                    .dataFetcher(env -> {
                        HttpResponse<JsonNode> response = SendRequest(GENRE_LIST_URL);
                        return response.getBody().getObject().get("genres");
                    }))
            .build();

    private static GraphQLSchema genreSchema = GraphQLSchema.newSchema()
            .query(genreListObjectType)
            .build();

    private static final GraphQL genreGraphQl = new GraphQL(genreSchema);

    public static Map<String, Object> executeGenreQuery(String query) {
        Object result = genreGraphQl.execute(query).getData();
        try {
            return (Map<String, Object>) result;
        } catch (ClassCastException e) {
            throw new RuntimeException("Error while converting query result to Map");
        }
    }
}
