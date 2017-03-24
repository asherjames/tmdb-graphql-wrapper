import ash.java.graphql.TmdbSchema;
import ash.java.graphql.schema.MovieSchema;
import com.google.gson.Gson;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MovieSearchQueryTest {

    private static Object resultObject;

    @BeforeClass
    public static void setupResults() {
        Gson gson = new Gson();

        resultObject = TmdbSchema.executeQuery("{movieSearch(query: \"Alien\"){poster_path}}");
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull();
    }
}
