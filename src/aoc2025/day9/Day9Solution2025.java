package aoc2025.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import structure.BaseDay;
import utils.Position;

public class Day9Solution2025 extends BaseDay{

    @Override
    protected Day9Y2025 getInputData() {
        return Day9Y2025.getDay9Data();
    }

    @Override
    public void runDaySolution() {
        Day9Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUIÓN: " + getLargestArea((Day9Y2025) input));
    }

    private Long getLargestArea(Day9Y2025 input) {
        return IntStream.range(0, input.getPossibleLocations().size())
                .boxed()
                .flatMapToLong(i->IntStream.range(i+1, input.getPossibleLocations().size()
                                    ).mapToLong(index-> getArea(input.getPossibleLocations().get(i), input.getPossibleLocations().get(index))))
                .max().orElse(0);
    }

    private Long getArea(Position position, Position position2) {
        return ((long)(Math.abs(position.getI()-position2.getI())+1)) * 
                (Math.abs(position.getJ()-position2.getJ())+1);
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUIÓN: " + getLargestAreaFromValidRectangles((Day9Y2025) input));
    }

    private Long getLargestAreaFromValidRectangles(Day9Y2025 input) {
        List<Position> lines = generateLines(input);
        return IntStream.range(0, input.getPossibleLocations().size())
                .boxed()
                .flatMapToLong(i->IntStream.range(i+1, input.getPossibleLocations().size()
                                    ).mapToLong(index-> getAreaIfValid(input.getPossibleLocations().get(i), 
                                                                        input.getPossibleLocations().get(index), input, lines)))
                .max().orElse(0);
    }

    private List<Position> generateLines(Day9Y2025 input) {
        List<Position> lines = new ArrayList<>();
        List<Position> locations = input.getPossibleLocations();
        for(int i = 0; i<locations.size();i++){
            Position p = locations.get(i);
            Position p2;
            if(i == 0){
                p2 = locations.get(locations.size()-1);
            }else{
                p2 = locations.get(i-1);
            }
            generateLine(p, p2, lines);
        }
        return lines;
    }

    private void generateLine(Position p, Position p1, List<Position> lines) {
        int x1 = Math.min(p.getI(), p1.getI());
        int x2 = Math.max(p.getI(), p1.getI());
        int y1 = Math.min(p.getJ(), p1.getJ());
        int y2 = Math.max(p.getJ(), p1.getJ());
        if(x1 == x2){
            IntStream.range(y1+1, y2).forEach(y->lines.add(new Position(x1, y, null)));
        }else{
            IntStream.range(x1+1, x2).forEach(x->lines.add(new Position(x, y1, null)));
        }
    }

    private Long getAreaIfValid(Position position, Position position2, Day9Y2025 input, List<Position> lines) {
        int x1 = Math.max(position.getI(), position2.getI());
        int x2 =  Math.min(position.getI(), position2.getI());
        int y1 = Math.max(position.getJ(), position2.getJ());
        int y2 = Math.min(position.getJ(), position2.getJ());
        Boolean isInValid = input.getPossibleLocations().stream()
                                .anyMatch(p-> p.getI()<x1 && p.getI()>x2 && p.getJ()<y1 && p.getJ()>y2);
        if(isInValid){
            return 0l;
        }else{
            isInValid = lines.stream()
                                .anyMatch(p-> p.getI()<x1 && p.getI()>x2 && p.getJ()<y1 && p.getJ()>y2);
            if(isInValid){
                return 0l;
            }else{
               return getArea(position, position2);
            }
        }
    }

    
}
