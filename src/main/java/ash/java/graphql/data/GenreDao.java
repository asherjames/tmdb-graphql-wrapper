package ash.java.graphql.data;

import ash.java.graphql.data.models.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllMovieGenres();
}
