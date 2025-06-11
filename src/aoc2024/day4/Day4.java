package aoc2024.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aoc2024.day6.Position;

public class Day4 {
 
    List<Position> positions;

    public Day4(List<Position> positions){
        this.positions = positions;
    }

    public List<Position> getPositions(){
        return this.positions;
    }

    public Position getPosition(int i, int j){
        return positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public String getValueP(int i, int j){
        Position p =  positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
        return p!=null?p.getValue():".";
    }

    public static Day4 getDay4Data(){
        List<Position> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day4Data.txt"))){
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                char[] line = lines.get(i).toCharArray();
                for(int j = 0;j<line.length;j++){
                    positions.add(new Position(i, j, String.valueOf(line[j])));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day4(positions);
    }
}
