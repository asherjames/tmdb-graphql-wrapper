package ash.java.graphql.schema;

import ash.java.graphql.data.TmdbSearcher;
import graphql.schema.GraphQLFieldDefinition;

public abstract class AbstractSchema {

    protected final TmdbSearcher searcher;

    public AbstractSchema(final TmdbSearcher searcher) {
        this.searcher = searcher;
    }

    public abstract GraphQLFieldDefinition getFieldDefinition();
}
