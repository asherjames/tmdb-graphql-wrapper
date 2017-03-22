import ash.java.graphql.TmdbSchema;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class KeywordQueryTest {

    private static Object resultObject;
    private static JsonObject resultJson;
    private static Object resultIdObject;
    private static JsonObject resultIdJson;
    private static Object resultNameObject;
    private static JsonObject resultNameJson;

    @BeforeClass
    public static void setupResults() {
        resultObject = TmdbSchema.executeQuery("{keywordList(filmId: \"123\"){id name}}");
        resultJson = TestUtil.extractData(resultObject);

        resultIdObject = TmdbSchema.executeQuery("{keywordList(filmId: \"123\"){id}}");
        resultIdJson = TestUtil.extractData(resultIdObject);

        resultNameObject = TmdbSchema.executeQuery("{keywordList(filmId: \"123\"){name}}");
        resultNameJson = TestUtil.extractData(resultNameObject);
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull();
    }

    @Test
    public void correctQueryShouldReturnJsonArrayOfCorrectLength(){
        assertThat(resultJson.get("keywordList").getAsJsonArray().size()).isEqualTo(4);
    }

    @Test
    public void correctQueryShouldReturnListWithCorrectKeywords(){
        assertThat(resultJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("elves"));

        assertThat(resultJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(603));
    }

    @Test
    public void correctIdQueryShouldReturnListWithJustIds(){
        assertThat(resultIdJson.get("keywordList").getAsJsonArray().get(3).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(10364));

        assertThat(resultIdJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isNull();

    }

    @Test
    public void correctNameQueryReturnsListWithJustNames() {
        assertThat(resultNameJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isNull();

        assertThat(resultNameJson.get("keywordList").getAsJsonArray().get(2).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("hobbit"));
    }
}
