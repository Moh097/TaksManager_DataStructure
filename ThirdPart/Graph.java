import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<Task, Integer> taskIndexMap;
    private int[][] adjacencyMatrix;
    private int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyMatrix = new int[numVertices][numVertices];
            for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
        }
        this.taskIndexMap = new HashMap<>();
    }

    public void addTask(Task task) {
        int index = taskIndexMap.size();
        taskIndexMap.put(task, index);
    }

    public void addEdge(Task source, Task destination, int weight) {
        int sourceIndex = taskIndexMap.get(source);
        int destinationIndex = taskIndexMap.get(destination);
        adjacencyMatrix[sourceIndex][destinationIndex] = weight;
    }

    public void printGraph() {
        for (Map.Entry<Task, Integer> entry : taskIndexMap.entrySet()) {
            Task task = entry.getKey();
            int i = entry.getValue();
            System.out.print(task.getName() + " -> ");
            boolean first = true;
            for (int j = 0; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] != Integer.MAX_VALUE) {
                    if (!first) {
                        System.out.print("\t");
                    }
                    Task destination = getTaskByIndex(j);
                    System.out.print("Edge from " + task.getName() + " to " + destination.getName() + " with weight: " + adjacencyMatrix[i][j]);
                    first = false;
                }
            }
            System.out.println();
        }
    }

    private Task getTaskByIndex(int index) {
        for (Map.Entry<Task, Integer> entry : taskIndexMap.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Dijkstra's Algorithm
    public void dijkstra(Task startTask) {
        int startVertex = taskIndexMap.get(startTask);
        boolean[] visited = new boolean[numVertices];
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int minVertex = findMinVertex(distances, visited);
            visited[minVertex] = true;

            for (int j = 0; j < numVertices; j++) {
                if (!visited[j] && adjacencyMatrix[minVertex][j] != Integer.MAX_VALUE) {
                    int newDist = distances[minVertex] + adjacencyMatrix[minVertex][j];
                    if (newDist < distances[j]) {
                        distances[j] = newDist;
                    }
                }
            }
        }

        printDistances(distances, "Dijkstra", startTask);
    }

    private int findMinVertex(int[] distances, boolean[] visited) {
        int minVertex = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minVertex = i;
                minDistance = distances[i];
            }
        }
        return minVertex;
    }

    public void bellmanFord(Task startTask) {
        int startVertex = taskIndexMap.get(startTask);
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            for (int u = 0; u < numVertices; u++) {
                for (int v = 0; v < numVertices; v++) {
                    if (adjacencyMatrix[u][v] != Integer.MAX_VALUE) {
                        if (distances[u] != Integer.MAX_VALUE && distances[u] + adjacencyMatrix[u][v] < distances[v]) {
                            distances[v] = distances[u] + adjacencyMatrix[u][v];
                        }
                    }
                }
            }
        }

        for (int u = 0; u < numVertices; u++) {
            for (int v = 0; v < numVertices; v++) {
                if (adjacencyMatrix[u][v] != Integer.MAX_VALUE) {
                    if (distances[u] != Integer.MAX_VALUE && distances[u] + adjacencyMatrix[u][v] < distances[v]) {
                        System.out.println("Graph contains a negative-weight cycle");
                        return;
                    }
                }
            }
        }

        printDistances(distances, "Bellman-Ford", startTask);
    }

    private void printDistances(int[] distances, String algorithm, Task startTask) {
        System.out.println("Shortest path from " + startTask.getName() + " (" + algorithm + "):");
        for (Map.Entry<Task, Integer> entry : taskIndexMap.entrySet()) {
            Task task = entry.getKey();
            int i = entry.getValue();
            System.out.println("To " + task.getName() + ": " + (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        
        Task task1 = new Task("Task1", "01", "07", "2024", "urgent", "Work");
        Task task2 = new Task("Task2", "02", "07", "2024", "normal", "Home");
        Task task3 = new Task("Task3", "03", "07", "2024", "normal", "Work");
        Task task4 = new Task("Task4", "04", "07", "2024", "normal", "Home");
        Task task5 = new Task("Task5", "05", "07", "2024", "urgent", "Work");
        Task task6 = new Task("Task6", "06", "07", "2024", "urgent", "Home");
        Task task7 = new Task("Task7", "07", "07", "2024", "normal", "Work");
        Task task8 = new Task("Task8", "08", "07", "2024", "urgent", "Home");

        graph.addTask(task1);
        graph.addTask(task2);
        graph.addTask(task3);
        graph.addTask(task4);
        graph.addTask(task5);
        graph.addTask(task6);
        graph.addTask(task7);
        graph.addTask(task8);

        graph.addEdge(task1, task2, 2);
        graph.addEdge(task1, task3, 4);
        graph.addEdge(task1, task4, 8);
        graph.addEdge(task2, task3, 1);
        graph.addEdge(task2, task4, 7);
        graph.addEdge(task2, task5, 6);
        graph.addEdge(task3, task5, 3);
        graph.addEdge(task3, task6, 4);
        graph.addEdge(task4, task6, 1);
        graph.addEdge(task4, task7, 2);
        graph.addEdge(task5, task6, 5);
        graph.addEdge(task5, task7, 2);
        graph.addEdge(task5, task8, 4);
        graph.addEdge(task6, task8, 1);
        graph.addEdge(task7, task8, 3);

        System.out.println("Graph Representation:");
        graph.printGraph();

        System.out.println("\nDijkstra's Algorithm:");
        graph.dijkstra(task1);

        System.out.println("\nBellman-Ford Algorithm:");
        graph.bellmanFord(task1);
    }
}
