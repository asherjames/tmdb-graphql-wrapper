package ash.java.graphql.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TestUtil {

    private static Gson gson = new Gson();

    public static JsonObject extractData(Object input) {
        return gson.toJsonTree(input).getAsJsonObject().get("data").getAsJsonObject();
    }

    public static JsonArray extractError(Object input) {
        return gson.toJsonTree(input).getAsJsonObject().get("errors").getAsJsonArray();
    }
}