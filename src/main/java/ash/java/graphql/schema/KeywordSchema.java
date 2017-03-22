package ash.java.graphql.schema;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import graphql.GraphQL;
import graphql.schema.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ash.java.graphql.data.TmdbSearcher.*;
import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class KeywordSchema {

    private KeywordSchema() {
    }

    private static GraphQLObjectType keywordObjectType = newObject()
            .name("keyword")
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

    private static GraphQLFieldDefinition keywordResultFieldType = newFieldDefinition()
            .type(new GraphQLList(keywordObjectType))
            .name("keywordList")
            .argument(arg -> arg.name("filmId")
                    .type(GraphQLString))
            .dataFetcher(env -> {
                HttpResponse<JsonNode> response = sendRequest(TmdbArgUrl.MOVIE_KEYWORDS_URL, env.getArgument("filmId"));
                return response.getBody().getObject().getJSONArray("keywords");
            }).build();

    public static GraphQLFieldDefinition getKeywordResultFieldType() {
        return keywordResultFieldType;
    }
}
