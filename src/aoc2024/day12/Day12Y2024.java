package aoc2024.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Position;

public class Day12Y2024 {
    
    private List<Position> flowers;

    private Set<String> flowerTypes;

    public List<Position> getFlowers(){
        return this.flowers;
    }

    public Set<String> getFlowerTypes(){
        return this.flowerTypes;
    }

    public Day12Y2024(List<Position> positions, Set<String> flowerTypes){
        this.flowers = positions;
        this.flowerTypes = flowerTypes;
    }

    public Position getPosition(int i, int j){
        return flowers.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public static Day12Y2024 getDay12Data(){
        List<Position> positions = new ArrayList<>();
        Set<String> flowerTypes = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay12.txt"))) {
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                char[] line = lines.get(i).toCharArray();
                for(int j = 0;j<line.length;j++){
                    positions.add(new Position(i, j, String.valueOf(line[j])));
                    flowerTypes.add(String.valueOf(line[j]));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day12Y2024(positions, flowerTypes);
    }

    public int getMaxI(){
        return getFlowers().stream().mapToInt(x->x.getI()).max().orElse(0);
    }

    public int getMaxJ(){
        return getFlowers().stream().mapToInt(x->x.getJ()).max().orElse(0);
    }

}
