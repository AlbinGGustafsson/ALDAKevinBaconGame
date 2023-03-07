import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphLoader {

    /**
     * Skapar en graf utifrån det angivna dokumentet.
     * Läser igenom dokumentet en rad i taget. Läser av flaggan som står först som avgör ifall raden representerar
     * en skådespelare eller film. Lägger in dessa som noder i grafen.
     *
     * @param filePath   Sökväg till dokumentet med skådespelare och filmer.
     *
     * @return Den genererade grafen skapad från dokumentet.
     */
    public BaconGraph loadGraphFile(String filePath) {

        Map<String, Set<String>> graph = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String actor = null;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(1) == 'a') {
                    actor = line;
                    graph.put(actor, new HashSet<>());
                } else {
                    graph.putIfAbsent(line, new HashSet<>());
                    graph.get(line).add(actor);
                    graph.get(actor).add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaconGraph(graph);
    }
}
