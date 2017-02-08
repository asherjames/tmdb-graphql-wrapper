package ash.java.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class HelloSchema {

    public static GraphQLObjectType helloQueryType = newObject()
            .name("helloWorldQuery")
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("hello")
                    .staticValue("world"))
            .build();

    public static GraphQLSchema helloSchema = GraphQLSchema.newSchema()
            .query(helloQueryType)
            .build();

    public static GraphQL graphQL = new GraphQL(helloSchema);
}
