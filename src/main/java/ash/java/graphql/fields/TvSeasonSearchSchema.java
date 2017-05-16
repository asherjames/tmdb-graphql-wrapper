package ash.java.graphql.fields;


import ash.java.graphql.data.TvDao;
import graphql.schema.GraphQLFieldDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvSeasonSearchSchema implements FieldProducer {

    private TvDao movieDao;

    @Autowired
    public TvSeasonSearchSchema(TvDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public GraphQLFieldDefinition getFieldDefinition() {
        return null;
    }
}
