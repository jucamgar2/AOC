package aoc2023.day13;

import java.util.List;
import java.util.stream.IntStream;
import structure.BaseDay;
import utils.PositionBoard;

public class Day13Solution2023 extends BaseDay {
    @Override
    protected Day13Y2023 getInputData() {
        return Day13Y2023.readDay13Data();
    }

    @Override
    public void runDaySolution() {
        Day13Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + summarizeNotes((Day13Y2023) input));
    }

    private Long summarizeNotes(Day13Y2023 input) {
        return input.getMirrorMaps().stream().mapToLong(this::getPatternNote).sum();
    }

    private Long getPatternNote(PositionBoard mirrorMap) {
        int columnMirror = 0;
        int lineMirror = 0;
        for (int i = 0; i < mirrorMap.getMaxI(); i++) {
            if (isLineMirror(mirrorMap, i)) {
                lineMirror = i + 1;
                break;
            }
        }
        if (lineMirror == 0) {
            for (int j = 0; j < mirrorMap.getMaxJ(); j++) {
                if (isColumnMirror(mirrorMap, j)) {
                    columnMirror = j + 1;
                    break;
                }
            }
        }
        return (long) columnMirror + lineMirror * 100;
    }

    private boolean isLineMirror(PositionBoard mirrorMap, int i) {
        for (int x = 0; i - x >= 0 && i + x < mirrorMap.getMaxI(); x++) {
            if (!mirrorMap.getLine(i - x).equals(mirrorMap.getLine(i + x + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnMirror(PositionBoard mirrorMap, int j) {
        for (int x = 0; j - x >= 0 && j + x + 1 < mirrorMap.getMaxJ(); x++) {
            if (!mirrorMap.getColumn(j - x).equals(mirrorMap.getColumn(j + x + 1))) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + summarizeNotesWithCorrection((Day13Y2023) input));
    }

    private Long summarizeNotesWithCorrection(Day13Y2023 input) {
        return input.getMirrorMaps().stream().mapToLong(this::getPatternNoteWithCorrection).sum();
    }

    private Long getPatternNoteWithCorrection(PositionBoard mirrorMap) {
        int columnMirror = 0;
        int lineMirror = 0;
        for (int i = 0; i < mirrorMap.getMaxI(); i++) {
            if (isLineMirrorWithError(mirrorMap, i)) {
                lineMirror = i + 1;
                break;
            }
        }
        if (lineMirror == 0) {
            for (int j = 0; j < mirrorMap.getMaxJ(); j++) {
                if (isColumnMirrorWithError(mirrorMap, j)) {
                    columnMirror = j + 1;
                    break;
                }
            }
        }
        return (long) columnMirror + lineMirror * 100;
    }

    private boolean isColumnMirrorWithError(PositionBoard mirrorMap, int j) {
        int numOfErrors = 0;
        for (int x = 0; j - x >= 0 && j+x < mirrorMap.getMaxJ(); x++) {
            if (!mirrorMap.getColumn(j - x).equals(mirrorMap.getColumn(j + x + 1))) {
                if (numOfErrors == 0 && haveLinesOneError(mirrorMap.getColumn(j - x), mirrorMap.getColumn(j + x + 1))) {
                    numOfErrors = 1;
                } else {
                    return false;
                }
            }
        }
        return numOfErrors == 1;
    }

    private boolean isLineMirrorWithError(PositionBoard mirrorMap, int i) {
        int numOfErrors = 0;
        for (int x = 0; i - x >= 0 && i+x < mirrorMap.getMaxI(); x++) {
            if (!mirrorMap.getLine(i - x).equals(mirrorMap.getLine(i+x+1))) {
                if (numOfErrors == 0 && haveLinesOneError(mirrorMap.getLine(i - x), mirrorMap.getLine(i + x + 1))) {
                    numOfErrors = 1;
                } else {
                    return false;
                }
            }
        }
        return numOfErrors == 1;
    }

    private boolean haveLinesOneError(List<String> line, List<String> line2) {
        long numOfErrors = IntStream.range(0, line.size()).filter(index -> !line.get(index).equals(line2.get(index)))
                .count();
        return numOfErrors == 1;
    }
}