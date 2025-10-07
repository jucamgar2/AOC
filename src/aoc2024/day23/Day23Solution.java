package aoc2024.day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day23Solution {
    
    public static void day23Solution(){
        Day23 input = Day23.getDay23Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day23 input) {
        System.out.println("SOLUTION: " + getTriConnectionWithT(input));
    }

    private static Integer getTriConnectionWithT(Day23 input) {
        Set<Set<String>> triConnections = getTriConnections(input);
        return triConnections.stream().mapToInt(triConnection->containsT(triConnection)?1:0).sum();
    }

    private static boolean containsT(Set<String> triConnection) {
        return triConnection.stream().anyMatch(computer->computer.startsWith("t"));
    }

    private static Set<Set<String>> getTriConnections(Day23 input) {
        Set<Set<String>> triConnections = new HashSet<>();
        Map<String, Set<String>> connections = input.getConnections();
        connections.keySet().stream().forEach(computer ->generateTriConnections(computer, connections, triConnections));
        return triConnections;
    }

    private static void generateTriConnections(String computer, Map<String, Set<String>> connections, Set<Set<String>> triConnections){
        List<String> compConnection = new ArrayList<>(connections.get(computer));
        for(int i = 0; i<compConnection.size();i++){
            for(int j =i; j<compConnection.size();j++){
                String comp1 = compConnection.get(i);
                String comp2 = compConnection.get(j);
                if(connections.get(comp1).contains(comp2)){
                    Set<String> triConnection = new HashSet<>();
                    triConnection.add(computer);
                    triConnection.add(comp1);
                    triConnection.add(comp2);
                    triConnections.add(triConnection);
                }
            }
        }
    }

    private static void part2Solution(Day23 input) {
        System.out.println("SOLUTION: " + getLanPartyPassword(input));
    }

    private static String getLanPartyPassword(Day23 input) {
        List<String> lanParty = getLanParty(input);
        if(lanParty==null){
            lanParty = getLanPartyCliques(input);
        }
        Collections.sort(lanParty);
        return lanParty.toString().trim().replace("[", "").replace("]", "").replace(" ", "");
    }

    private static List<String> getLanParty(Day23 input){
        Set<Set<String>> visitedNetworks = new HashSet<Set<String>>();
        Map<String, Set<String>> connections = input.getConnections();
        for(String node: connections.keySet()){
            Set<String> initialNetwork = new HashSet<>();
            initialNetwork.add(node);
            search(node, initialNetwork, visitedNetworks, connections);
        }
        Set<String> largestNetwork = null;
        for (Set<String> net : visitedNetworks) {
            if (largestNetwork == null || net.size() > largestNetwork.size()) {
                largestNetwork = net;
            }
        }
        return new ArrayList<>(largestNetwork);
    }

    private static void search(String node, Set<String> network, Set<Set<String>> visitedNetworks, Map<String, Set<String>> connections) {
        List<String> sorted = new ArrayList<String>(network);
        Collections.sort(sorted);
        Set<String> key = new LinkedHashSet<String>(sorted);
        if (visitedNetworks.contains(key)) {
            return;
        }
        visitedNetworks.add(new LinkedHashSet<String>(network));

        for (String neighbor : connections.get(node)) {
            if (network.contains(neighbor)) continue;

            boolean connectedToAll = true;
            for (String other : network) {
                if (!connections.get(other).contains(neighbor)) {
                    connectedToAll = false;
                    break;
                }
            }

            if (connectedToAll) {
                Set<String> newNetwork = new HashSet<String>(network);
                newNetwork.add(neighbor);
                search(neighbor, newNetwork, visitedNetworks, connections);
            }
        }
    }

    /**
     * Never finished method to be finish in the future
     * @param input
     * @return
     */
    private static List<String> getLanPartyCliques(Day23 input) {
        Map<String, Set<String>> connections = input.getConnections();
        Set<Set<String>> cliques = getTriConnections(input);
        while(cliques.size()>1){
            System.out.println(cliques.size());
            List<Set<String>> cliqueList = new ArrayList<>(cliques);
            Set<Set<String>> newCliques = new HashSet<>();
            for(int i = 0; i<cliques.size();i++){
                for(int j=i+1; j<cliques.size(); j++){
                    Set<String> clique1 = cliqueList.get(i);
                    Set<String> clique2 = cliqueList.get(j);
                    List<String> diff = Stream.concat(clique1.stream(), clique2.stream())
                         .filter(e -> !(clique1.contains(e) && clique2.contains(e)))
                         .collect(Collectors.toList());
                    if(clique1.size() == clique2.size() && diff.size()==2){
                        String comp1 = diff.get(0);
                        String comp2 = diff.get(1);
                        if(connections.get(comp1).contains(comp2)){
                            Set<String> newClique = new TreeSet<>(clique1); 
                            newClique.addAll(clique2);
                            newCliques.add(newClique);
                        }
                    }
                }
            }
            cliques = newCliques;
        } 
        List<Set<String>> cliqueList = new ArrayList<>(cliques);
        return new ArrayList<>(cliqueList.get(0));
    }
}
