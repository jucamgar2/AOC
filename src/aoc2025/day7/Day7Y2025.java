package aoc2025.day7;

import utils.PositionBoard;

public class Day7Y2025 {
    
    private PositionBoard board;

    public PositionBoard getBoard(){
        return this.board;
    }

    public Day7Y2025(PositionBoard board){
        this.board = board;
    }

    public static Day7Y2025 getDay7Data(){
        return new Day7Y2025(PositionBoard.readPositionBoard("inputs/2025/TestDay7.txt"));
    }

}
