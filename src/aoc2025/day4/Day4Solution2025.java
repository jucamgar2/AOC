package aoc2025.day4;

import java.util.List;
import java.util.stream.Stream;

import structure.BaseDay;
import utils.Position;
import utils.PositionBoard;

public class Day4Solution2025 extends BaseDay{

    @Override
    protected Day4Y2025 getInputData() {
        return Day4Y2025.getDay4Data();
    }

    @Override
    public void runDaySolution() {
        Day4Y2025 input = Day4Y2025.getDay4Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN: " + countAccesibleRolls((Day4Y2025)input));
    }

    private Long countAccesibleRolls(Day4Y2025 input){
        return getAccesibleRolls(input).count();
    }

    private Stream<Position> getAccesibleRolls(Day4Y2025 input) {
        return input.getDiagram().getPositions().stream()
                .filter(paperRoll->paperRoll.getValue().equals("@"))
                .filter(paperRoll-> isAccesible(paperRoll, input.getDiagram()));
    }

    private Boolean isAccesible(Position paperRoll, PositionBoard diagram) {
        return diagram.getEightNeighbors(paperRoll).stream().filter(roll-> roll.getValue().equals("@")).count()<4;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: " + countRemovableRollPapers((Day4Y2025)input));
    }

    private Long countRemovableRollPapers(Day4Y2025 input) {
        removeAllRollPapers(input);
        return input.getDiagram().getPositions().stream()
                    .filter(x->x.getValue().equals("x"))
                    .count();
    }

    private void removeAllRollPapers(Day4Y2025 input) {
        List<Position> accesibleRolls = getAccesibleRolls(input).toList();
        while (!accesibleRolls.isEmpty()) {
            accesibleRolls.forEach(accesibleRoll -> accesibleRoll.setValue("x"));
            accesibleRolls = getAccesibleRolls(input).toList();
        }
    }    
    
}
