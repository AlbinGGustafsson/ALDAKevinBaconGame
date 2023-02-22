import java.util.*;

public class Game {
    public static final String KEVIN_BACON = "Bacon, Kevin (I)";
    private Scanner scanner = new Scanner(System.in);
    private BaconGraph baconGraph;

    public static void main(String[] args) {
        new Game().run();
    }
    private void run() {
        startUp();
        printInstructions();
        inputLoop();
        System.exit(0);
    }

    private void printInstructions() {
        System.out.println("Enter a name of a actor to see it's Kevin Bacon score.");
        System.out.println("Enter exit to close the program.");
    }

    private void startUp() {
        System.out.println("Starting to load the graph file");
        GraphLoader graphLoader = new GraphLoader();
        long startTime = System.currentTimeMillis();
        Map<String, Set<String>> graphMap = graphLoader.loadGraphFile("src/main/resources/moviedata.txt");
        long endTime = System.currentTimeMillis();
        System.out.printf("It took %d.%d seconds to load the graph file.%n", (endTime - startTime) / 1000, (endTime - startTime) % 1000);
        System.out.printf("The program had %d.%d seconds to spare.%n%n", 120 - (endTime - startTime) / 1000, 1000 - (endTime - startTime) % 1000);

        baconGraph = new BaconGraph(graphMap);
    }

    private void inputLoop() {
        String input;
        do {
            System.out.print("Name ?> ");
            input = scanner.nextLine();
            long startTime = System.currentTimeMillis();

            List<String> path = baconGraph.shortestPath(KEVIN_BACON, input);
            if (path.isEmpty()) {
                System.out.println("Could not find the actor");
                continue;
            }

            printResult(path, input);
            long endTime = System.currentTimeMillis();
            System.out.printf("It took %d.%d seconds to find the path.%n", (endTime - startTime) / 1000, (endTime - startTime) % 1000);
        } while (!input.equalsIgnoreCase("exit"));
    }

    private void printResult(List<String> path, String input) {
        System.out.printf("Kevin Bacon Score for '%s' is: %d%n", input, path.size() / 2);

        Iterator<String> iterator = path.listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(" -> ");
            }
        }

//        for (int i = 0; i < path.size(); i++) {
//            System.out.print(path.get(i));
//            if (i != path.size() - 1) {
//                System.out.print(" -> ");
//            }
//        }
        System.out.println();

    }

}
