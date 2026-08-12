package aoc2023.day21;

import utils.PositionBoard;

public class Day21Y2023 {
    
    private PositionBoard garden;

    public PositionBoard getGarden(){
        return this.garden;
    }

    public Day21Y2023(PositionBoard garden){
        this.garden = garden;
    }

    public static Day21Y2023 readDay21Data(){
        return new Day21Y2023(PositionBoard.readPositionBoard("inputs/2023/Day21Data.txt"));
    }
}
