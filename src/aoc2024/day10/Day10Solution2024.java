package aoc2024.day10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import structure.BaseDay;
import utils.IntPosition;

public class Day10Solution2024 extends BaseDay{

    @Override
    protected Day10Y2024 getInputData(){
        return Day10Y2024.getDay10Data();
    }
    
    @Override
    public void runDaySolution(){
        Day10Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input){
        System.out.println("SOLUCION: "  + getSumOfTrailheads((Day10Y2024)input));
    }

    private static Integer getSumOfTrailheads(Day10Y2024 input){
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

    @Override
    protected void part2Solution(Object input){
        System.out.println("SOLUCION: "  + getRatigOfTrailheads((Day10Y2024)input));
    }

    private static Integer getRatigOfTrailheads(Day10Y2024 input){
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
