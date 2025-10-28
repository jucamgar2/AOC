package aoc2024.day8;

import java.util.List;
import java.util.stream.IntStream;

import structure.BaseDay;
import utils.Position;

public class Day8Solution2024 extends BaseDay{

    @Override
    protected Day8Y2024 getInputData(){
        return Day8Y2024.getDay8Data();
    }

    @Override
    public void runDaySolution(){
        Day8Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);

    }

    @Override
    protected void part1Solution(Object input){
        System.out.println("SOLUCION: " + generateAntinodeMap((Day8Y2024)input,false));
    }

    private static Long generateAntinodeMap(Day8Y2024 input, Boolean isPart2){
        Day8Y2024 antiNodeMap = input.deepCopy();
        Integer maxI = input.getPositions().stream().mapToInt(x->x.getI()).max().orElse(0);
        Integer maxJ = input.getPositions().stream().mapToInt(x->x.getJ()).max().orElse(0);
        input.frequencies.stream().map(freq->input.getAllAntennasByFrecuency(freq)).forEach(list->generateAntinodesByFrequence(list, antiNodeMap,maxI, maxJ,isPart2));
        return antiNodeMap.positions.stream().filter(x->x.getValue().equals("#")).count();
    }

    private static void generateAntinodesByFrequence(List<Position> antennas, Day8Y2024 antinodeMap, Integer maxI, Integer maxJ, Boolean isPart2){
        IntStream.range(0, antennas.size())
            .forEach(i -> IntStream.range(i + 1, antennas.size())
                .forEach(j -> {
                    Position p1 = antennas.get(i);
                    Position p2 = antennas.get(j);
                    boolean isPart2Valid = isPart2 && antennas.size()>2;
                    generateAntiNode(antinodeMap, p1, p2, maxI,maxJ, isPart2Valid);
                }));
    }

    private static void generateAntiNode(Day8Y2024 antinodeMap, Position p1, Position p2, Integer maxI, Integer maxJ, Boolean isPart2){
        Integer distanceI = p2.getI()-p1.getI();
        Integer distanceJ = p2.getJ()-p1.getJ();
        if(isPositionValid(p1.getI() -  distanceI, p1.getJ() -  distanceJ, maxI, maxJ)){
            antinodeMap.getPosition(p1.getI() - distanceI, p1.getJ() - distanceJ).setValue("#");
        }
        if(isPositionValid(p2.getI() + distanceI, p2.getJ() + distanceJ, maxI, maxJ)){
            antinodeMap.getPosition(p2.getI() + distanceI, p2.getJ() + distanceJ).setValue("#");
        }
        if(isPart2){
            generateNodesOfPart2(antinodeMap, p1, p2, maxI, maxJ, distanceI, distanceJ);
        }
    }

    private static boolean isPositionValid(Integer i, Integer j, Integer maxI, Integer maxJ){
        return i<=maxI && i>=0 && j<=maxJ &&j>=0;
    }

    @Override
    protected void part2Solution(Object input){
        System.out.println("SOLUCION: " + generateAntinodeMap((Day8Y2024)input, true));
    }

    private static void generateNodesOfPart2(Day8Y2024 antinodeMap, Position p1, Position p2, Integer maxI, Integer maxJ,
            Integer distanceI, Integer distanceJ) {
            int i = p1.getI();
            int j = p1.getJ();
            while(antinodeMap.getPosition(i, j)!=null){
                Position actualPosition = antinodeMap.getPosition(i, j);
                actualPosition.setValue("#");
                i += distanceI;
                j += distanceJ;
            }
            i = p1.getI() - distanceI;
            j = p1.getJ() - distanceJ;
            while(antinodeMap.getPosition(i, j)!=null){
                Position actualPosition = antinodeMap.getPosition(i, j);
                actualPosition.setValue("#");
                i -= distanceI;
                j -= distanceJ;
            }
    }

}
