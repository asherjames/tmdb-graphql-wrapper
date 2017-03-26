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

public class KeywordSchema extends AbstractSchema {

    public KeywordSchema(TmdbSearcher searcher) {
        super(searcher);
    }

    private GraphQLObjectType keywordObjectType = newObject()
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

    private GraphQLFieldDefinition keywordResultFieldType = newFieldDefinition()
            .type(new GraphQLList(keywordObjectType))
            .name("keywordList")
            .argument(arg -> arg.name("filmId")
                    .type(GraphQLString))
            .dataFetcher(env -> {
                HttpResponse<JsonNode> response = searcher.sendRequest(TmdbArgUrl.MOVIE_KEYWORDS_URL, env.getArgument("filmId"));
                return response.getBody().getObject().getJSONArray("keywords");
            }).build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return keywordResultFieldType;
    }
}
