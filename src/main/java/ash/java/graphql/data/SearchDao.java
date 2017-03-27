package ash.java.graphql.data;

import java.util.List;

public interface SearchDao {

    List<Movie> searchMoviesWithQuery(String query);
}
