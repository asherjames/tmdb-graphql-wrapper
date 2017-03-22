import ash.java.graphql.TmdbSchema;
import ash.java.graphql.schema.GenreSchema;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GenreQueryTest {

    private static Object resultObjectIdName;
    private static JsonObject resultJsonIdName;
    private static Object resultObjectId;
    private static JsonObject resultJsonId;
    private static Object resultObjectName;
    private static JsonObject resultJsonName;

    @BeforeClass
    public static void setupResults() {
        Gson gson = new Gson();

        resultObjectIdName = TmdbSchema.executeQuery("{genres{id name}}");
        resultJsonIdName = gson.toJsonTree(resultObjectIdName).getAsJsonObject();

        resultObjectId = TmdbSchema.executeQuery("{genres{id}}");
        resultJsonId = gson.toJsonTree(resultObjectId).getAsJsonObject();

        resultObjectName = TmdbSchema.executeQuery("{genres{name}}");
        resultJsonName = gson.toJsonTree(resultObjectName).getAsJsonObject();
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObjectIdName).isNotNull();
    }

    @Test
    public void correctQueryShouldReturnCorrectNumberOfGenres() {
        assertThat(resultJsonIdName.get("genres").getAsJsonArray().size()).isEqualTo(19);
    }

    @Test
    public void correctQueryShouldReturnListWithCorrectValues() {
        assertThat(resultJsonIdName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(28));

        assertThat(resultJsonIdName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("Action"));
    }

    @Test
    public void correctIdQueryReturnsListWithJustIds() {
        assertThat(resultJsonId.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(28));

        assertThat(resultJsonId.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isNull();
    }

    @Test
    public void correctNameQueryReturnsListWithJustNames() {
        assertThat(resultJsonName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isNull();

        assertThat(resultJsonName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("Action"));
    }
}
