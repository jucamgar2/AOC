package aoc2023.day21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import structure.BaseDay;
import utils.Position;
import utils.PositionBoard;

public class Day21Solution2023 extends BaseDay{

    @Override
    protected Day21Y2023 getInputData() {
        return Day21Y2023.readDay21Data();
    }

    @Override
    public void runDaySolution() {
        Day21Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getPlotsToReach((Day21Y2023) input, 64));
    }

    private int getPlotsToReach(Day21Y2023 input, int steps) {
        PositionBoard garden = input.getGarden();
        Position start = garden.getPositions().stream().filter(x->x.getValue().equals("S")).findFirst().orElse(null);
        Set<Position> current = new HashSet<>();
        current.add(start);
        for (int step = 1; step <= steps; step++) {
            Set<Position> next = new HashSet<>();
            for (Position p : current) {
                for (Position neighbor : garden.getFourNeighbors(p)) {
                    if (neighbor.getValue().equals(".") || neighbor.getValue().equals("S")) {
                        next.add(neighbor);
                    }
                }
            }
            current = next;
        }
        return current.size();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getPlotsWithInfiniteMap((Day21Y2023) input));
    }

    private Long getPlotsWithInfiniteMap(Day21Y2023 input) {
        long y0 = getPlotsToReachWithInfMap(input, 65);
        System.out.println("65");
        long y1 = getPlotsToReachWithInfMap(input, 196);
        System.out.println("196");
        long y2 = getPlotsToReachWithInfMap(input, 327);

        long x = (26_501_365L - 65) / 131;

        long firstDifference = y1 - y0;
        long secondDifference = y2 - 2 * y1 + y0;

        return y0
                + x * firstDifference
                + x * (x - 1) / 2 * secondDifference;
    }

    private int getPlotsToReachWithInfMap(Day21Y2023 input, int steps) {
        PositionBoard garden = input.getGarden();

        Position start = garden.getPositions()
                .stream()
                .filter(p -> p.getValue().equals("S"))
                .findFirst()
                .orElseThrow();

        int height = garden.getMaxI() + 1;
        int width = garden.getMaxJ() + 1;

        Set<Long> current = new HashSet<>();
        current.add(encode(start.getI(), start.getJ()));

        for (int step = 0; step < steps; step++) {

            Set<Long> next = new HashSet<>(current.size() * 2);

            for (long pos : current) {

                int i = decodeI(pos);
                int j = decodeJ(pos);

                addIfValid(garden, next, i - 1, j, height, width);
                addIfValid(garden, next, i + 1, j, height, width);
                addIfValid(garden, next, i, j - 1, height, width);
                addIfValid(garden, next, i, j + 1, height, width);
            }

            current = next;
        }

        return current.size();
    }

    private void addIfValid(
        PositionBoard garden,
        Set<Long> next,
        int i,
        int j,
        int height,
        int width
    ) {
        int mapI = Math.floorMod(i, height);
        int mapJ = Math.floorMod(j, width);

        Position position = garden.getPosition(mapI, mapJ);

        if (position.getValue().equals(".")
                || position.getValue().equals("S")) {

            next.add(encode(i, j));
        }
    }

    private long encode(int i, int j) {
        return ((long) i << 32) | (j & 0xffffffffL);
    }

    private int decodeI(long value) {
        return (int) (value >> 32);
    }

    private int decodeJ(long value) {
        return (int) value;
    }

    private List<Position> getFourNeighborsInfMap(PositionBoard board, Position p){
        int i = p.getI();
        int j = p.getJ();
        List<Position> possibleNeighbors = new ArrayList<>();
        Position up = getPositionInf(board, i-1, j);
        Position down = getPositionInf(board, i+1, j);
        Position left = getPositionInf(board, i, j-1);
        Position right = getPositionInf(board, i, j+1);
        possibleNeighbors.add(left);
        possibleNeighbors.add(down);
        possibleNeighbors.add(right);
        possibleNeighbors.add(up);
        return possibleNeighbors;
    }

    private Position getPositionInf(PositionBoard board, int i, int j){
        i = Math.floorMod(i, board.getMaxI());
        j = Math.floorMod(j, board.getMaxJ());
        return board.getPosition(i, j);
    }


    
}
