package ash.java.graphql.schemas;

import ash.java.graphql.data.SearchDao;
import graphql.schema.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private DataFetcher movieSchemaDataFetcher = (DataFetchingEnvironment env) -> {
        JSONObject object = (JSONObject) env.getSource();
        return object.get(env.getFields().get(0).getName());
    };

    private GraphQLObjectType movieObjectType = newObject()
            .name("movie")
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("poster_path")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("adult")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("overview")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("release_date")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(new GraphQLList(GraphQLInt))
                    .name("genres_ids")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_title")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("original_language")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("title")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("backdrop_path")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("popularity")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("vote_count")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("video")
                    .dataFetcher(movieSchemaDataFetcher))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("vote_average")
                    .dataFetcher(movieSchemaDataFetcher))
            .build();

    private GraphQLFieldDefinition movieSearchFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(movieObjectType))
            .name("movieSearch")
            .argument(arg -> arg.name("query")
                    .type(GraphQLString))
            .dataFetcher(env -> {
                String query = env.getArgument("query");
                return searchDao.searchMoviesWithQuery(query);
            }).build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return movieSearchFieldDefinition;
    }
}
