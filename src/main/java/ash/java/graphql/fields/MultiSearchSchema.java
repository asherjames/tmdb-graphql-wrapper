package ash.java.graphql.fields;

import ash.java.graphql.data.SearchDao;
import ash.java.graphql.types.MovieType;
import ash.java.graphql.types.PersonType;
import ash.java.graphql.types.TvShowType;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLUnionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.schema.GraphQLUnionType.newUnionType;

@Service
public class MultiSearchSchema implements FieldProducer {

    private SearchDao searchDao;

    @Autowired
    public MultiSearchSchema(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    private GraphQLUnionType multiSearchType = newUnionType()
            .name("MultiSearch")
            .possibleType(new PersonType().getGraphQlType())
            .possibleType(new MovieType().getGraphQlType())
            .possibleType(new TvShowType().getGraphQlType())
            .typeResolver(object -> {
                if(object instanceof PersonType) {
                    return new PersonType().getGraphQlType();
                }
                if(object instanceof MovieType) {
                    return new MovieType().getGraphQlType();
                }
                if(object instanceof TvShowType) {
                    return new TvShowType().getGraphQlType();
                }
                return null;
            })
            .build();

    @Override
    public GraphQLFieldDefinition getFieldDefinition() {
        return null;
    }
}
