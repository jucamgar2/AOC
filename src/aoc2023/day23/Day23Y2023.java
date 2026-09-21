package aoc2023.day23;

import utils.PositionBoard;

public class Day23Y2023 {
    
    private PositionBoard map;

    public PositionBoard getMap(){
        return this.map;
    }

    public Day23Y2023(PositionBoard map){
        this.map = map;
    }

    public static Day23Y2023 readDay23Data(){
        return new Day23Y2023(PositionBoard.readPositionBoard("inputs/2023/TestDay23.txt"));
    }
}
