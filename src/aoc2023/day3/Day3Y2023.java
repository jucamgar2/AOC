package aoc2023.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Position;

public class Day3Y2023 {

    private List<Position> positions;

    public List<Position> getPositions(){
        return this.positions;
    }

    public Day3Y2023(List<Position> positions){
        this.positions = positions;
    }

    public Integer getMaxI(){
        return this.positions.stream().mapToInt(x->x.getI()).max().orElse(0);
    }

    public Integer getMaxJ(){
        return this.positions.stream().mapToInt(x->x.getJ()).max().orElse(0);
    }

    public Position getPosition(int i, int j){
        return positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public static Day3Y2023 getDay3Data(){
        List<Position> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/TestDay3.txt"))) {
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                char[] line = lines.get(i).toCharArray();
                for(int j = 0;j<line.length;j++){
                    positions.add(new Position(i, j, String.valueOf(line[j])));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day3Y2023(positions);
    }
    
}
