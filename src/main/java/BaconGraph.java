import java.util.*;

public class BaconGraph {

    private Map<String, Set<String>> graphMap;
    private Map<String, String> visited = new HashMap<>();

    public BaconGraph(Map<String, Set<String>> graphMap) {
        this.graphMap = graphMap;
    }

    /**
     * Finds the shortest path between two nodes in the graph using Breadth-First Search.
     *
     * @param startNode the starting node
     * @param endNode   the ending node
     * @return a list of nodes in the shortest path from startNode to endNode
     */
    public List<String> shortestPath(String startNode, String endNode) {
        // create a map to keep track of visited nodes and their parents

        if (visited.containsKey(endNode)) {
            return gatherPath(endNode);
        }

        LinkedList<String> path = new LinkedList<>();

        visited.put(startNode, null);

        // create a queue for BFS
        Queue<String> queue = new LinkedList<>();
        queue.add(startNode);

        // BFS algorithm
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(endNode)) {
                // found the end node, construct the path and return it
                return gatherPath(endNode);
            }
            for (String neighbor : graphMap.get(current)) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        // no path found between startNode and endNode
        return path;
    }

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
