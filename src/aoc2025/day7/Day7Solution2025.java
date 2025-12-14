package aoc2025.day7;

import java.util.HashMap;
import java.util.Map;

import structure.BaseDay;
import utils.Position;
import utils.PositionBoard;

public class Day7Solution2025 extends BaseDay{

    @Override
    protected Day7Y2025 getInputData() {
        return Day7Y2025.getDay7Data();
    }
    
    @Override
    public void runDaySolution() {
        Day7Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getCountOfSplits((Day7Y2025) input));
    }

    private int getCountOfSplits(Day7Y2025 input) {
        PositionBoard board = input.getBoard();
        int maxI = board.getMaxI();
        int maxJ = board.getMaxJ();
        int splits = 0;
        for(int i = 1;i<=maxI;i++){
            for(int j =0;j<=maxJ;j++){
                Position actual = board.getPosition(i, j);
                Position upPosition=board.getPosition(i-1, j);
                if(upPosition.getValue().equals("S")||upPosition.getValue().equals("|")){
                    if(actual.getValue().equals(".")){
                        actual.setValue("|");
                    }else if(actual.getValue().equals("^")){
                        board.getPosition(i, j-1).setValue("|");
                        board.getPosition(i, j+1).setValue("|");
                        splits++;
                    }
                }
            }
        }
        return splits;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: " + (getNumOfTimelines((Day7Y2025)input)));
    }

    private Long getNumOfTimelines(Day7Y2025 input) {
        PositionBoard board = input.getBoard();
        Position start = findStart(board);
        Map<Position, Long> memo = new HashMap<>();
        Position first = board.getPosition(start.getI() + 1, start.getJ());
        return traverse(first, board, memo);
    }

    private long traverse(
        Position pos,
        PositionBoard board,
        Map<Position, Long> memo) {
        if (pos == null) {
            return 1L;
        }
        if (memo.containsKey(pos)) {
            return memo.get(pos);
        }
        long result;
        String cell = pos.getValue();
        if ("^".equals(cell)) {
            Position left = board.getPosition(pos.getI() + 1, pos.getJ() - 1);
            Position right = board.getPosition(pos.getI() + 1, pos.getJ() + 1);

            result = traverse(left, board, memo)
                + traverse(right, board, memo);
        } else if (".".equals(cell) || "|".equals(cell)) {
            Position down = board.getPosition(pos.getI() + 1, pos.getJ());
            result = traverse(down, board, memo);
        } else {
            result = 0L;
        }
        memo.put(pos, result);
        return result;
    }

    private Position findStart(PositionBoard board) {
    return board.getPositions().stream()
            .filter(p -> "S".equals(p.getValue()))
            .findFirst()
            .orElse(null);
    }


    
}
