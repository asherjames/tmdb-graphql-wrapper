package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.SearchDao;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.MultiSearchSchema;
import ash.java.graphql.test.TestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultiSearchQueryTest {

    private static Gson gson = new Gson();

    private static JsonObject personNameQueryJson;
    private static JsonObject movieTitleQueryJson;
    private static JsonObject tvShowNameQueryJson;
    private static JsonObject moviePersonQueryJson;
    private static JsonObject multiTypeQueryJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        Object personNameQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){"
                + "... on Person {name}}}");

        personNameQueryJson = TestUtil.extractData(personNameQueryResultObject);

        Object movieTitleQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){"
                + "... on Movie{title}}}");

        movieTitleQueryJson = TestUtil.extractData(movieTitleQueryResultObject);

        Object tvShowTitleQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){"
                + "... on TvShow {name}}}");

        tvShowNameQueryJson = TestUtil.extractData(tvShowTitleQueryResultObject);

        Object moviePersonQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){"
                + "... on Person {profilePath}"
                + "... on Movie {releaseDate}}}");

        moviePersonQueryJson = TestUtil.extractData(moviePersonQueryResultObject);

        Object multiTypeQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){"
                + "... on Person {id}"
                + "... on Movie {overview genreIds originalLanguage}"
                + "... on TvShow{popularity firstAirDate originalName}}}");

        multiTypeQueryJson = TestUtil.extractData(multiTypeQueryResultObject);
    }

    @Test
    public void correctValueInPersonNameQuery() {
        assertThat(getPeople(personNameQueryJson).get("name")).isEqualTo(new JsonPrimitive("Sigourney Weaver"));
    }

    @Test
    public void personNameQueryContainsOnlyPersonResults() {
        assertThat(getMovies(personNameQueryJson).size()).isEqualTo(0);
        assertThat(getPeople(personNameQueryJson).size()).isEqualTo(1);
        assertThat(getTvShows(personNameQueryJson).size()).isEqualTo(0);
    }

    @Test
    public void correctValueInMovieTitleQuery() {
        assertThat(getMovies(movieTitleQueryJson).get("title")).isEqualTo(new JsonPrimitive("Das Boot"));
    }

    @Test
    public void movieTitleQueryContainsOnlyMovieResults() {
        assertThat(getMovies(movieTitleQueryJson).size()).isEqualTo(1);
        assertThat(getPeople(movieTitleQueryJson).size()).isEqualTo(0);
        assertThat(getTvShows(movieTitleQueryJson).size()).isEqualTo(0);
    }

    @Test
    public void correctValueInTvShowNameQuery() {
        assertThat(getTvShows(tvShowNameQueryJson).get("name")).isEqualTo(new JsonPrimitive("House"));
    }

    @Test
    public void tvShowNameQueryContainsOnlyTvShowResults() {
        assertThat(getMovies(tvShowNameQueryJson).size()).isEqualTo(0);
        assertThat(getPeople(tvShowNameQueryJson).size()).isEqualTo(0);
        assertThat(getTvShows(tvShowNameQueryJson).size()).isEqualTo(1);
    }

    @Test
    public void moviePersonQueryHasCorrectValues() {
        assertThat(getPeople(moviePersonQueryJson).get("profilePath")).isEqualTo(new JsonPrimitive
                ("/wlg55BTcp3kqfTb3zDtqOFyqhDR.jpg"));
        assertThat(getMovies(moviePersonQueryJson).get("releaseDate")).isEqualTo(new JsonPrimitive("1981-09-16"));
    }

    @Test
    public void moviePersonQueryContainsOnlyMovieAndPersonResults() {
        assertThat(getMovies(moviePersonQueryJson).size()).isEqualTo(1);
        assertThat(getPeople(moviePersonQueryJson).size()).isEqualTo(1);
        assertThat(getTvShows(moviePersonQueryJson).size()).isEqualTo(0);
    }

    @Test
    public void multiTypeQueryContainsCorrectPerson() {
        assertThat(getPeople(multiTypeQueryJson).get("id")).isEqualTo(new JsonPrimitive(10205));
    }

    @Test
    public void multiTypeQueryContainsCorrectMovie() {
        JsonObject movie = getMovies(multiTypeQueryJson);
        Type genreListType = new TypeToken<List<Integer>>(){}.getType();

        assertThat(movie.get("overview")).isEqualTo(new JsonPrimitive("A German submarine hunts allied ships..."));

        List<Integer> genreIds = gson.fromJson(movie.get("genreIds"), genreListType);
        assertThat(genreIds).containsExactlyInAnyOrder(28, 18, 36, 10752, 12);

        assertThat(movie.get("originalLanguage")).isEqualTo(new JsonPrimitive("de"));
    }

    @Test
    public void multiTypeQueryContainsCorrectTvShow() {

    }

    private JsonObject getMovies(JsonObject input) {
        return getResultElement(input, 0);
    }

    private JsonObject getPeople(JsonObject input) {
        return getResultElement(input, 1);
    }

    private JsonObject getTvShows(JsonObject input) {
        return getResultElement(input, 2);
    }

    private JsonObject getResultElement(JsonObject input, int type) {
        return input.get("multiSearch").getAsJsonArray().get(type).getAsJsonObject();
    }

    private static List<FieldProducer> mockFields() {
        List<Object> results = new ArrayList<>();

        results.add(TestTypeInstances.getMovie());
        results.add(TestTypeInstances.getPerson());
        results.add(TestTypeInstances.getTvShow());

        SearchDao searchDao = mock(SearchDao.class);

        Map<String, Object> params = new HashMap<>();
        params.put("query", "query input");

        when(searchDao.searchMultiSearch(params)).thenReturn(results);

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new MultiSearchSchema(searchDao));

        return fieldProducers;
    }
}
