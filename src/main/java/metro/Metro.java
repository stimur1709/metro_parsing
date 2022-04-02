package metro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Metro {
    private final JSONObject metroObject;

    public Metro(JSONObject stationsObject, JSONArray linesArray, JSONArray connectionsArray) {
        metroObject = new JSONObject();
        metroObject.put("stations", stationsObject);
        metroObject.put("lines", linesArray);
        metroObject.put("connections", connectionsArray);
    }

    public JSONObject getMetroObject() {
        return metroObject;
    }
}
