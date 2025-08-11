package aoc2024.day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aoc2024.day6.Position;

public class Day16 {

    private List<Position> positions;
    
    public List<Position> getPositions(){
        return this.positions;
    }

    public Position getPosition(int i, int j){
        return this.positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public Position getEPosition(){
        return this.positions.stream().filter(x->x.getValue().equals("E")).findAny().orElse(null);
    }

    public Position getSPosition(){
        return this.positions.stream().filter(x->x.getValue().equals("S")).findAny().orElse(null);
    }

    public Day16(List<Position> positions){
        this.positions = positions;
    }

    public static Day16 getDay16Data(){
        List<Position> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay16.txt"))) {
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
        return new Day16(positions);
    }
}
