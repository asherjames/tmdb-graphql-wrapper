package ash.java.graphql.schemas;

import ash.java.graphql.data.SearchDao;
import ash.java.graphql.data.models.Movie;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@Service
public class MovieSearchSchema implements FieldProducer {

    private SearchDao searchDao;

    @Autowired
    public MovieSearchSchema(final SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    private DataFetcher makeDataFetcher(Function<Movie, Object> func) {
        return (DataFetchingEnvironment env) -> {
            Movie movie = (Movie) env.getSource();
            return func.apply(movie);
        };
    }

    private GraphQLObjectType movieObjectType = newObject()
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

    private GraphQLFieldDefinition movieSearchFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(movieObjectType))
            .name("movieSearch")
            .argument(arg -> arg.name("query").type(new GraphQLNonNull(GraphQLString)))
            .argument(arg -> arg.name("language").type(GraphQLString))
            .argument(arg -> arg.name("page").type(GraphQLInt))
            .argument(arg -> arg.name("include_adult").type(GraphQLBoolean))
            .argument(arg -> arg.name("region").type(GraphQLString))
            .argument(arg -> arg.name("year").type(GraphQLInt))
            .argument(arg -> arg.name("primary_release_year").type(GraphQLInt))
            .dataFetcher(env -> {
                String query = env.getArgument("query");
                return searchDao.searchMoviesWithQuery(query);
            }).build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return movieSearchFieldDefinition;
    }
}
