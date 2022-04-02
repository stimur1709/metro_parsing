package metro;

public class Station implements Comparable<Station> {
    private final String numberLine;
    private final String name;

    public Station(String name, String numberLine) {
        this.name = name;
        this.numberLine = numberLine;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Station station) {
        int lineComparison = numberLine.compareTo(station.getNumberLine());
        if (lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return compareTo((Station) o) == 0;
    }
}
