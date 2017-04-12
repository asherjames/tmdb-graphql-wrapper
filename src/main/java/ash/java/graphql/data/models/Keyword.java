package ash.java.graphql.data.models;

import graphql.annotations.GraphQLField;

public class Keyword extends TmdbType {

    @GraphQLField
    private int id;

    @GraphQLField
    private String name;

    public Keyword(int id, String name) {
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
