package aoc2023.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import structure.BaseDay;
import utils.Position;
import utils.PositionBoard;

public class Day14Solution2023 extends BaseDay{

    @Override
    protected Day14Y2023 getInputData() {
        return Day14Y2023.readDay14Data();
    }
    
    @Override
    public void runDaySolution() {
        Day14Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getTotalNorthLoad((Day14Y2023) input));
    }

    private Long getTotalNorthLoad(Day14Y2023 input) {
        return IntStream.rangeClosed(0, input.getPlatform().getMaxJ())
                .mapToLong(index-> getLoadInColumn(new ArrayList<>(input.getPlatform().getColumn(index))))
                .sum();
    }

    private Long getLoadInColumn(List<String> column) {
        for(int i = 0; i<column.size(); i++){
            String actItem = column.get(i);
            if(actItem.equals("O")){
                int lastEmptyPlace = i;
                for(int x = i-1; x>=0; x--){
                    if(column.get(x).equals(".")){
                        lastEmptyPlace = x;
                    }else{
                        break;
                    }
                }
                if(i!=lastEmptyPlace){
                    column.set(i, ".");
                    column.set(lastEmptyPlace, "O");
                }
            }
        }
        return calculateLoadInColumn(column);
    }

    private Long calculateLoadInColumn(List<String> column) {
        return IntStream.range(0, column.size())
                    .filter(index->column.get(index).equals("O"))
                    .mapToLong(index->column.size()-index)
                    .sum();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getLoadAfterCycles((Day14Y2023) input));
    }

    private Long getLoadAfterCycles(Day14Y2023 input) {
        PositionBoard board = input.getPlatform();
        Map<String, Integer> seen = new HashMap<>();
        List<String> states = new ArrayList<>();
        int cycle = 0;
        String finalState = "";
        while (true) {
            String state = board.toString();
            if (seen.containsKey(state)) {
                int loopStart = seen.get(state);
                int loopSize = cycle - loopStart;
                int target =
                    loopStart +
                    (1_000_000_000 - loopStart) % loopSize;
                finalState = states.get(target);
                break;
            }
            seen.put(state, cycle);
            states.add(board.toString());
            doFullCycle(board);
            cycle++;
        }
        return calculateLoadInNorth(finalState);
    }

    private Long calculateLoadInNorth(String finalState) {
        String[] lines = finalState.split("\n");
        long totalLoad = 0;
        int maxLoad = lines.length;
        for (int i = 0; i < lines.length; i++) {
            int load = maxLoad - i;
            for (char c : lines[i].toCharArray()) {
                if (c == 'O') {
                    totalLoad += load;
                }
            }
        }
        return totalLoad;
    }

    public void doFullCycle(PositionBoard board) {
        tiltNorth(board);
        tiltWest(board);
        tiltSouth(board);
        tiltEast(board);
    }

    private void tiltNorth(PositionBoard board) {
        for (int j = 0; j <= board.getMaxJ(); j++) {
            int nextFreeRow = 0;
            for (int i = 0; i <= board.getMaxI(); i++) {
                Position current = board.getPosition(i, j);
                if (current.getValue().equals("#")) {
                    nextFreeRow = i + 1;
                } else if (current.getValue().equals("O")) {
                    if (i != nextFreeRow) {
                        board.getPosition(nextFreeRow, j).setValue("O");
                        current.setValue(".");
                    }
                    nextFreeRow++;
                }
            }
        }
    }

    private void tiltSouth(PositionBoard board) {
        for (int j = 0; j <= board.getMaxJ(); j++) {
            int nextFreeRow = board.getMaxI();
            for (int i = board.getMaxI(); i >= 0; i--) {
                Position current = board.getPosition(i, j);
                if (current.getValue().equals("#")) {
                    nextFreeRow = i - 1;
                } else if (current.getValue().equals("O")) {
                    if (i != nextFreeRow) {
                        board.getPosition(nextFreeRow, j).setValue("O");
                        current.setValue(".");
                    }
                    nextFreeRow--;
                }
            }
        }
    }

    private void tiltWest(PositionBoard board) {
        for (int i = 0; i <= board.getMaxI(); i++) {
            int nextFreeColumn = 0;
            for (int j = 0; j <= board.getMaxJ(); j++) {
                Position current = board.getPosition(i, j);
                if (current.getValue().equals("#")) {
                    nextFreeColumn = j + 1;
                } else if (current.getValue().equals("O")) {
                    if (j != nextFreeColumn) {
                        board.getPosition(i, nextFreeColumn).setValue("O");
                        current.setValue(".");
                    }
                    nextFreeColumn++;
                }
            }
        }
    }

    private void tiltEast(PositionBoard board) {
        for (int i = 0; i <= board.getMaxI(); i++) {
            int nextFreeColumn = board.getMaxJ();
            for (int j = board.getMaxJ(); j >= 0; j--) {
                Position current = board.getPosition(i, j);
                if (current.getValue().equals("#")) {
                    nextFreeColumn = j - 1;
                } else if (current.getValue().equals("O")) {
                    if (j != nextFreeColumn) {
                        board.getPosition(i, nextFreeColumn).setValue("O");
                        current.setValue(".");
                    }
                    nextFreeColumn--;
                }
            }
        }
    }

}
