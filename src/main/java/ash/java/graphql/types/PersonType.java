package ash.java.graphql.types;

import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class PersonType {

    private PersonType() {
    }

    private static GraphQLObjectType personObjectType = newObject()
            .name("person")
            .field(newFieldDefinition()
                    .type(GraphQLBoolean)
                    .name("adult"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name"))
            .field(newFieldDefinition()
                    .type(GraphQLLong)
                    .name("popularity"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("profilePath"))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("mediaType"))
            .build();

    public static GraphQLObjectType getType() {
        return personObjectType;
    }
}
