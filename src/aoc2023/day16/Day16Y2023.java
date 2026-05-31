package aoc2023.day16;

import utils.PositionBoard;

public class Day16Y2023 {
    
    private PositionBoard cavern;

    public PositionBoard getCavern(){
        return this.cavern;
    }

    public Day16Y2023(PositionBoard cavern){
        this.cavern = cavern;
    }

    public static Day16Y2023 readDay16Data(){
        return new Day16Y2023(PositionBoard.readPositionBoard("inputs/2023/TestDay16.txt"));
    }
}
