package aoc2024.day21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;


public class Graph {
    private Map<String, Node> nodes = new HashMap<>();

    public Node addNode(String value) {
        return nodes.computeIfAbsent(value, Node::new);
    }

    public void addEdge(String from, String to, String label) {
        Node n1 = addNode(from);
        Node n2 = addNode(to);
        n1.addEdge(n2, label);
    }

    public Node getNode(String value) {
        return nodes.get(value);
    }

    public void printGraph() {
        for (Node node : nodes.values()) {
            for (Edge edge : node.getEdges()) {
                System.out.println(edge);
            }
        }
    }

    public Map<NodePair, List<String>> buildPathsForNodePair(){
        Map<NodePair, List<String>> paths = new HashMap<>();
        for(Node from: nodes.values()){
            for(Node to: nodes.values()){
                NodePair nodePair = new NodePair(from, to);
                if(from.equals(to)){
                    if(!paths.containsKey(nodePair)){
                        paths.put(nodePair, List.of("A"));
                    }
                }else if(!paths.containsKey(nodePair)){
                    List<String> shortestPaths = findShortestPathsForNodePair(nodePair);
                    paths.put(nodePair, shortestPaths);
                }
            }
        }
        return paths;
    }

    public List<String> findShortestPathsForNodePair(NodePair nodePair){
        Node startNode = nodePair.getFrom();
        Node endNode = nodePair.getTo();
        if (startNode == null || endNode == null) return Collections.emptyList();
        Queue<List<Edge>> queue = new LinkedList<>();
        List<String> shortestPaths = new ArrayList<>();
        int minLength = Integer.MAX_VALUE;
        for (Edge edge : startNode.getEdges()) {
            List<Edge> path = new ArrayList<>();
            path.add(edge);
            queue.add(path);
        }
        while (!queue.isEmpty()) {
            List<Edge> path = queue.poll();
            Edge last = path.get(path.size() - 1);
            if (path.size() > minLength) continue;
            if (last.getTo().equals(endNode)) {
                if (path.size() < minLength) {
                    minLength = path.size();
                    shortestPaths.clear();
                }
                String pathString = path.stream()
                                        .map(Edge::getLabel)
                                        .collect(Collectors.joining("")); 
                pathString+="A";
                shortestPaths.add(pathString);
            } else {
                for (Edge edge : last.getTo().getEdges()) {
                    boolean visited = path.stream()
                            .anyMatch(e -> e.getFrom().equals(edge.getTo()) || e.getTo().equals(edge.getTo()));
                    if (visited) continue;
                    List<Edge> newPath = new ArrayList<>(path);
                    newPath.add(edge);
                    queue.add(newPath);
                }
            }
        }
        return shortestPaths;
    }

}

class Node {
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

class Edge {
    private Node from;
    private Node to;
    private String label;

    public Edge(Node from, Node to, String label) {
        this.from = from;
        this.to = to;
        this.label = label;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return from + " --(" + label + ")--> " + to;
    }
}

class NodePair{
    private Node from;
    private Node to;
    
    public NodePair(Node from, Node to){
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodePair pairNode = (NodePair) o;
        return this.from.getValue().equals(pairNode.getFrom().getValue()) &&
                 this.to.getValue().equals(pairNode.getTo().getValue());
    }

    @Override
    public int hashCode(){
        return Objects.hash(from.getValue(), to.getValue());
    }
}
