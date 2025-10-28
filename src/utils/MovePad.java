package utils;

public class MovePad {

    private Graph movePad;

    public Graph getMovePad(){
        return this.movePad;
    }

    public MovePad(){
        Graph graph = new Graph();
        graph.addEdge("A", "^", "<");
        graph.addEdge("^", "A", ">");

        graph.addEdge("A", ">", "v");
        graph.addEdge(">", "A", "^");

        graph.addEdge("^", "v", "v");
        graph.addEdge("v", "^", "^");

        graph.addEdge(">", "v", "<");
        graph.addEdge("v", ">", ">");
        
        graph.addEdge("v", "<", "<");
        graph.addEdge("<", "v", ">");
        this.movePad = graph;
    }
    
}
