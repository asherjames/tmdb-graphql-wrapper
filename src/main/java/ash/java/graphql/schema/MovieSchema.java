package ash.java.graphql.schema;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static ash.java.graphql.data.TmdbSearcher.*;
import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class MovieSchema {

    private static Logger log = LoggerFactory.getLogger(MovieSchema.class);

    private MovieSchema() {
    }

    private static GraphQLObjectType movieObjectType = newObject()
            .name("movie")
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("poster_path")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("adult")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("overview")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("release_date")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(new GraphQLList(GraphQLInt))
                    .name("genres_ids")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_title")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_language")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("title")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("backdrop_path")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("popularity")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("vote_count")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("video")
                    .dataFetcher(env -> null))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("vote_average")
                    .dataFetcher(env -> null))
            .build();

    private static GraphQLObjectType movieResultObjectType = newObject()
            .name("result")
            .field(newFieldDefinition()
                    .type(new GraphQLList(movieObjectType))
                    .name("movieList")
                    .argument(arg -> arg.name("query")
                            .type(GraphQLString))
                    .dataFetcher(env -> {
                        HashMap<String, Object> queryParams = new HashMap<>();
                        queryParams.put("query", env.getArgument("query"));
                        HttpResponse<JsonNode> response = sendRequest(TmdbQueryUrl.MOVIE_SEARCH_URL, queryParams);
                        return response.getBody().getObject().getJSONArray("results");
                    })
            ).build();
}
