package utils;

public class NumericPad {

    private Graph numericPad;

    public Graph getGraph(){
        return this.numericPad;
    }

    public NumericPad(){
        Graph graph = new Graph();
        graph.addEdge("A", "3", "^");
        graph.addEdge("3", "A", "v");
        graph.addEdge("A", "0", "<");
        graph.addEdge("0", "A", ">");
        graph.addEdge("0", "2", "^");
        graph.addEdge("2", "0", "v");
        graph.addEdge("3", "6", "^");
        graph.addEdge("6", "3", "v");
        graph.addEdge("3", "2", "<");
        graph.addEdge("2", "3", ">");
        graph.addEdge("2", "5", "^");
        graph.addEdge("5", "2", "v");
        graph.addEdge("2", "1", "<");
        graph.addEdge("1", "2", ">");
        graph.addEdge("1", "4", "^");
        graph.addEdge("4", "1", "v");
        graph.addEdge("6", "9", "^");
        graph.addEdge("9", "6", "v");
        graph.addEdge("6", "5", "<");
        graph.addEdge("5", "6", ">");
        graph.addEdge("5", "8", "^");
        graph.addEdge("8", "5", "v");
        graph.addEdge("5", "4", "<");
        graph.addEdge("4", "5", ">");
        graph.addEdge("4", "7", "^");
        graph.addEdge("7", "4", "v");
        graph.addEdge("9", "8", "<");
        graph.addEdge("8", "9", ">");
        graph.addEdge("8", "7", "<");
        graph.addEdge("7", "8", ">");
        this.numericPad = graph;
    }
    
}
