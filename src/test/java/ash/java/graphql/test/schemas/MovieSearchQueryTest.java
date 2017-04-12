package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.SearchDao;
import ash.java.graphql.data.models.Movie;
import ash.java.graphql.data.models.Person;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.MovieSearchSchema;
import ash.java.graphql.test.TestUtil;
import ash.java.graphql.types.PersonType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import graphql.annotations.GraphQLAnnotations;
import graphql.schema.GraphQLObjectType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieSearchQueryTest {

    private static Object posterPathResultObject;
    private static JsonObject posterPathResultJson;

    private static Object multipleFieldsResultObject;
    private static JsonObject multipleFieldsResultJson;

    private static Object nullQueryResultObject;
    private static JsonArray nullQueryResultJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        posterPathResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){poster_path}}");
        posterPathResultJson = TestUtil.extractData(posterPathResultObject);

        multipleFieldsResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){release_date title popularity vote_count}}");
        multipleFieldsResultJson = TestUtil.extractData(multipleFieldsResultObject);

        nullQueryResultObject = schema.executeQuery("{movieSearch(year: 1981){release_date title popularity vote_count}}");
        nullQueryResultJson = TestUtil.extractError(nullQueryResultObject);
    }

    @Test
    public void correctPosterpathQueryShouldNotReturnNull() {
        assertThat(posterPathResultObject).isNotNull();
    }

    @Test
    public void correctPosterpathQueryShouldReturnCorrectValue() {
        assertThat(posterPathResultJson.get("movieSearch").getAsJsonArray().get(0).getAsJsonObject().get("poster_path"))
                .isEqualTo(new JsonPrimitive("/kI1rptTkqDWj6SBRsYwguBvPViT.jpg"));
    }

    @Test
    public void correctMultipleFieldQueryReturnsAllFields() {
        JsonObject queryObject = multipleFieldsResultJson.get("movieSearch").getAsJsonArray().get(0).getAsJsonObject();

        assertThat(queryObject.get("release_date")).isEqualTo(new JsonPrimitive("1981-09-16"));
        assertThat(queryObject.get("title")).isEqualTo(new JsonPrimitive("Das Boot"));
        assertThat(queryObject.get("popularity")).isEqualTo(new JsonPrimitive(3.495501));
        assertThat(queryObject.get("vote_count")).isEqualTo(new JsonPrimitive(501));
    }

    @Test
    public void nullQueryReturnsError() {
        JsonObject errorObject = nullQueryResultJson.get(0).getAsJsonObject();

        assertThat(errorObject.get("description").getAsString()).isEqualTo("Missing field argument query");
    }

    private static List<FieldProducer> mockFields() {
        List<Movie> movies = new ArrayList<>();

        Movie dasBoot = new Movie();
        dasBoot.setPosterPath("/kI1rptTkqDWj6SBRsYwguBvPViT.jpg");
        dasBoot.setAdult(false);
        dasBoot.setOverview("A German submarine hunts allied ships...");
        dasBoot.setReleaseDate("1981-09-16");
        dasBoot.setGenreIds(Stream.of(28, 18, 36, 10752, 12).collect(Collectors.toList()));
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

        Map<String, Object> params = new HashMap<>();
        params.put("query", "Das Boot");
        params.put("language", null);
        params.put("page", null);
        params.put("include_adult", null);
        params.put("region", null);
        params.put("year", null);
        params.put("primary_release_year", null);

        when(searchDao.searchMoviesWithMultipleParameters(params)).thenReturn(movies);

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new MovieSearchSchema(searchDao));

        return fieldProducers;
    }
}
