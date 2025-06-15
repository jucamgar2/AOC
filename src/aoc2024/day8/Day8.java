package aoc2024.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import aoc2024.day6.Position;

public class Day8 {

    List<Position> positions;
    
    Set<String> frequencies;

    public List<Position> getPositions(){
        return this.positions;
    }

    public Set<String> getFrequences(){
        return this.frequencies;
    }

    public Position getPosition(int i, int j){
        return positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public Day8(List<Position> positions, Set<String> frequencies){
        this.positions = positions;
        this.frequencies = frequencies;
    }

    public List<Position> getAllAntennasByFrecuency(String frequency){
        return this.positions.stream().filter(pos->pos.getValue().equals(frequency)).toList();
    }

    public static Day8 getDay8Data(){
        List<Position> positions = new ArrayList<>();
        Set<String> frequencies = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day8Data.txt"))) {
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                char[] line = lines.get(i).toCharArray();
                for(int j = 0;j<line.length;j++){
                    positions.add(new Position(i, j, String.valueOf(line[j])));
                    frequencies.add(String.valueOf(line[j]));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        frequencies.removeIf(x->x.equals("."));
        return new Day8(positions, frequencies);
    }

    public Day8 deepCopy(){
        List<Position> newPositions = this.positions.stream()
            .map(Position::clone)
            .collect(Collectors.toList());
        return new Day8(newPositions, this.frequencies);
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
    
}
