import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {

    public void writerInJSOWFile(JSONObject object, String path) {

        try (FileWriter file = new FileWriter(path)) {
            file.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
