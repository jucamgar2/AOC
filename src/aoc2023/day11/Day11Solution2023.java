package aoc2023.day11;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import structure.BaseDay;
import utils.Position;
import utils.PositionBoard;

public class Day11Solution2023 extends BaseDay{

    @Override
    protected Day11Y2023 getInputData() {
        return Day11Y2023.readDay11Data();
    }
    @Override
    public void runDaySolution() {
        Day11Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getSumOfLengths((Day11Y2023)input, false));
    }

    private Long getSumOfLengths(Day11Y2023 input, boolean isPart2) {
        PositionBoard galaxyMap = input.getGalaxyMap();
        List<Integer> columnsWithNoGalaxy = IntStream.range(0, galaxyMap.getMaxI())
                    .filter(i-> !galaxyMap.getPositionsInI(i).contains("#"))
                    .boxed()
                    .toList();
        List<Integer> rowsWithNoGalaxy = IntStream.range(0, galaxyMap.getMaxJ())
                    .filter(i-> !galaxyMap.getPositionsInJ(i).contains("#"))
                    .boxed()
                    .toList();
        List<Position> galaxys = galaxyMap.getPositions().stream().filter(x->x.getValue().equals("#")).toList();
        return LongStream.range(0, galaxys.size())
                .flatMap(i->LongStream.range(i, galaxys.size())
                        .map(j->getGalxysDistance(galaxys.get((int)i), galaxys.get((int)j), rowsWithNoGalaxy, columnsWithNoGalaxy, isPart2)))
                .sum();
    }
    private Long getGalxysDistance(Position position, Position position2, List<Integer> rowsWithNoGalaxy,
            List<Integer> columnsWithNoGalaxy, boolean isPart2) {
        int distanceMultiplier = 1;
        if(isPart2){
            distanceMultiplier = 999999;
        }
        Integer distance = Math.abs(position.getI()-position2.getI()) + Math.abs(position.getJ()-position2.getJ());
        int maxI = Math.max(position.getI(), position2.getI());
        int maxJ = Math.max(position.getJ(), position2.getJ());
        int minI = Math.min(position.getI(), position2.getI());
        int minJ = Math.min(position.getJ(), position2.getJ());
        long dobuleRows = rowsWithNoGalaxy.stream().filter(row-> row>=minJ && row<=maxJ).count();
        long dobuleColumns =columnsWithNoGalaxy.stream().filter(column-> column>=minI && column<=maxI).count();
        return distance+(dobuleColumns+dobuleRows)*distanceMultiplier;
    }
    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getSumOfLengths((Day11Y2023)input, true));
    }
    
}
