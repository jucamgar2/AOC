package aoc2024.day20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc2024.day6.Position;

public class Day20Solution {
    
    public static void day20Solution(){
        Day20 input = Day20.getDay20Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day20 input) {
        Integer bestJumpLimit = 100;
        System.out.println("CHEATS TO SAVE " + bestJumpLimit + " PICOSECONDS");
        System.out.println("SOLUCION: " + getNumberOfGoodCheats(input, bestJumpLimit));
    }

    private static Integer getNumberOfGoodCheats(Day20 input, Integer bestJumpLimit) {
        List<Position> path = getPath(input);
        return path.stream()
                .filter(pos -> canPositionCheat(pos, input, path))
                .mapToInt(pos->getNumberOfBestJumpsByPosition(pos, input, path, bestJumpLimit))
                .sum();
    }

    private static Boolean canPositionCheat(Position pos, Day20 input, List<Position> path) {
        return !getDirectionsToCheat(input, pos).isEmpty();
    }

    private static Integer getNumberOfBestJumpsByPosition(Position pos, Day20 input, List<Position> path, Integer bestJumpLimit) {
        Integer indexOfActualPosition = path.indexOf(pos);
        Set<Position> cheatPositions = getDirectionsToCheat(input, pos);
        Integer counterOfBestJumpsForPosition = 0;
        for(Position p: cheatPositions){
            Integer indexOfPosition = path.indexOf(p);
            if(path.contains(p)){
                if(indexOfPosition> indexOfActualPosition){
                    Integer jumpSize = indexOfPosition - indexOfActualPosition -2; 
                    if(jumpSize>= bestJumpLimit){
                        counterOfBestJumpsForPosition++;
                    }
                }
            }
        }
        return counterOfBestJumpsForPosition;
    }

    private static Set<Position> getDirectionsToCheat(Day20 input, Position pos){
        Set<Position> positionsToCheat = new HashSet<>();
        int i = pos.getI();
        int j = pos.getJ();
        addIfCheat(input, positionsToCheat, i-1, j);
        addIfCheat(input, positionsToCheat, i+1, j);
        addIfCheat(input, positionsToCheat, i, j-1);
        addIfCheat(input, positionsToCheat, i, j+1);
        return positionsToCheat;
    }

    private static void addIfCheat(Day20 input, Set<Position> positionsToCheat,
                               int i1, int j1) {
        Position first = input.getPosition(i1, j1);
        if (first != null && first.getValue().equals("#")){
            positionsToCheat.addAll(getNeighbors(input, first));
        }
    }

    private static List<Position> getPath(Day20 input){
        List<Position> path = new ArrayList<>();
        Position p = input.getSPosition();
        path.add(p);
        while(p!=input.getEPosition()){
            for(Position neighbor: getNeighbors(input, p)){
                if(!path.contains(neighbor)){
                    path.add(neighbor);
                    p = neighbor;   
                }
            }
        }
        return path;
    }

    private static List<Position> getNeighbors(Day20 input, Position p){
        int i = p.getI();
        int j = p.getJ();
        List<Position> possibleNeighbors = new ArrayList<>();
        Position up = input.getPosition(i-1, j);
        Position down = input.getPosition(i+1, j);
        Position left = input.getPosition(i, j-1);
        Position right = input.getPosition(i, j+1);
        possibleNeighbors.add(left);
        possibleNeighbors.add(down);
        possibleNeighbors.add(right);
        possibleNeighbors.add(up);
        return possibleNeighbors.stream().filter(pos->pos!=null && (pos.getValue().equals(".") || pos.getValue().equals("E"))).toList();
    }

    private static void part2Solution(Day20 input) {
        Integer bestJumpLimit =100;
        System.out.println("CHEATS TO SAVE " + bestJumpLimit + " PICOSECONDS");
        System.out.println("SOLUCION: " + getNumberOfGoodCheatsPart2(input, bestJumpLimit));
    }

    private static Integer getNumberOfGoodCheatsPart2(Day20 input, Integer bestJumpLimit){
        List<Position> path = getPath(input);
        return path.stream()
                .mapToInt(pos->getNumberOfBestJumpsByPositionPart2(pos, input, path, bestJumpLimit))
                .sum();
    }

    private static Integer getNumberOfBestJumpsByPositionPart2(Position pos, Day20 input, List<Position> path, Integer bestJumpLimit) {
        Integer indexOfActualPosition = path.indexOf(pos);
        List<Position> cheatPositions = getPositionsInCheatRange(pos, input);
        Integer counterOfBestJumpsForPosition = 0;
        for(Position p: cheatPositions){
            Integer indexOfPosition = path.indexOf(p);
            if(path.contains(p)){
                if(indexOfPosition> indexOfActualPosition){
                    Integer cheatSize = Math.abs(pos.getI() - p.getI()) + Math.abs(pos.getJ() - p.getJ());
                    Integer jumpSize = indexOfPosition - indexOfActualPosition - cheatSize; 
                    if(jumpSize>= bestJumpLimit){
                        counterOfBestJumpsForPosition++;
                    }
                }
            }
        }
        return counterOfBestJumpsForPosition;
    }

    private static List<Position> getPositionsInCheatRange(Position p, Day20 input){
        return input.getGrid().stream()
                        .filter(position-> position.getValue().equals(".") || position.getValue().equals("E"))
                        .filter(position-> isPositionInCheatRange(p, position)).toList();
    }

    private static Boolean isPositionInCheatRange(Position p, Position cheatP){
        Integer pi = p.getI();
        Integer pj = p.getJ();
        Integer ci = cheatP.getI();
        Integer cj = cheatP.getJ();
        Integer di = Math.abs(pi-ci);
        Integer dj = Math.abs(pj-cj);
        return di + dj <=20;
    }
}
