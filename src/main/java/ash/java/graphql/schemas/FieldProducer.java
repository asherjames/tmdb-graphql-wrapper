package ash.java.graphql.schemas;

import graphql.schema.GraphQLFieldDefinition;

public interface FieldProducer {

    GraphQLFieldDefinition getFieldDefinition();
}
