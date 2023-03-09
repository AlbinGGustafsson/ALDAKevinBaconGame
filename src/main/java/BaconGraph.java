import java.util.*;

public class BaconGraph {

    private Map<String, Set<String>> graphMap;
    private Map<String, String> visited;
    private Queue<String> queue;

    public BaconGraph(Map<String, Set<String>> graphMap) {
        this.graphMap = graphMap;
    }

    /**
     * Genererar den kortaste vägen mellan två noder i grafen med hjälp av bredden först sökning.
     * Första sökningen bygger upp vägar till startnoden tills den hittar slutnoden.
     * Dessa sparas i en map visited som kopplar ihop noder med dess föregående nod. Följande sökningar kollar
     * om slutnoden finns som nyckel i visited och skippar då hela sökningen och kan generera vägen direkt. Annars sker
     * en ny sökning.
     *
     * @param startNode startnoden
     * @param endNode   slutnoden
     * @return En lista med noder för den kortaste vägen, tom lista om ingen väg fanns.
     */
    public List<String> shortestPath(String startNode, String endNode) {

        //Har det redan gjorts en sökning eller om vi inte vill ha en ny startnod så hoppar vi över detta och fortsätter sökningen från den tidigare sökningen
        if (visited == null || !visited.containsKey(startNode)) {
            visited = new HashMap<>();
            visited.put(startNode, null);
            queue = new LinkedList<>();
            queue.add(startNode);
        }

        //Om vi redan hittat en väg till slutnoden tidigare
        if (visited.containsKey(endNode)) {
            return gatherPath(endNode);
        }

        while (!queue.isEmpty()) {
            String current = queue.poll();
            //Lägger till grannar innan man kollar om det är rätt för att det ska bli korrekt i en eventuell nästa sökning
            for (String neighbor : graphMap.get(current)) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
            if (current.equals(endNode)) {
                return gatherPath(endNode);
            }
        }
        return Collections.emptyList();
    }

    /**
     * Traverserar visited baklänges från den eftersökta noden genom dess föregående noder
     * och lägger vägen i path, tills den når startnoden.
     *
     * @param endNode slutnoden
     * @return Listan path med noder för den kortaste vägen.
     */
    public List<String> gatherPath(String endNode) {
        LinkedList<String> path = new LinkedList<>();
        String node = endNode;
        while (node != null) {
            path.offerFirst(node);
            node = visited.get(node);
        }
        return path;
    }
}
