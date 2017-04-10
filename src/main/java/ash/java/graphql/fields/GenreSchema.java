package ash.java.graphql.fields;

import ash.java.graphql.data.GenreDao;
import ash.java.graphql.data.models.Genre;
import ash.java.graphql.types.GenreType;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private GraphQLFieldDefinition genreListFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(GenreType.getType()))
            .name("genres")
            .dataFetcher(env -> genreDao.getAllMovieGenres())
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return genreListFieldDefinition;
    }
}