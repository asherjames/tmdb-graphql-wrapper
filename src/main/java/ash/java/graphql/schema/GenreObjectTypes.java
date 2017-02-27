package ash.java.graphql.schema;

import static ash.java.graphql.data.TmdbSearcher.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import graphql.GraphQL;
import graphql.schema.*;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

public class GenreObjectTypes {

    private static GraphQLObjectType genreObjectType = newObject()
            .name("genre")
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(env -> {
                        HttpResponse<JsonNode> response = SendRequest(GENRE_LIST_URL);
                        return response.getBody().getObject().get("id");
                    }))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name"))
            .build();

    private static GraphQLObjectType genreListObjectType = newObject()
            .name("genre_list")
            .field(newFieldDefinition()
                    .type(new GraphQLList(genreObjectType)))
            .name("genres")
            .build();

    private static GraphQLSchema genreSchema = GraphQLSchema.newSchema()
            .query(genreListObjectType)
            .build();

    public static final GraphQL genreGraphQl = new GraphQL(genreSchema);
}
