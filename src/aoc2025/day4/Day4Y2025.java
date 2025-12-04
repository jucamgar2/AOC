package aoc2025.day4;

import utils.PositionBoard;

public class Day4Y2025 {
    
    private PositionBoard diagram;

    public PositionBoard getDiagram(){
        return this.diagram;
    }

    public Day4Y2025(PositionBoard diagram){
        this.diagram = diagram;
    }

    public static Day4Y2025 getDay4Data(){
        return new Day4Y2025(PositionBoard.readPositionBoard("inputs/2025/TestDay4.txt"));
    }
}
