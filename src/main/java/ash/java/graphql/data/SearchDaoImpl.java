package ash.java.graphql.data;

import ash.java.graphql.data.models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchDaoImpl implements SearchDao {
    @Override
    public List<Movie> searchMoviesWithQuery(String query) {
        return null;
    }
}
