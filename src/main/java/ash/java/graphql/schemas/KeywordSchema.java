package ash.java.graphql.schemas;

import ash.java.graphql.data.MovieDao;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@Service
public class KeywordSchema implements FieldProducer {

    private MovieDao movieDao;

    @Autowired
    public KeywordSchema(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    private GraphQLObjectType keywordObjectType = newObject()
            .name("keyword")
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(env -> {
                        Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) env.getSource();
                        return entry.getKey();
                    }))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name")
                    .dataFetcher(env -> {
                        Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) env.getSource();
                        return entry.getValue();
                    }))
            .build();

    private GraphQLFieldDefinition keywordResultFieldType = newFieldDefinition()
            .type(new GraphQLList(keywordObjectType))
            .name("keywordList")
            .argument(arg -> arg.name("filmId")
                    .type(GraphQLInt))
            .dataFetcher(env -> {
                Integer filmId = env.getArgument("filmId");
                return movieDao.getKeywordsForMovie(filmId).entrySet();
            })
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return keywordResultFieldType;
    }
}
