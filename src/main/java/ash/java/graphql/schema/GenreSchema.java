package ash.java.graphql.schema;

import ash.java.graphql.data.TmdbSearcher;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import graphql.schema.*;
import org.json.JSONObject;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import static ash.java.graphql.data.TmdbUrls.*;

public class GenreSchema extends AbstractSchema {

    public GenreSchema(final TmdbSearcher searcher) {
        super(searcher);
    }

    private GraphQLObjectType genreObjectType = newObject()
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

    private GraphQLFieldDefinition genreListFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(genreObjectType))
            .name("genres")
            .dataFetcher(env -> {
                HttpResponse<JsonNode> response = searcher.sendRequest(TmdbUrl.GENRE_LIST_URL);
                return response.getBody().getObject().get("genres");
            }).build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return genreListFieldDefinition;
    }
}
