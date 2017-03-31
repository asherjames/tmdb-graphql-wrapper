package ash.java.graphql.data;

import ash.java.graphql.data.models.Keyword;

import java.util.List;

public interface MovieDao {

    List<Keyword> getKeywordsForMovie(int movieId);
}
