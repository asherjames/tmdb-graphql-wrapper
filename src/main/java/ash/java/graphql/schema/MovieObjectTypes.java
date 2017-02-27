package ash.java.graphql.schema;

import graphql.GraphQL;
import graphql.schema.*;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

public class MovieObjectTypes {

    private static GraphQLObjectType movieObjectType = newObject()
            .name("movie")
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("poster_path"))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("adult"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("overview"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("release_date"))
            .field(newFieldDefinition()
                    .type(new GraphQLList(GraphQLInt))
                    .name("genre_ids")
                    .argument(newArgument()
                            .name("genres")
                            .type(new GraphQLList(GraphQLInt))))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_title"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_language"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("title"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("backdrop_path"))
            .field(newFieldDefinition()
                    .type(GraphQLBigDecimal)
                    .name("popularity"))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("vote_count"))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("video"))
            .field(newFieldDefinition()
                    .type(GraphQLBigDecimal)
                    .name("vote_average"))
            .build();

    private static GraphQLObjectType resultObjectType = newObject()
            .name("results")
            .field(newFieldDefinition()
                    .type(new GraphQLList(movieObjectType))
                    .name("results"))
            .build();

    private static GraphQLSchema movieSchema = GraphQLSchema.newSchema()
            .query(resultObjectType)
            .build();

    public static final GraphQL movieGraphQl = new GraphQL(movieSchema);
}
