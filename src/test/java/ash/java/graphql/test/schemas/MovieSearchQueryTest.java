package ash.java.graphql.test.schemas;

import ash.java.graphql.data.SearchDao;
import ash.java.graphql.data.models.Movie;
import ash.java.graphql.schemas.FieldProducer;
import ash.java.graphql.schemas.MovieSearchSchema;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        dasBoot.setGenreIds(Stream.of(28,18,36,10752,12).collect(Collectors.toList()));
        dasBoot.setId(387);
        dasBoot.setOriginalTitle("Das Boot");
        dasBoot.setOriginalLanguage("de");
        dasBoot.setTitle("Das Boot");
        dasBoot.setBackdropPath("/mc0PbKrrFRCUEpI09reR3ihHrIo.jpg");
        dasBoot.setPopularity(3.495501);
        dasBoot.setVoteCount(501);
        dasBoot.setVideo(false);
        dasBoot.setVoteAverage(7.9);

        movies.add(dasBoot);

        SearchDao searchDao = mock(SearchDao.class);

        when(searchDao.searchMoviesWithQuery("das boot")).thenReturn(movies);

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new MovieSearchSchema(searchDao));

        return fieldProducers;
    }
}
