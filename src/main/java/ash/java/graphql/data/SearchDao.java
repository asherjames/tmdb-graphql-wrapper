package ash.java.graphql.data;

import ash.java.graphql.data.models.Movie;

import java.util.List;

public interface SearchDao {

    List<Movie> searchMoviesWithQuery(String query);
}
