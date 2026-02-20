package aoc2025.day12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import structure.BaseDay;
import utils.Shape;

public class Day12Solution2025 extends BaseDay{

    @Override
    protected Day12Y2025 getInputData() {
        return Day12Y2025.readDay12Data();   
    }

    @Override
    public void runDaySolution() {
        Day12Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getNumOfRegionsWithAllPresents((Day12Y2025) input));
    }

    private Long getNumOfRegionsWithAllPresents(Day12Y2025 input) {
        Map<String, Shape> shapes = processSahpes(input.getShapes());
        return input.getRegions().stream().filter(region-> canPresentsFitInRegion(region, shapes)).count();    
    }

    private Map<String, Shape> processSahpes(Map<String,String> shapes) {
        Map<String, Shape> processedShapes = new HashMap<>();
        for(String key: shapes.keySet()){
            String shape = shapes.get(key);
            String[] rows = shape.split("/");
            int height = rows.length;
            int width = rows[0].length();
            int area = 0;
            boolean[][] region = new boolean[height][width];
             for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    region[i][j] = rows[i].charAt(j) == '#';
                    if(region[i][j]){
                        area++;
                    }
                }
            }
            List<boolean[][]> variants = new ArrayList<boolean[][]>();
            boolean[][] current = region;
            for (int i = 0; i < 4; i++) {

                addIfNotExists(variants, current);

                boolean[][] flipped = flipHorizontal(current);
                addIfNotExists(variants, flipped);

                current = rotate(current);
            }
            Shape processedShape = new Shape(variants, area);
            processedShapes.put(key, processedShape);
        }
        return processedShapes;
    }

    private boolean[][] rotate(boolean[][] shape) {
        int height = shape.length;
        int width = shape[0].length;
        boolean[][] rotated = new boolean[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotated[j][height - 1 - i] = shape[i][j];
            }
        }
        return rotated;
    }

    private boolean[][] flipHorizontal(boolean[][] shape) {
        int height = shape.length;
        int width = shape[0].length;
        boolean[][] flipped = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                flipped[i][width - 1 - j] = shape[i][j];
            }
        }
        return flipped;
    }

    private boolean equalsShape(boolean[][] a, boolean[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void addIfNotExists(List<boolean[][]> list, boolean[][] candidate) {
        for (boolean[][] existing : list) {
            if (equalsShape(existing, candidate)) {
                return;
            }
        }
        list.add(candidate);
    }

    private boolean canPresentsFitInRegion(List<String> region, Map<String,Shape> shapes) {
        String[] regionMap = region.get(0).split("x");
        List<String> quantities = region.subList(1, region.size());
        List<Shape> presents = getPresentsInvolveds(quantities, shapes);
        int height = Integer.parseInt(regionMap[0]);
        int width = Integer.parseInt(regionMap[1]);
        Integer regionArea = height * width;
        Integer neededArea = presents.stream().mapToInt(shape-> shape.getArea()).sum();
        if(neededArea>regionArea){
            return false;
        }
        boolean[][] board = new boolean[height][width];
        Collections.sort(presents, (a, b) -> b.getArea() - a.getArea());

        return solve(board, presents, 0);
    }

    private List<Shape> getPresentsInvolveds(List<String> region, Map<String,Shape> shapes) {
        List<Shape> presents = new ArrayList<>();
        for(int i = 0; i<region.size(); i++){
            Integer repetitions = Integer.parseInt(region.get(i));
            if(repetitions>0){
                for(int j =0; j<repetitions; j++){
                    presents.add(shapes.get(String.valueOf(i)));
                }
            }
        }
        return presents;
    }

    private boolean solve(boolean[][] board, List<Shape> pieces, int index) {
        if (index == pieces.size()) {
            return true; 
        }
        Shape currentShape = pieces.get(index);
        for (boolean[][] variant : currentShape.getVariants()) {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (canPlace(board, variant, row, col)) {
                        place(board, variant, row, col, true);
                        if (solve(board, pieces, index + 1)) {
                            return true;
                        }
                        place(board, variant, row, col, false);
                    }
                }
            }
        }
        return false;
    }

    private boolean canPlace(boolean[][] board, boolean[][] shape, int startRow, int startCol) {
        int shapeHeight = shape.length;
        int shapeWidth = shape[0].length;
        if (startRow + shapeHeight > board.length ||
            startCol + shapeWidth > board[0].length) {
            return false;
        }
        for (int i = 0; i < shapeHeight; i++) {
            for (int j = 0; j < shapeWidth; j++) {

                if (shape[i][j] && board[startRow + i][startCol + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void place(boolean[][] board, boolean[][] shape,
                   int startRow, int startCol, boolean value) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j]) {
                    board[startRow + i][startCol + j] = value;
                }
            }
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("RETO CONSEGUIDO");
    }

    
}
