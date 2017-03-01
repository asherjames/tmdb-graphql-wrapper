import ash.java.graphql.schema.GenreObjectTypes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GenreQueryTest {

    private static Object resultObject;
    private static JsonObject resultJson;

    @BeforeClass
    public static void setupResults() {
        resultObject = GenreObjectTypes.executeGenreQuery("{genres{id name}}");
        resultJson = new Gson().toJsonTree(resultObject).getAsJsonObject();
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull();
    }

    @Test
    public void correctQueryShouldReturnCorrectNumberOfGenres() {
        assertThat(resultJson.get("genres").getAsJsonArray().size()).isEqualTo(19);
    }

    @Test
    public void correctQueryShouldReturnListWithCorrectValues() {
        assertThat(resultJson.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(28));
    }
}
