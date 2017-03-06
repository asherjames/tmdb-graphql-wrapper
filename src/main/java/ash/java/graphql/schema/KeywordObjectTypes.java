package ash.java.graphql.schema;

import graphql.GraphQL;
import graphql.schema.*;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

public class KeywordObjectTypes {

    private static GraphQLObjectType keywordObjectType = newObject()
            .name("movie")
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
                    .type(GraphQLInt)
                    .name("id"))
            .field(newFieldDefinition()
                    .type(new GraphQLList(keywordObjectType))
                    .name("keywords"))
            .build();

    private static GraphQLSchema keywordSchema = GraphQLSchema.newSchema()
            .query(keywordResultObjectType)
            .build();

    private static final GraphQL movieGraphQl = new GraphQL(keywordSchema);

    public static Object executeMovieQuery(String query) {
        return movieGraphQl.execute(query).getData();
    }
}
