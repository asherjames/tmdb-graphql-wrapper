package ash.java.graphql.fields;

import ash.java.graphql.data.MovieDao;
import ash.java.graphql.data.models.Keyword;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
                        Keyword keyword = (Keyword) env.getSource();
                        return keyword.getId();
                    }))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name")
                    .dataFetcher(env -> {
                        Keyword keyword = (Keyword) env.getSource();
                        return keyword.getName();
                    }))
            .build();

    private GraphQLFieldDefinition keywordResultFieldType = newFieldDefinition()
            .type(new GraphQLList(keywordObjectType))
            .name("keywordList")
            .argument(arg -> arg.name("filmId")
                    .type(GraphQLInt))
            .dataFetcher(env -> movieDao.getKeywordsForMovie(env.getArgument("filmId")))
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return keywordResultFieldType;
    }
}
