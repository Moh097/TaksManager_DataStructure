class Edge {
    Task source, destination;
    int weight;

    public Edge(Task source, Task destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge from " + source.getName() + " to " + destination.getName() + " with weight: " + weight;
    }
}
