package ash.java.graphql.fields;

import ash.java.graphql.data.SearchDao;
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

//    private GraphQLUnionType multiSearchType = newUnionType()
//            .name("MultiSearch")
//            .possibleType()

    @Override
    public GraphQLFieldDefinition getFieldDefinition() {
        return null;
    }
}
