package tfip.nus.iss.Day26;

import java.io.Reader;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

    public static JsonObject toJson(String str) {
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

}
