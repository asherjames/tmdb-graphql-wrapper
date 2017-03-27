package ash.java.graphql.data;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MovieDaoImpl implements MovieDao {
    @Override
    public Map<Integer, String> getKeywordsForMovie(int movieId) {
        return null;
    }
}
