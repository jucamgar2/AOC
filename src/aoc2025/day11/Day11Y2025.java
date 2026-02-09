package aoc2025.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import utils.Graph;

public class Day11Y2025 {

    private Graph graph;

    public Graph getGraph(){
        return this.graph;
    }

    public Day11Y2025(Graph graph){
        this.graph = graph;
    }

    public static Day11Y2025 getDay11Data(){
        Graph graph = new Graph();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/Day11Data.txt"))) {
            br.lines().forEach(line->{
                String[] edgedString = line.split(":");
                String from = edgedString[0].trim();
                graph.addNode(from);
                for(String to: edgedString[1].split(" ")){
                    graph.addNode(to.trim());
                    graph.addEdge(from, to, "");
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day11Y2025(graph);
    }
    
}
