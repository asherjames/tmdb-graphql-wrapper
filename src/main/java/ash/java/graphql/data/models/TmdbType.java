package ash.java.graphql.data.models;

import graphql.annotations.GraphQLAnnotations;
import graphql.schema.GraphQLObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TmdbType {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    public GraphQLObjectType getGraphQlType() {
        GraphQLObjectType objectType = null;
        try {
            objectType = GraphQLAnnotations.object(this.getClass());
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            log.error("Exception while trying to produce GraphQL type for class", e);
        }

        return objectType;
    }
}
