package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.SearchDao;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.MultiSearchSchema;
import ash.java.graphql.test.TestUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultiSearchQueryTest {

    private static Object personNameQueryResultObject;
    private static JsonObject personNameQueryJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        personNameQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\") {... on Person {name}}}");
        personNameQueryJson = TestUtil.extractData(personNameQueryResultObject);
    }

    @Test
    public void correctValueInPersonNameQuery() {
        assertThat(personNameQueryJson.get("multiSearch").getAsJsonArray().get(1).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("Sigourney Weaver"));
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
