package aoc2023.day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.validation.Validator;

import structure.BaseDay;
import utils.Position;
import utils.PositionBoard;

public class Day23Solution2023 extends BaseDay{

    @Override
    protected Day23Y2023 getInputData() {
        return Day23Y2023.readDay23Data();
    }

    @Override
    public void runDaySolution() {
        Day23Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getLongestHike((Day23Y2023) input));
    }

    private Integer getLongestHike(Day23Y2023 input) {
        PositionBoard map = input.getMap();
        Position start = map.getPositions().stream()
                        .filter(x->x.getI()==0 && x.getValue().equals("."))
                        .findFirst().orElse(null);
        Position end = map.getPositions().stream()
                        .filter(x->x.getI()==map.getMaxI() && x.getValue().equals("."))
                        .findFirst().orElse(null);
        return findLongestPath(start, end, new HashSet<Position>(), map);
    }

    private Integer findLongestPath(Position current, Position end, Set<Position> visited, PositionBoard map) {
        if(current.equals(end)){
            return 0;
        }
        visited.add(current);
        int max = Integer.MIN_VALUE;
        for(Position next : getNeighbors(map, current)){
            if(!visited.contains(next)){
                int distance = 1 + findLongestPath(next, end, visited, map);
                max = Math.max(max, distance);
            }
        }
        visited.remove(current);
        return max;
    }

    private Set<Position> getNeighbors(PositionBoard map, Position current){
        List<String> validPositions = List.of(".", ">", "<", "^", "v");
        if(current.getValue().equals(">")){
            return Set.of(map.getPosition(current.getI(), current.getJ()+1));
        }else if(current.getValue().equals("<")){
            return Set.of(map.getPosition(current.getI(), current.getJ()-1));
        }else if(current.getValue().equals("^")){
            return Set.of(map.getPosition(current.getI()-1, current.getJ()));
        }else if(current.getValue().equals("v")){
            return Set.of(map.getPosition(current.getI()+1, current.getJ()));
        }else{
            return map.getFourNeighbors(current).stream()
                    .filter(pos-> validPositions.contains(pos.getValue()))
                    .collect(Collectors.toSet());
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getLongestHikeClimbing((Day23Y2023) input));
    }

    private Integer getLongestHikeClimbing(Day23Y2023 input) {
        PositionBoard map = input.getMap();
        Position start = map.getPositions().stream()
                .filter(x ->x.getI() == 0 && x.getValue().equals("."))
                .findFirst()
                .orElse(null);
        Position end = map.getPositions().stream()
                .filter(x ->x.getI() == map.getMaxI() && x.getValue().equals("."))
                .findFirst()
                .orElse(null);
        Set<Position> nodes = getNodes(map, start, end);
        Map<Position, List<Edge>> graph = buildGraph(map, nodes);
        return findLongestPathGraph(start, end,new HashSet<>(), graph);
    }

    private Set<Position> getNodes(PositionBoard map, Position start, Position end) {
        Set<Position> nodes = new HashSet<>();
        for (Position position : map.getPositions()) {
            if (position.getValue().equals("#")) {
                continue;
            }
            if (position.equals(start) || position.equals(end)) {
                nodes.add(position);
                continue;
            }
            int numberOfNeighbors = getNeighborsClimbing(map, position).size();
            if (numberOfNeighbors > 2) {
                nodes.add(position);
            }
        }
        return nodes;
    }

    private Map<Position, List<Edge>> buildGraph(PositionBoard map, Set<Position> nodes) {
        Map<Position, List<Edge>> graph = new HashMap<>();
        for (Position node : nodes) {
            List<Edge> edges = new ArrayList<>();
            for (Position neighbor : getNeighborsClimbing(map, node)) {
                Position previous = node;
                Position current = neighbor;
                int distance = 1;
                while (!nodes.contains(current)) {
                    Set<Position> neighbors = getNeighborsClimbing(map, current);
                    Position next = null;
                    for (Position position : neighbors) {
                        if (!position.equals(previous)) {
                            next = position;
                            break;
                        }
                    }
                    if (next == null) {
                        break;
                    }
                    previous = current;
                    current = next;
                    distance++;
                }
                if (nodes.contains(current)) {
                    edges.add(new Edge(current, distance));
                }
            }
            graph.put(node, edges);
        }
        return graph;
    }

    private Integer findLongestPathGraph(Position current, Position end, Set<Position> visited, Map<Position, List<Edge>> graph) {
        if (current.equals(end)) {
            return 0;
        }
        visited.add(current);
        int max = -1;
        for (Edge edge : graph.get(current)) {
            Position next = edge.target();
            if (!visited.contains(next)) {
                int distance = findLongestPathGraph(next, end, visited, graph);
                if (distance != -1) {
                    max = Math.max(max,edge.distance() + distance);
                }
            }
        }
        visited.remove(current);
        return max;
    }

    private Set<Position> getNeighborsClimbing(PositionBoard map, Position current) {
        List<String> validPositions = List.of(".", ">", "<", "^", "v");
        return map.getFourNeighbors(current)
                .stream()
                .filter(pos ->validPositions.contains(pos.getValue()))
                .collect(Collectors.toSet());
    }

    private record Edge(Position target, int distance) {
    }

}
