import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSONReader {
    public void getNumberOfStations(String path) {
        JSONParser parser = new JSONParser();
        try {
            Object objects = parser.parse(new FileReader(path));
            JSONObject metroObj = (JSONObject) objects;
            JSONObject stationsObj = (JSONObject) metroObj.get("stations");
            stationsObj.keySet().forEach(k -> {
                JSONArray stationsArray = (JSONArray) stationsObj.get(k);
                System.out.println("На Линии " + k + " " + stationsArray.size() + " станций");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getNumberOfConnection(String path) {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(path));
            JSONObject metroObj = (JSONObject) object;
            JSONArray connectionArray = (JSONArray) metroObj.get("connections");
            System.out.println("Количество переходов в метро " + connectionArray.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
