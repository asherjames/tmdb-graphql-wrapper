package ash.java.graphql.types;

import graphql.annotations.GraphQLField;

public class KeywordType extends TmdbObjectType {

    @GraphQLField
    private int id;

    @GraphQLField
    private String name;

    public KeywordType() {}

    public KeywordType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
