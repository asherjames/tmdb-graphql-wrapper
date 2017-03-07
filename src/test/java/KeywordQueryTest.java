import ash.java.graphql.schema.KeywordObjectTypes;
import com.google.gson.Gson;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class KeywordQueryTest {

    private static Object result;

    @BeforeClass
    public static void setupResults() {
        Gson gson = new Gson();

//        result = KeywordObjectTypes.executeKeywordQuery("{keywords(id: \"123\"){id name}}");
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
//        assertThat(result).isNotNull();

        Object result = KeywordObjectTypes.executeKeywordQuery("{keywordList(filmId: \"123\"){id name}}");

        assertThat(result).isNotNull();
    }
}
