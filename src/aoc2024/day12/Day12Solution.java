package aoc2024.day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc2024.day6.Position;

public class Day12Solution {
    
    public static void day12Solution(){
        Day12 input = Day12.getDay12Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    public static void part1Solution(Day12 input){ 
        System.out.println("SOLUCION: " + getPriceOfFencing(input));
    }

    private static List<Set<Position>> getRegions(Day12 input){
        List<Set<Position>> regions = new ArrayList<>();
        List<Position> visited = new ArrayList<>();
        int maxI = input.getMaxI();
        int maxJ = input.getMaxJ();
        for(int i = 0; i<=maxI;i++){
            for(int j = 0; j<=maxJ; j++){
                Set<Position> region = new HashSet<>();
                addToRegion(input.getPosition(i, j), input, region, visited);
                if(!region.isEmpty()){
                    regions.add(region);
                }
            }
        }
        return regions;
    }

    private static void addToRegion(Position p, Day12 input, Set<Position> region, List<Position> visited){
        if(!visited.contains(p)){
            List<Position> positions = getArroundFlowersOfTheRegion(p, input.getFlowers());
            visited.add(p);
            region.add(p);
            for(Position position: positions){
                addToRegion(position, input, region, visited);
            }
        }
        
    }

    private static List<Position> getArroundFlowersOfTheRegion(Position p, List<Position> positions){
        return positions.stream().filter(position->isPositionNeighborOfRegion(p, position)).filter(position-> position.getValue().equals(p.getValue())).toList();
    }

    private static boolean isPositionNeighborOfRegion(Position p, Position position){
        return (Math.abs(p.getI() - position.getI())==1 && p.getJ()-position.getJ() == 0 )||
        (Math.abs(p.getJ()-position.getJ()) ==1 && p.getI()-position.getI() == 0);
    }

    private static Long getPriceOfFencing(Day12 input){
        List<Set<Position>> regions = getRegions(input);
        return regions.stream().mapToLong(region->getArea(region, input)*getPerimeter(region, input)).sum();
    }

    private static Long getPerimeter(Set<Position> region, Day12 input) {
        return region.stream().mapToLong(flower->getDifferentFlowersArround(flower, input)).sum();
    }

    private static long getArea(Set<Position> region, Day12 input) {
        return region.size();
    }

    private static long getDifferentFlowersArround(Position flower, Day12 input){
        int i = flower.getI();
        int j = flower.getJ();
        Position upPosition = input.getPosition(i-1, j);
        Position downPosition = input.getPosition(i+1, j);
        Position leftPosition = input.getPosition(i, j-1);
        Position rightPosition = input.getPosition(i, j+1);
        List<Position> arroundPositions = new ArrayList<>();
        arroundPositions.add(upPosition);
        arroundPositions.add(downPosition);
        arroundPositions.add(leftPosition);
        arroundPositions.add(rightPosition);
        return arroundPositions.stream()
            .filter(f -> f == null || !f.getValue().equals(flower.getValue()))
            .count();
    }

    public static void part2Solution(Day12 input){ 
        System.out.println("SOLUCION: " + getPriceOfFencingPart2(input));
    }

    private static Long getPriceOfFencingPart2(Day12 input){
        List<Set<Position>> regions = getRegions(input);
        return regions.stream().mapToLong(region->getArea(region, input)*getRegionSides(region, input)).sum();
    }

    private static long getRegionSides(Set<Position> region, Day12 input) {
        int sides = 0;
        for(Position position:region){
            sides+=countVertices(position, input);
        }
        return sides;
    }

    private static int countVertices(Position p, Day12 input){
        int i = p.getI();
        int j = p.getJ();
        String type = p.getValue();
        int sides = 0;

        boolean up    = sameType(input.getPosition(i - 1, j), type);
        boolean down  = sameType(input.getPosition(i + 1, j), type);
        boolean left  = sameType(input.getPosition(i, j - 1), type);
        boolean right = sameType(input.getPosition(i, j + 1), type);

        boolean ul = sameType(input.getPosition(i - 1, j - 1), type);
        boolean ur = sameType(input.getPosition(i - 1, j + 1), type); 
        boolean dl = sameType(input.getPosition(i + 1, j - 1), type); 
        boolean dr = sameType(input.getPosition(i + 1, j + 1), type); 

        if (!right && !down) sides++;
        if (!down && !left) sides++;
        if (!left && !up) sides++;
        if (!up && !right) sides++;
        if (right && down && !dr) sides++;
        if (down && left && !dl) sides++;
        if (left && up && !ul) sides++;
        if (up && right && !ur) sides++;

        return sides;
    }

    private static boolean sameType(Position pos, String type) {
        return pos != null && pos.getValue().equals(type);
    }
}