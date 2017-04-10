package ash.java.graphql.types;

import ash.java.graphql.data.models.Genre;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class GenreType {

    private GenreType() {}

    private static GraphQLObjectType genreObjectType = newObject()
            .name("genre")
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(env -> {
                        Genre genre = (Genre) env.getSource();
                        return genre.getId();
                    }))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name")
                    .dataFetcher(env -> {
                        Genre genre = (Genre) env.getSource();
                        return genre.getName();
                    }))
            .build();

    public static GraphQLObjectType getType() {
        return genreObjectType;
    }
}
