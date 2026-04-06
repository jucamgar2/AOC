package aoc2023.day11;

import utils.PositionBoard;

public class Day11Y2023 {
    
    private PositionBoard galaxyMap;

    public PositionBoard getGalaxyMap(){
        return this.galaxyMap;
    }

    public Day11Y2023(PositionBoard galaxyMap){
        this.galaxyMap = galaxyMap;
    }

    public static Day11Y2023 readDay11Data(){
        return new Day11Y2023(PositionBoard.readPositionBoard("inputs/2023/TestDay11.txt"));
    }


}
