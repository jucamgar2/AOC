package utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String value;
    private List<Edge> edges = new ArrayList<>();

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void addEdge(Node destination, String label) {
        edges.add(new Edge(this, destination, label));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return value;
    }
}
