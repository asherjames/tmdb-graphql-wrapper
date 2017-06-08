package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.TvDao;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.TvSeasonSearchSchema;
import ash.java.graphql.test.TestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        Object seasonIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){id}}");
        seasonIdJson = TestUtil.extractData(seasonIdQueryResultObject);
    }

    @Test
    public void seasonIdIsCorrect() {
        assertThat(getResult(seasonIdJson).get("id")).isEqualTo(new JsonPrimitive(3676));
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
}
