package ash.java.graphql.test.schemas;

import ash.java.graphql.data.SearchDao;
import ash.java.graphql.data.models.Movie;
import ash.java.graphql.schemas.FieldProducer;
import ash.java.graphql.schemas.MovieSearchSchema;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieSearchQueryTest {

    private static Object resultObject;

    @BeforeClass
    public static void setupResults() {
//        TmdbSchema schema = new TmdbSchema(new TmdbHttpUtils());
//
//        resultObject = schema.executeQuery("{movieSearch(query: \"Alien\"){poster_path}}");
    }

//    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull();
    }

    private static List<FieldProducer> mockFields() {
        List<Movie> movies = new ArrayList<>();

        Movie dasBoot = new Movie();
        dasBoot.setPosterPath("/kI1rptTkqDWj6SBRsYwguBvPViT.jpg");
        dasBoot.setAdult(false);
        dasBoot.setOverview("A German submarine hunts allied ships...");
        dasBoot.setReleaseDate("1981-09-16");

        movies.add(dasBoot);

        SearchDao searchDao = mock(SearchDao.class);

        when(searchDao.searchMoviesWithQuery("das boot")).thenReturn(movies);

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new MovieSearchSchema(searchDao));

        return fieldProducers;
    }
}
