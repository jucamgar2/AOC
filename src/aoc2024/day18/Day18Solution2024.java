package aoc2024.day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

import structure.BaseDay;
import utils.Position;

public class Day18Solution2024 extends BaseDay{

    @Override
    protected Day18Y2024 getInputData(){
        return Day18Y2024.getDay18Data();
    }
    
    public void runDaySolution(){
        Day18Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
        
    }

    @Override
    protected void part1Solution(Object input) {
        Integer minSteps = getMinimumNumberOfStepsToEscape((Day18Y2024)input) -1;
        System.out.println("SOLUCION: " + minSteps);
    }

    private static Integer getMinimumNumberOfStepsToEscape(Day18Y2024 input) {
        simulateMemoryFall(input);
        Position start = input.getPosition(0, 0);
        Position end = input.getEndPosition();
        return getShortestPath(input, start, end).size();
    }

    private static void simulateMemoryFall(Day18Y2024 input){
        Integer memoryFallSize = 12;
        if(input.getBytesToFall().size()>=1024){
            memoryFallSize = 1024;
        }
        IntStream.range(0, memoryFallSize).forEach(num->{
            Position memoryToFall = input.getBytesToFall().get(num);
            Position positionInGrid = input.getPosition(memoryToFall.getI(), memoryToFall.getJ());
            positionInGrid.setValue("#");
        });
    }

    public static List<Position> getShortestPath(Day18Y2024 input, Position start, Position end){
        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
        Map<Position, Position> parents = new HashMap<>();
        queue.add(start);
        visited.add(start);
        parents.put(start, null);
        while(!queue.isEmpty()){
            Position current = queue.poll();
            if(current.equals(end)){
                return buildPath(parents, end);
            }
            for(Position next:getNeighbors(input, current)){
                if(!visited.contains(next)){
                    queue.add(next);
                    visited.add(next);
                    parents.put(next, current);
                }
            }
        }
        return Collections.emptyList();
    }

    private static List<Position> getNeighbors(Day18Y2024 input, Position p){
        List<Position> possibleNeighbors = new ArrayList<>();
        int i = p.getI();
        int j = p.getJ();
        Position up = input.getPosition(i-1, j);
        Position down = input.getPosition(i+1, j);
        Position left = input.getPosition(i, j-1);
        Position right = input.getPosition(i, j+1);
        possibleNeighbors.add(left);
        possibleNeighbors.add(down);
        possibleNeighbors.add(right);
        possibleNeighbors.add(up);
        return possibleNeighbors.stream()
            .filter(neighbor-> neighbor!=null && !neighbor.getValue().equals("#"))
            .toList();
    }

    private static List<Position> buildPath(Map<Position, Position> parents, Position end){
        List<Position> path = new ArrayList<>();
        Position node = end;
        while(node !=null){
            path.add(node);
            node = parents.get(node);
        }
        return path;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + findFirstByteToMakeExitUnreachable((Day18Y2024)input)); 
    }

    private static String findFirstByteToMakeExitUnreachable(Day18Y2024 input) {
        Integer memoryFallStart = 13;
        if(input.getBytesToFall().size()>=1024){
            memoryFallStart = 1025;
        }
        Boolean continueFalling = true;
        String byteThatMakesUnreachable = "";
        Position start = input.getPosition(0, 0);
        Position end = input.getEndPosition();
        for(int indexMemoryFall = memoryFallStart;continueFalling;indexMemoryFall++){
            Position byteToFall = input.getBytesToFall().get(indexMemoryFall);
            input.getPosition(byteToFall.getI(), byteToFall.getJ()).setValue("#");
            if(getShortestPath(input, start, end).size()== 0){
                continueFalling = false;
                byteThatMakesUnreachable = byteToFall.getI() + "," + byteToFall.getJ();
            }
        }
        return byteThatMakesUnreachable;
    }
}
