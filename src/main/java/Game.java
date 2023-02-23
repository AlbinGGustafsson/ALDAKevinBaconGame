import java.util.*;

public class Game {
    public static final String KEVIN_BACON = "<a>Bacon, Kevin (I)";
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

    private void startUp() {
        System.out.println("Starting to load the graph file");
        GraphLoader graphLoader = new GraphLoader();
        long startTime = System.currentTimeMillis();
        Map<String, Set<String>> graphMap = graphLoader.loadGraphFile("src/main/resources/moviedata.txt");
        long endTime = System.currentTimeMillis();
        System.out.printf("It took %.3f seconds to load the graph file.%n", (endTime - startTime) / 1000.0);
        System.out.printf("The program had %.3f seconds to spare.%n%n", 120 - (endTime - startTime) / 1000.0);

        baconGraph = new BaconGraph(graphMap);
    }

    private void printInstructions() {
        System.out.println("Enter exit to close the program.");
        System.out.println("Enter the name of an actor to see it's Kevin Bacon score.");
    }

    private void inputLoop() {
        String input;
        do {
            System.out.print("Name?> ");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit")) {
                long startTime = System.currentTimeMillis();
                List<String> path = baconGraph.shortestPath(KEVIN_BACON, "<a>" + input);
                long endTime = System.currentTimeMillis();

                if (path.isEmpty()) {
                    System.out.println("Could not find the actor");
                    continue;
                }
                printResult(path, input);
                System.out.printf("It took %.3f seconds to find the path.%n", (endTime - startTime) / 1000.0);
            }
        } while (!input.equalsIgnoreCase("exit"));
    }

    private void printResult(List<String> path, String input) {
        System.out.printf("Kevin Bacon score for '%s' is: %d%n", input, path.size() / 2);

        Iterator<String> iterator = path.listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().substring(3));
            if (iterator.hasNext()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
