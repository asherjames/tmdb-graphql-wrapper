import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.TmdbHttpUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MovieSearchQueryTest {

    private static Object resultObject;

    @BeforeClass
    public static void setupResults() {
//        TmdbSchema schema = new TmdbSchema(new TmdbHttpUtils());
//
//        resultObject = schema.executeQuery("{movieSearch(query: \"Alien\"){poster_path}}");
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull();
    }
}
