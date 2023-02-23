import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphLoader {
    public Map<String, Set<String>> loadGraphFile(String filePath) {

        Map<String, Set<String>> graph = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String actor = null;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(1) == 'a') {
                    actor = line;
                    graph.putIfAbsent(actor, new HashSet<>());
                } else {
                    graph.putIfAbsent(line, new HashSet<>());
                    graph.get(line).add(actor);
                    graph.get(actor).add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

}
