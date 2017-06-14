package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.TvDao;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.TvSeasonSearchSchema;
import ash.java.graphql.test.TestUtil;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TvSeasonSearchSchemaTest {

    private static Gson gson = new Gson();

    private static JsonObject seasonIdJson;
    private static JsonObject episodeAirDateJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        Object seasonIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){id}}");
        seasonIdJson = TestUtil.extractData(seasonIdQueryResultObject);

        Object episodeAirDateQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{airDate}}}");
        episodeAirDateJson = TestUtil.extractData(episodeAirDateQueryResultObject);
    }

    @Test
    public void seasonIdIsCorrect() {
        assertThat(getResult(seasonIdJson).get("id")).isEqualTo(new JsonPrimitive(3676));
    }

    @Test
    public void episodeAirdatesAreCorrect() {
        JsonElement datesJson = episodeAirDateJson.getAsJsonObject("tvSeasonSearch").get("episodes");
        List<AirDateMapping> dates = gson.fromJson(datesJson, new TypeToken<List<AirDateMapping>>(){}.getType());

        assertThat(dates).hasSize(24);
        assertThat(dates.stream().map(m -> m.value)).containsSequence("2008-09-16", "2008-09-23", "2008-09-30", "2008-10-14", "2008-10-21");
    }

    private static JsonObject getResult(JsonObject jsonObject) {
        return jsonObject.get("tvSeasonSearch").getAsJsonObject();
    }

    private static List<FieldProducer> mockFields() {
        TvDao tvDao = mock(TvDao.class);

        when(tvDao.getTvSeason(1408, 5)).thenReturn(TestTypeInstances.getTvSeason());

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new TvSeasonSearchSchema(tvDao));

        return fieldProducers;
    }

    private class AirDateMapping {
        @SerializedName("airDate")
        private String value;
    }
}
