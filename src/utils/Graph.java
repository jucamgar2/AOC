package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

