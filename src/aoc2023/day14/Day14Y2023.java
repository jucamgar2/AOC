package aoc2023.day14;

import utils.PositionBoard;

public class Day14Y2023 {
    
    private PositionBoard platform;

    public PositionBoard getPlatform(){
        return this.platform;
    }

    public Day14Y2023(PositionBoard platform){
        this.platform = platform;
    }

    public static Day14Y2023 readDay14Data(){
        return new Day14Y2023(PositionBoard.readPositionBoard("inputs/2023/TestDay14.txt"));
    }

}
