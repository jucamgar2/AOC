package aoc2023.day22;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import structure.BaseDay;
import utils.Point;

public class Day22Solution2023 extends BaseDay{

    @Override
    protected Day22Y2023 getInputData() {
        return Day22Y2023.readDay22Data();
    }    
    
    @Override
    public void runDaySolution() {
        Day22Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }
    

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solucion: " + countSelectableBricks((Day22Y2023) input));
    }

    private Integer countSelectableBricks(Day22Y2023 input) {
        List<Brick> bricks = input.getBricks();
        bricks.sort(Comparator.comparingInt(Brick::minZ));
        Map<Column, Integer> heights = new HashMap<>();
        Map<Point, Integer> occupied = new HashMap<>();
        Map<Integer, Set<Integer>> supports = new HashMap<>();
        Map<Integer, Set<Integer>> supportedBy = new HashMap<>();
        for(Brick brick: bricks){
            dropBrick(heights, brick);
            Set<Integer> brickSupports = findSupports(brick, occupied);
            supportedBy.put(brick.getId(),brickSupports);
            for (Integer support : brickSupports) {
                supports.computeIfAbsent(support, key -> new HashSet<>())
                        .add(brick.getId());
            }
            for (Point cube : brick.getCubes()) {
                occupied.put(cube, brick.getId());
            }
        }
        int selectableBricks = 0; 
        for (Brick brick : bricks) {
            int brickId = brick.getId();
            Set<Integer> bricksAbove = supports.getOrDefault(brickId, Set.of());
            boolean selectable = true; 
            for (Integer brickAbove : bricksAbove) {
                Set<Integer> supportsOfAbove = supportedBy.get(brickAbove);
                if (supportsOfAbove.size() == 1) {
                    selectable = false; 
                    break; 
                }
            } 
            if (selectable) {
                 selectableBricks++; 
            } 
        } 
        return selectableBricks;
    }

    private static void dropBrick(Map<Column, Integer> heights, Brick brick){
        int maxHeight = 0;
        for(int x = brick.minX(); x <= brick.maxX(); x++){
            for(int y = brick.minY(); y <= brick.maxY(); y++){
                Column column = new Column(x, y);
                int columnHeight = heights.getOrDefault(column, 0);
                maxHeight = Math.max(maxHeight, columnHeight);
            }
        }
        int newMinZ = maxHeight + 1;
        int amountToFall = brick.minZ() - newMinZ;
        brick.moveDown(amountToFall);
        for (int x = brick.minX(); x <= brick.maxX(); x++) {
            for (int y = brick.minY(); y <= brick.maxY(); y++) {
                Column column = new Column(x, y);
                heights.put(column, brick.maxZ());
            }
        }
    }

    private static Set<Integer> findSupports(Brick brick, Map<Point, Integer> occupied) {
        Set<Integer> supports = new HashSet<>();
        int bottomZ = brick.minZ();
        for (int x = brick.minX(); x <= brick.maxX(); x++) {
            for (int y = brick.minY(); y <= brick.maxY(); y++) {
                Point below = new Point(x, y, bottomZ - 1);
                Integer supportingBrick = occupied.get(below);
                if (supportingBrick != null) {
                    supports.add(supportingBrick);
                }
            }
        }
        return supports;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getMaxBricksFalling((Day22Y2023) input));   
    }

    private Integer getMaxBricksFalling(Day22Y2023 input) {
        int total = 0;
        List<Brick> bricks = input.getBricks();
        Map<Column, Integer> heights = new HashMap<>();
        Map<Point, Integer> occupied = new HashMap<>();
        Map<Integer, Set<Integer>> supports = new HashMap<>();
        Map<Integer, Set<Integer>> supportedBy = new HashMap<>();
        for(Brick brick: bricks){
            dropBrick(heights, brick);
            Set<Integer> brickSupports = findSupports(brick, occupied);
            supportedBy.put(brick.getId(),brickSupports);
            for (Integer support : brickSupports) {
                supports.computeIfAbsent(support, key -> new HashSet<>())
                        .add(brick.getId());
            }
            for (Point cube : brick.getCubes()) {
                occupied.put(cube, brick.getId());
            }
        }
        for (Brick brick : bricks) {
            total += countFallingBricks(brick.getId(), supports, supportedBy);
        }
        return total;
    }

    private int countFallingBricks(
        int removedBrick,
        Map<Integer, Set<Integer>> supports,
        Map<Integer, Set<Integer>> supportedBy) {

        Set<Integer> fallen = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();

        fallen.add(removedBrick);
        queue.add(removedBrick);

        while (!queue.isEmpty()) {
            int brick = queue.poll();
            Set<Integer> bricksAbove =
                    supports.getOrDefault(brick, Set.of());
            for (Integer brickAbove : bricksAbove) {
                if (fallen.contains(brickAbove)) {
                    continue;
                }
                Set<Integer> brickSupports =
                        supportedBy.getOrDefault(brickAbove, Set.of());

                if (fallen.containsAll(brickSupports)) {
                    fallen.add(brickAbove);
                    queue.add(brickAbove);
                }
            }
        }
        return fallen.size() - 1;
    }

}
