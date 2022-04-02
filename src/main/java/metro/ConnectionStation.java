package metro;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ConnectionStation {

    private final List<Station> stations;
    private final TreeSet<Connections> connections;

    public ConnectionStation() {
        stations = new ArrayList<>();
        connections = new TreeSet<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addConnection(Connections connection) {
        if (!containsStation(connection)) {
            connections.add(connection);
        }
    }

    public TreeSet<Connections> getConnections() {
        return connections;
    }

    private boolean containsStation(Connections connectionStation) {
        for (Connections connection : connections) {
            for (Station station : connection.getStationsCon()) {
                for (Station stationIn : connectionStation.getStationsCon()) {
                    if (station.getName().equals(stationIn.getName()) && station.getNumberLine().equals(stationIn.getNumberLine())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
