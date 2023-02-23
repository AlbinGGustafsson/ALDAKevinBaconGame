import java.util.*;

public class BaconGraph {

    private Map<String, Set<String>> graphMap;
    private Map<String, String> visited = new HashMap<>();
    Queue<String> queue = new LinkedList<>();

    public BaconGraph(Map<String, Set<String>> graphMap) {
        this.graphMap = graphMap;
    }

    /**
     * Genererar den kortaste vägen mellan två noder i grafen med hjälp av bredden först sökning.
     * Första sökningen bygger upp vägar till Kevin Bacon tills den hittar slutnoden.
     * Dessa sparas i en map visited som kopplar ihop noder med dess föregående nod. Följande sökningar kollar först
     * om slutnoden finns som nyckel i visited och skippar då hela sökningen och kan generera vägen direkt. Annars sker
     * en ny sökning.
     *
     * @param startNode startnoden
     * @param endNode   slutnoden
     * @return En lista med noder för den kortaste vägen, tom lista om ingen väg fanns.
     */
    //TODO: Factchecka att första sökningen bara bygger upp delar av alla vägar till Kevin Bacon i grafen.
    public List<String> shortestPath(String startNode, String endNode) {

        if (visited.containsKey(endNode)) {
            return gatherPath(endNode);
        }

        //Har det redan gjorts en sökning så hoppar vi över detta och fortsätter sökningen från den första
        if (!visited.containsKey(startNode)) {
            visited.put(startNode, null);
            queue = new LinkedList<>();
            queue.add(startNode);
        }

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(endNode)) {
                return gatherPath(endNode);
            }
            for (String neighbor : graphMap.get(current)) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    /**
     * Traverserar visited baklänges från den eftersökta noden genom dess föregående noder
     * och lägger vägen i path, tills den når Kevin Bacon.
     *
     * @param endNode den eftersökta noden
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
