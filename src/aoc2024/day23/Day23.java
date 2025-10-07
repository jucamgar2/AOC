package aoc2024.day23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day23 {

    private Map<String, Set<String>> connections;
    
    public Map<String,Set<String>> getConnections(){
        return this.connections;
    }

    public Day23(Map<String, Set<String>> connections){
        this.connections = connections;
    }

    public static Day23 getDay23Data(){
        Map<String, Set<String>> connections = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay23.txt"))) {
            br.lines().forEach(line->{
                String[] computers = line.split("-");
                String comp1 = computers[0];
                String comp2 = computers[1];
                addConnection(connections, comp1, comp2);
                addConnection(connections, comp2, comp1);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day23(connections);
    }

    public static void addConnection(Map<String, Set<String>> connections, String comp1, String comp2){
        if(connections.containsKey(comp1)){
            connections.get(comp1).add(comp2);
        }else{
            Set<String> conn = new HashSet<>();
            conn.add(comp2);
            connections.put(comp1, conn);
        }
    }

}
