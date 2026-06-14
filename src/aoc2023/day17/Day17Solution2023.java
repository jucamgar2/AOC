package aoc2023.day17;

import java.util.Arrays;
import java.util.PriorityQueue;

import structure.BaseDay;
import utils.Direction;
import utils.State;

public class Day17Solution2023 extends BaseDay {

    @Override
    protected Day17Y2023 getInputData() {
        return Day17Y2023.readDay17Data();
    }

    @Override
    public void runDaySolution() {
        Day17Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getLeastHeatLoss((Day17Y2023) input));
    }

    private Integer getLeastHeatLoss(Day17Y2023 input) {
        int[][] lavaFloor = input.getLavaPoll();

        int rows = lavaFloor.length;
        int columns = lavaFloor[0].length;
        int[][][][] dist = new int[rows][columns][4][4];
        for (int[][][] a : dist) {
            for (int[][] b : a) {
                for (int[] c : b) {
                    Arrays.fill(c, Integer.MAX_VALUE);
                }
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[0][1][Direction.EAST.ordinal()][1] = lavaFloor[0][1];
        dist[1][0][Direction.SOUTH.ordinal()][1] = lavaFloor[1][0];

        pq.add(new State(0, 1, Direction.EAST, 1, lavaFloor[0][1]));
        pq.add(new State(1, 0, Direction.SOUTH, 1, lavaFloor[1][0]));

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (current.row() == rows - 1 && current.col() == columns - 1) {
                return current.cost();
            }

            if (current.cost() > dist[current.row()][current.col()][current.dir().ordinal()][current.steps()]) {
                continue;
            }

            Direction currentDir = current.dir();

            if (current.steps() < 3) {
                relax(current, currentDir, current.steps() + 1, lavaFloor,
                        dist, pq, rows, columns);
            }

            relax(current, turnLeft(currentDir), 1, lavaFloor, dist,
                    pq, rows, columns);

            relax(current, turnRight(currentDir), 1, lavaFloor,
                    dist, pq, rows, columns);
        }
        return -1;
    }

    private void relax(State current, Direction newDirection, int newSteps, int[][] lavaFloor,
            int[][][][] dist, PriorityQueue<State> pq, int rows, int columns) {
        int[] pos = move(current.row(),
                current.col(),
                newDirection);

        int newRow = pos[0];
        int newCol = pos[1];

        if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= columns) {
            return;
        }

        int newCost = current.cost() +
                lavaFloor[newRow][newCol];

        if (newCost < dist[newRow][newCol][newDirection.ordinal()][newSteps]) {

            dist[newRow][newCol][newDirection.ordinal()][newSteps] = newCost;

            pq.add(new State(newRow, newCol, newDirection,newSteps, newCost));
        }
    }

    private int[] move(int row, int col, Direction dir) {
        return switch (dir) {
            case NORTH -> new int[] { row - 1, col };
            case SOUTH -> new int[] { row + 1, col };
            case EAST -> new int[] { row, col + 1 };
            case WEST -> new int[] { row, col - 1 };
        };
    }

    private Direction turnLeft(Direction dir) {
        return switch (dir) {
            case NORTH -> Direction.WEST;
            case WEST -> Direction.SOUTH;
            case SOUTH -> Direction.EAST;
            case EAST -> Direction.NORTH;
        };
    }

    private Direction turnRight(Direction dir) {
        return switch (dir) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getLeastHeatLossWithUltraCrucible((Day17Y2023) input));
    }

    private Integer getLeastHeatLossWithUltraCrucible(Day17Y2023 input) {
        int[][] lavaFloor = input.getLavaPoll();

        int rows = lavaFloor.length;
        int columns = lavaFloor[0].length;
        int[][][][] dist = new int[rows][columns][4][11];
        for (int[][][] a : dist) {
            for (int[][] b : a) {
                for (int[] c : b) {
                    Arrays.fill(c, Integer.MAX_VALUE);
                }
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[0][1][Direction.EAST.ordinal()][1] = lavaFloor[0][1];
        dist[1][0][Direction.SOUTH.ordinal()][1] = lavaFloor[1][0];

        pq.add(new State(0, 1, Direction.EAST, 1, lavaFloor[0][1]));
        pq.add(new State(1, 0, Direction.SOUTH, 1, lavaFloor[1][0]));

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (current.row() == rows - 1 && current.col() == columns - 1) {
                return current.cost();
            }

            if (current.cost() > dist[current.row()][current.col()][current.dir().ordinal()][current.steps()]) {
                continue;
            }

            Direction currentDir = current.dir();

            if(current.steps() < 4){
                relax(current, currentDir, current.steps() + 1, lavaFloor,
                            dist, pq, rows, columns);
            }else{
                if (current.steps() < 10) {
                    relax(current, currentDir, current.steps() + 1, lavaFloor,
                            dist, pq, rows, columns);
                }

                relax(current, turnLeft(currentDir), 1, lavaFloor, dist,
                        pq, rows, columns);

                relax(current, turnRight(currentDir), 1, lavaFloor,
                        dist, pq, rows, columns);
            }
        }
        return -1;
    }

}
