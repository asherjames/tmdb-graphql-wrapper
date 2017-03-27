package ash.java.graphql.schemas;

import ash.java.graphql.data.GenreDao;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@Service
public class GenreSchema implements FieldProducer {

    private GenreDao genreDao;

    @Autowired
    public GenreSchema(final GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    private GraphQLObjectType genreObjectType = newObject()
            .name("genre")
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

    private GraphQLFieldDefinition genreListFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(genreObjectType))
            .name("genres")
            .dataFetcher(env -> genreDao.getAllMovieGenres())
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return genreListFieldDefinition;
    }
}
