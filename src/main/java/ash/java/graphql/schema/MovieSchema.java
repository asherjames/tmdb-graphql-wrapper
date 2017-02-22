package ash.java.graphql.schema;

import graphql.GraphQL;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class MovieSchema {

    private static GraphQLObjectType movieQueryType = newObject()
            .name("movieQuery")
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
                    .name("genre_ids"))
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

    private static GraphQLSchema movieSchema = GraphQLSchema.newSchema()
            .query(movieQueryType)
            .build();

    public static GraphQL graphQL = new GraphQL(movieSchema);
}
