package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class PrettyJsonPrinting {
    public static String getPrettyJsonString(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(uglyJson);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
