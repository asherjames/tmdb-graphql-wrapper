package ash.java.graphql.data;

import ash.java.graphql.data.models.Movie;

import java.util.List;
import java.util.Map;

public interface SearchDao {

    List<Movie> searchMoviesWithQuery(String query);

    List<Movie> searchMoviesWithMultipleParameters(Map<String, String> params);
}
