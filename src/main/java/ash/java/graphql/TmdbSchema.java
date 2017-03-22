package ash.java.graphql;

import ash.java.graphql.schema.GenreSchema;
import ash.java.graphql.schema.KeywordSchema;
import ash.java.graphql.schema.MovieSchema;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import static graphql.schema.GraphQLObjectType.newObject;

public class TmdbSchema {

    private static TmdbSchema ourInstance = new TmdbSchema();

    private GraphQL graphQL;

    public static Object executeQuery(String query) {
        return ourInstance.graphQL.execute(query);
    }

    private TmdbSchema() {
        GraphQLObjectType queryType = newObject()
                .name("QueryType")
                .field(MovieSchema.getMovieSearchFieldDefinition())
                .field(KeywordSchema.getKeywordResultFieldType())
                .field(GenreSchema.getGenreListFieldDefinition())
                .build();

        GraphQLSchema tmdbSchema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        graphQL = new GraphQL(tmdbSchema);
    }
}
