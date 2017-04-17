package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.SearchDao;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.MultiSearchSchema;
import com.google.gson.JsonObject;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultiSearchQueryTest {

    private static Object queryResultObject;
    private static JsonObject queryResultJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());
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
