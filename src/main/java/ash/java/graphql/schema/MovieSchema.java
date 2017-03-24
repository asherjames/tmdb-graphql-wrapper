package ash.java.graphql.schema;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import graphql.schema.*;
import org.json.JSONObject;

import java.util.HashMap;

import static ash.java.graphql.data.TmdbSearcher.*;
import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class MovieSchema {
    private MovieSchema() {
    }

    private static DataFetcher movieSchemaDataFetcher = (DataFetchingEnvironment env) -> {
        JSONObject object = (JSONObject) env.getSource();
        return object.get(env.getFields().get(0).getName());
    };

    private static GraphQLObjectType movieObjectType = newObject()
            .name("movie")
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("poster_path")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("adult")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("overview")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("release_date")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(new GraphQLList(GraphQLInt))
                    .name("genres_ids")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_title")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_language")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("title")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("backdrop_path")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("popularity")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("vote_count")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("video")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("vote_average")
                    .dataFetcher(movieSchemaDataFetcher))
            .build();

    private static GraphQLFieldDefinition movieSearchFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(movieObjectType))
            .name("movieSearch")
            .argument(arg -> arg.name("query")
                    .type(GraphQLString))
            .dataFetcher(env -> {
                HashMap<String, Object> queryParams = new HashMap<>();
                queryParams.put("query", env.getArgument("query"));
                HttpResponse<JsonNode> response = sendRequest(TmdbQueryUrl.MOVIE_SEARCH_URL, queryParams);
                return response.getBody().getObject().getJSONArray("results");
            }).build();

    public static GraphQLFieldDefinition getMovieSearchFieldDefinition() {
        return movieSearchFieldDefinition;
    }
}
