import metro.ConnectionStation;
import metro.Connections;
import metro.Line;
import metro.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Parser {
    private Document document;
    private List<Line> lines;
    private ConnectionStation connectionStation;

    public Parser(String url) {
        try {
            document = Jsoup.connect(url).maxBodySize(0).get();
            lines = new ArrayList<>();
            connectionStation = new ConnectionStation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConnectionStation getConnectionStation() {
        return connectionStation;
    }

    public JSONArray parseLine() {
        Elements lineList = document.select(".js-metro-line");
        JSONArray linesArray = new JSONArray();
        for (Element element : lineList) {
            Line line = new Line(element.attr("data-line"), element.ownText());
            lines.add(line);
            linesArray.add(line.getJsonLine());
        }
        return linesArray;
    }

    public JSONObject parseStation() {
        Elements stationLineList = document.select(".js-metro-stations");
        JSONObject stationObj = new JSONObject();
        for (Element stationElement : stationLineList) {
            JSONArray stationArray = new JSONArray();
            Elements nameStation = stationElement.getElementsByClass("name");
            for (Element station : nameStation) {
                stationArray.add(station.text());
                stationObj.put(stationElement.attr("data-line"), stationArray);
                connectionStation.addStation(new Station(station.text(), stationElement.attr("data-line")));
            }
        }
        return stationObj;
    }

    public void parseConnection() {
        Elements dataList = document.select(".js-metro-stations");
        for (Element element : dataList) {
            Elements connectionList = element.select("a:has(span[title])");
            for (Element connectionElement : connectionList) {
                String stationIn = connectionElement.text();
                stationIn = stationIn.substring(stationIn.indexOf(" ") + 1).trim();
                Elements connection = connectionElement.select(".t-icon-metroln");
                Connections connections = new Connections();
                connections.addStation(new Station(stationIn, element.attr("data-line")));
                for (Element e : connection) {
                    String stationOut = e.attr("title");
                    String indexOut = e.attr("class");
                    stationOut = stationOut.substring(stationOut.indexOf("«") + 1, stationOut.lastIndexOf("»")).trim();
                    connections.addStation(new Station(stationOut, indexOut.substring(indexOut.lastIndexOf("-") + 1).trim()));
                }
                connectionStation.addConnection(connections);
            }
        }
    }

    public JSONArray writeConnection(TreeSet<Connections> connections) {
        JSONArray connectionArray = new JSONArray();
        for (Connections connection : connections) {
            JSONArray connectionObjArray = new JSONArray();
            for (Station station : connection.getStationsCon()) {
                JSONObject connectionObj = new JSONObject();
                connectionObj.put("line", station.getNumberLine());
                connectionObj.put("station", station.getName());
                connectionObjArray.add(connectionObj);
            }
            connectionArray.add(connectionObjArray);
        }
        return connectionArray;
    }
}
