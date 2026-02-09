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

    public List<List<Node>> findAllPathsFromTo(Node from, Node to){
        List<List<Node>> allPaths = new ArrayList<>();
        List<Node> visited = new ArrayList<>();
        List<Node> path = new ArrayList<>();
        dfs(from, to, visited, path, allPaths);
        return allPaths;
    }

    private void dfs(Node current, Node end, List<Node> visited, List<Node> path, List<List<Node>> allPaths) {
        visited.add(current);
        path.add(current);
        if(current.equals(end)){
            allPaths.add(new ArrayList<>(path));
        }else{
            for(Edge edge: current.getEdges()){
                Node neighbor = edge.getTo();
                if(!visited.contains(neighbor)){
                    dfs(neighbor, end, visited, path, allPaths);
                }
            }
        }
        path.remove(current);
        visited.remove(current);
    }

    public int countPaths(String start, String end) {
        if (!nodes.containsKey(start)) {
            return 0;
        }
        Map<String, Integer> cache = new HashMap<>();
        return dfsCountPaths(start, end, cache);
    }

    private int dfsCountPaths(String curr, String end, Map<String, Integer> cache) {
        if (curr.equals(end)) {
            return 1;
        }
        if (cache.containsKey(curr)) {
            return cache.get(curr);
        }
        Node currentNode = nodes.get(curr);
        int count = 0;
        if (currentNode != null) {
            for (Edge edge : currentNode.getEdges()) {
                count += dfsCountPaths(edge.getTo().getValue(), end, cache);
            }
        }
        cache.put(curr, count);
        return count;
    }

}