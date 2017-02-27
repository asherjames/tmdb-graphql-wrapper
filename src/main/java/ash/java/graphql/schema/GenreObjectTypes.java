package ash.java.graphql.schema;

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
                    .name("id"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name"))
            .build();

    private static GraphQLSchema genreSchema = GraphQLSchema.newSchema()
            .query(genreObjectType)
            .build();

    public static final GraphQL genreGraphQl = new GraphQL(genreSchema);
}
