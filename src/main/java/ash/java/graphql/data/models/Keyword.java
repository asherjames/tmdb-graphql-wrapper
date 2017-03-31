package ash.java.graphql.data.models;

public class Keyword {
    private int id;
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
