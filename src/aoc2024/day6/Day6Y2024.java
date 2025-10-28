package aoc2024.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utils.Position;

public class Day6Y2024 {
    
    List<Position> positions;

    public Day6Y2024(List<Position> positions){
        this.positions = positions;
    }

    public List<Position> getPositions(){
        return this.positions;
    }

    public Position getCurrentPosition(){
        return positions.stream().filter(position->position.getValue().equals("^")).findFirst().orElse(null);
    }

    public Position getPosition(int i, int j){
        return positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public static Day6Y2024 getDay6Data(){
        List<Position> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay6.txt"))) {
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
        return new Day6Y2024(positions);
    }

    public String toString(){
        Integer maxI = getPositions().stream().mapToInt(x->x.getI()).max().orElse(0);
        Integer maxJ = getPositions().stream().mapToInt(x->x.getJ()).max().orElse(0);
        String res = "";
        for(int i = 0;i<=maxI;i++){
            for(int j=0;j<maxJ;j++){
                res += getPosition(i, j).getValue();
            }
            res+="\n";
        }
        return res;
    }

    public Day6Y2024 deepCopy() {
        List<Position> newPositions = this.positions.stream()
            .map(Position::clone)
            .collect(Collectors.toList());
        return new Day6Y2024(newPositions);
    }
}
