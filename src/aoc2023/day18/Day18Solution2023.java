package aoc2023.day18;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import structure.BaseDay;

public class Day18Solution2023 extends BaseDay{

    @Override
    protected Day18Y2023 getInputData() {
        return Day18Y2023.readDay18Data();
    }

    @Override
    public void runDaySolution() {
        Day18Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getCubicMetersOfLava((Day18Y2023) input));   
    }

    private Long getCubicMetersOfLava(Day18Y2023 input) {
        char[][] board = createPositionBoardFromSize(input);
        createDig(board, input);
        fillDig(board);
        long count = 0;
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private char[][] createPositionBoardFromSize(Day18Y2023 input) {
        List<String> directions = input.getDirections();
        List<Integer> distances = input.getDistances();
        int i = 0;
        int j = 0;
        int minI = 0;
        int maxI = 0;
        int minJ = 0;
        int maxJ = 0;
        for (int index = 0; index < directions.size(); index++) {
            int distance = distances.get(index);
            switch (directions.get(index)) {
                case "R" -> j += distance;
                case "L" -> j -= distance;
                case "D" -> i += distance;
                default -> i -= distance;
            }
            minI = Math.min(minI, i);
            maxI = Math.max(maxI, i);
            minJ = Math.min(minJ, j);
            maxJ = Math.max(maxJ, j);
        }
        int height = maxI - minI + 1;
        int width = maxJ - minJ + 1;
        char[][] board = new char[height][width];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        return board;
    }

    private void createDig(char[][] board, Day18Y2023 input) {
        List<String> directions = input.getDirections();
        List<Integer> distances = input.getDistances();
        int i = startI(input);
        int j = startJ(input);
        board[i][j] = '#';
        for (int index = 0; index < directions.size(); index++) {
            String direction = directions.get(index);
            int distance = distances.get(index);
            for (int step = 0; step < distance; step++) {
                switch (direction) {
                    case "R" -> j++;
                    case "L" -> j--;
                    case "D" -> i++;
                    default -> i--;
                }
                board[i][j] = '#';
            }
        }
    }

    private int startI(Day18Y2023 input) {
        int i = 0;
        int minI = 0;
        for (int index = 0; index < input.getDirections().size(); index++) {
            int distance = input.getDistances().get(index);
            switch (input.getDirections().get(index)) {
                case "D" -> i += distance;
                case "U" -> i -= distance;
                default -> { }
            }
            minI = Math.min(minI, i);
        }
        return -minI;
    }

    private int startJ(Day18Y2023 input) {
        int j = 0;
        int minJ = 0;
        for (int index = 0; index < input.getDirections().size(); index++) {
            int distance = input.getDistances().get(index);
            switch (input.getDirections().get(index)) {
                case "R" -> j += distance;
                case "L" -> j -= distance;
                default -> { }
            }
            minJ = Math.min(minJ, j);
        }
        return -minJ;
    }

    private void fillDig(char[][] board) {
        int height = board.length;
        int width = board[0].length;
        boolean[][] outside = new boolean[height][width];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < height; i++) {
            addIfValid(board, outside, i, 0, queue);
            addIfValid(board, outside, i, width - 1, queue);
        }
        for (int j = 0; j < width; j++) {
            addIfValid(board, outside, 0, j, queue);
            addIfValid(board, outside, height - 1, j, queue);
        }
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            addIfValid(board, outside, i - 1, j, queue);
            addIfValid(board, outside, i + 1, j, queue);
            addIfValid(board, outside, i, j - 1, queue);
            addIfValid(board, outside, i, j + 1, queue);
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == '.' && !outside[i][j]) {
                    board[i][j] = '#';
                }
            }
        }
    }

    private void addIfValid(char[][] board, boolean[][] outside, int i, int j, Queue<int[]> queue) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }
        if (board[i][j] != '.' || outside[i][j]) {
            return;
        }
        outside[i][j] = true;
        queue.add(new int[]{i, j});
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getCubicMetersOfLavaFromColors((Day18Y2023) input));
    }

    private long getCubicMetersOfLavaFromColors(Day18Y2023 input) {
        List<String> colors = input.getColors();
        long i = 0;
        long j = 0;
        long shoelaceArea = 0;
        long perimeter = 0;
        for (String color : colors) {
            String code = color.replaceAll("[^0-9a-fA-F]", "");
            long distance = Long.parseLong(code.substring(0, 5), 16);
            char direction = code.charAt(5);
            long ni = i;
            long nj = j;
            switch (direction) {
                case '0' -> nj = j + distance;
                case '1' -> ni = i + distance;
                case '2' -> nj = j - distance;
                default -> ni = i - distance;
            }
            shoelaceArea += (j * ni) - (i * nj);
            perimeter += distance;
            i = ni;
            j = nj;
        }
        long area = Math.abs(shoelaceArea) / 2;
        long interior = area - perimeter / 2 + 1;
        return interior + perimeter;
    }
}
