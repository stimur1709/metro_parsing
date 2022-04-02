package metro;

import java.util.TreeSet;

public class Connections implements Comparable<Connections> {
    private final TreeSet<Station> stationConnect;

    public Connections() {
        stationConnect = new TreeSet<>();
    }

    public void addStation(Station station) {
        stationConnect.add(station);
    }

    public TreeSet<Station> getStationsCon() {
        return stationConnect;
    }

    @Override
    public int compareTo(Connections o) {
        if (stationConnect.size() == o.getStationsCon().size()) {
            if (stationConnect.containsAll(o.getStationsCon())) {
                return 0;
            } else {
                return -1;
            }
        }
        if (stationConnect.size() < o.getStationsCon().size()) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        return compareTo((Connections) o) == 0;
    }
}
