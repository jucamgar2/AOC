package aoc2024.day10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10Solution {
    
    public static void day10Solution(){
        Day10 input = Day10.getDay10Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day10 input) {
        System.out.println("SOLUCION: "  + getSumOfTrailheads(input));
    }

    private static Integer getSumOfTrailheads(Day10 input){
        return input.getStartPositions().stream()
            .mapToInt(startPosition-> getNumOfTrailheadsByStartPosition(startPosition, input.getPositions()))
            .sum();
    }

    private static Integer getNumOfTrailheadsByStartPosition(IntPosition startPosition, List<IntPosition> positions){
        Set<IntPosition> peaks = new HashSet<>();
        dfs(startPosition, positions, new HashSet<>(), peaks);
        return peaks.size();
    }

    private static void dfs(IntPosition current, List<IntPosition> positions, Set<IntPosition> visited, Set<IntPosition> peaks) {
        if (current.getValue() == 9) {
            peaks.add(current);
            return;
        }
        visited.add(current);
        for (IntPosition next : getArroudPositionValids(current, positions)) {
            if (!visited.contains(next)) {
                dfs(next, positions, visited, peaks);
            }
        }
        visited.remove(current); 
    }

    private static List<IntPosition> getArroudPositionValids(IntPosition startPosition, List<IntPosition> positions){
        return positions.stream().filter(position->isArroundPosition(startPosition, position))
            .filter(position-> position.getValue() == startPosition.getValue() + 1)
            .toList();
    }

    private static Boolean isArroundPosition(IntPosition reference, IntPosition comparing){
        int differenceI = Math.abs(reference.getI() - comparing.getI());
        int differenceJ = Math.abs(reference.getJ() - comparing.getJ()); 
        return (differenceI==1&&differenceJ==0) || (differenceJ==1&&differenceI==0);
    }

    private static void part2Solution(Day10 input) {
        System.out.println("SOLUCION: "  + getRatigOfTrailheads(input));
    }

    private static Integer getRatigOfTrailheads(Day10 input){
        return input.getStartPositions().stream()
            .mapToInt(startPosition-> getRatingOfTrailheadsByStartPosition(startPosition, input.getPositions()))
            .sum();
    }

    private static Integer getRatingOfTrailheadsByStartPosition(IntPosition startPosition, List<IntPosition> positions){
        int total = 0;
        if(startPosition.getValue()==9){
            return 1;
        }
        else{
            for(IntPosition next:getArroudPositionValids(startPosition, positions)){
                total+=getRatingOfTrailheadsByStartPosition(next, positions);
            }
        }
        return total;
    }
    
}
