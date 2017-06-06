package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.types.tvseason.TvSeasonType;
import com.google.gson.Gson;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TvSeasonSearchSchemaTest {

    private static Gson gson = new Gson();

    @BeforeClass
    public static void setupResults() {
//        TmdbSchema schema = new TmdbSchema();
        TvSeasonType tvSeasonType = TestTypeInstances.getTvSeason();
    }

    @Test
    public void test(){}

//    private static List<FieldProducer> mockFields() {
//        TvSeasonType tvSeason = new TvSeasonType();
//    }
}
