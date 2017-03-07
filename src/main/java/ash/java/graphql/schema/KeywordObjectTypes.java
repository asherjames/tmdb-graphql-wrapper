package ash.java.graphql.schema;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import graphql.GraphQL;
import graphql.schema.*;

import static ash.java.graphql.data.TmdbSearcher.*;
import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class KeywordObjectTypes {

    private KeywordObjectTypes() {
    }

    private static GraphQLObjectType keywordObjectType = newObject()
            .name("keyword")
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name"))
            .build();

    private static GraphQLObjectType keywordResultObjectType = newObject()
            .name("results")
            .field(newFieldDefinition()
                    .type(new GraphQLList(keywordObjectType))
                    .name("keywordList")
                    .argument(arg -> arg.name("filmId")
                            .type(GraphQLString))
                    .dataFetcher(env -> {
                        HttpResponse<JsonNode> response = sendRequest(TmdbArgUrl.MOVIE_KEYWORDS_URL, env.getArgument("filmId"));
                        return response.getBody();
                    }))
            .build();

    private static GraphQLSchema keywordSchema = GraphQLSchema.newSchema()
            .query(keywordResultObjectType)
            .build();

    private static final GraphQL keywordGraphQl = new GraphQL(keywordSchema);

    public static Object executeKeywordQuery(String query) {
        return keywordGraphQl.execute(query).getData();
    }
}
