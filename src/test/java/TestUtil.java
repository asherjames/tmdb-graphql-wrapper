import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TestUtil {

    private static Gson gson = new Gson();

    public static JsonObject extractData(Object input) {
        return gson.toJsonTree(input).getAsJsonObject().get("data").getAsJsonObject();
    }
}
