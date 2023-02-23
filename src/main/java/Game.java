import java.util.*;

public class Game {
    public static final String KEVIN_BACON = "<a>Bacon, Kevin (I)";
    private Scanner scanner = new Scanner(System.in);
    private BaconGraph baconGraph;

    public static void main(String[] args) {
        new Game().run();
    }

    /**
     * Kör programmet.
     */
    private void run() {
        startUp();
        printInstructions();
        inputLoop();
        System.exit(0);
    }

    /**
     * Skapar en graf utifrån en graphLoader.
     */
    private void startUp() {
        System.out.println("Starting to load the graph file");
        GraphLoader graphLoader = new GraphLoader();
        long startTime = System.currentTimeMillis();
        baconGraph = graphLoader.loadGraphFile("src/main/resources/moviedata.txt");
        long endTime = System.currentTimeMillis();
        System.out.printf("It took %.3f seconds to load the graph file.%n", (endTime - startTime) / 1000.0);
        System.out.printf("The program had %.3f seconds to spare.%n%n", 120 - (endTime - startTime) / 1000.0);
    }

    /**
     * Skriver ut instruktionerna för programmet.
     */
    private void printInstructions() {
        System.out.println("Enter exit to close the program.");
        System.out.println("Enter the name of an actor to see it's Kevin Bacon score.");
    }

    /**
     * Läser in namn på skådespelare från användaren. Anropar shortestPath med den angivna skådespelaren.
     * Finns det ingen väg ges ett meddelande och användaren blir frågad på nytt. Om en väg hittas anropas
     * printResult. Loopen avslutas med "exit".
     */
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

    /**
     * Formaterar och skriver ut den kortaste vägen.
     *
     * @param path  Lista med den kortaste vägen.
     * @param input Namn på den sökta skådespelaren.
     */
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
