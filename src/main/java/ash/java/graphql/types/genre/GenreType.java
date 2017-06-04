package ash.java.graphql.types.genre;

import ash.java.graphql.types.TmdbObjectType;
import graphql.annotations.GraphQLField;

public class GenreType extends TmdbObjectType {

    @GraphQLField
    private int id;

    @GraphQLField
    private String name;

    public GenreType() {}

    public GenreType(int id, String name) {
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
