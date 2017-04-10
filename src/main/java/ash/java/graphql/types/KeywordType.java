package ash.java.graphql.types;

import ash.java.graphql.data.models.Keyword;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class KeywordType {

    private KeywordType() {}

    private static GraphQLObjectType keywordObjectType = newObject()
            .name("keyword")
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("id")
                    .dataFetcher(env -> {
                        Keyword keyword = (Keyword) env.getSource();
                        return keyword.getId();
                    }))
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name")
                    .dataFetcher(env -> {
                        Keyword keyword = (Keyword) env.getSource();
                        return keyword.getName();
                    }))
            .build();

    public static GraphQLObjectType getType() {
        return keywordObjectType;
    }
}
