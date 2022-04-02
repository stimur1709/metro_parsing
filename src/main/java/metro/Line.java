package metro;

import org.json.simple.JSONObject;

public class Line implements Comparable<Line> {
    private JSONObject jsonLine;
    private final String number;
    private final String name;

    public Line(String number, String name) {
        JSONObject jsonLine = new JSONObject();
        jsonLine.put("number", number);
        jsonLine.put("name", name);
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public JSONObject getJsonLine() {
        return jsonLine;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Line line) {
        return number.compareToIgnoreCase(line.getNumber());
    }

    @Override
    public boolean equals(Object o) {
        return compareTo((Line) o) == 0;
    }
}
