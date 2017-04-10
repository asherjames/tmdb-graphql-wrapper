package ash.java.graphql.fields;

import ash.java.graphql.data.SearchDao;
import ash.java.graphql.data.models.Movie;
import ash.java.graphql.types.MovieType;
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

    private GraphQLFieldDefinition movieSearchFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(MovieType.getType()))
            .name("movieSearch")
            .argument(arg -> arg.name("query").type(new GraphQLNonNull(GraphQLString)))
            .argument(arg -> arg.name("language").type(GraphQLString))
            .argument(arg -> arg.name("page").type(GraphQLInt))
            .argument(arg -> arg.name("include_adult").type(GraphQLBoolean))
            .argument(arg -> arg.name("region").type(GraphQLString))
            .argument(arg -> arg.name("year").type(GraphQLInt))
            .argument(arg -> arg.name("primary_release_year").type(GraphQLInt))
            .dataFetcher(env -> searchDao.searchMoviesWithMultipleParameters(env.getArguments()))
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return movieSearchFieldDefinition;
    }
}