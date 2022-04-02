import metro.Connections;
import metro.Metro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        String separator = File.separator;
        String path = String.format("src%smain%sresources%smetro.json", separator, separator, separator);
        Parser parser = new Parser("https://www.moscowmap.ru/metro.html#lines");

        JSONArray linesArray = parser.parseLine();

        JSONObject stationObject = parser.parseStation();

        parser.parseConnection();
        TreeSet<Connections> connections = parser.getConnectionStation().getConnections();
        JSONArray connectionsArray = parser.writeConnection(connections);
        Metro metro = new Metro(stationObject, linesArray, connectionsArray);

        JSONWriter jsonWriter = new JSONWriter();
        jsonWriter.writerInJSOWFile(metro.getMetroObject(), path);

        JSONReader jsonReader = new JSONReader();
        jsonReader.getNumberOfStations(path);
        jsonReader.getNumberOfConnection(path);
    }
}
