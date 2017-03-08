import ash.java.graphql.schema.KeywordObjectTypes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class KeywordQueryTest {

    private static Object resultObject;
    private static JsonObject resultJson;

    @BeforeClass
    public static void setupResults() {
        Gson gson = new Gson();

        resultObject = KeywordObjectTypes.executeKeywordQuery("{keywordList(filmId: \"123\"){id name}}");
        resultJson = gson.toJsonTree(resultObject).getAsJsonObject();
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull();
    }

    @Test
    public void correctQueryShouldReturnJsonArrayOfCorrectLength(){
        assertThat(resultJson.get("keywordList").getAsJsonArray().size()).isEqualTo(4);
    }
}
