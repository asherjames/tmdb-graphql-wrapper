package ash.java.graphql.data;

import java.util.Map;

public interface MovieDao {

    Map<Integer, String> getKeywordsForMovie(int movieId);
}
