package ash.java.graphql.types;

import ash.java.graphql.data.models.Movie;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

import java.util.function.Function;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class MovieType {

    private MovieType() {}

    private static DataFetcher makeDataFetcher(Function<Movie, Object> func) {
        return (DataFetchingEnvironment env) -> {
            Movie movie = (Movie) env.getSource();
            return func.apply(movie);
        };
    }

    private static GraphQLObjectType movieObjectType = newObject()
            .name("movie")
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("poster_path")
                    .dataFetcher(makeDataFetcher(Movie::getPosterPath)))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("adult"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("overview"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("release_date")
                    .dataFetcher(makeDataFetcher(Movie::getReleaseDate)))
            .field(newFieldDefinition()
                    .type(new GraphQLList(GraphQLInt))
                    .name("genres_ids")
                    .dataFetcher(makeDataFetcher(Movie::getGenreIds)))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_title")
                    .dataFetcher(makeDataFetcher(Movie::getOriginalTitle)))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_language")
                    .dataFetcher(makeDataFetcher(Movie::getOriginalLanguage)))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("title"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("backdrop_path")
                    .dataFetcher(makeDataFetcher(Movie::getBackdropPath)))
            .field(newFieldDefinition()
                    .type(GraphQLFloat)
                    .name("popularity"))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("vote_count")
                    .dataFetcher(makeDataFetcher(Movie::getVoteCount)))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("video"))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("vote_average")
                    .dataFetcher(makeDataFetcher(Movie::getVoteAverage)))
            .build();

    public static GraphQLObjectType getType() {
        return movieObjectType;
    }
}
