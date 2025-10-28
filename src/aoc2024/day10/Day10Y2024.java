package aoc2024.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.IntPosition;

public class Day10Y2024 {

    private List<IntPosition> positions;

    private List<IntPosition> startPositions;

    public Day10Y2024(List<IntPosition> positions, List<IntPosition> startPositions){
        this.positions = positions;
        this.startPositions = startPositions;
    }

    public List<IntPosition> getPositions(){
        return this.positions;
    }

    public List<IntPosition> getStartPositions(){
        return this.startPositions;
    }

    public IntPosition getPosition(int i, int j){
        return positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public static Day10Y2024 getDay10Data(){
        List<IntPosition> positions = new ArrayList<>();
        List<IntPosition> startPositions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day10Data.txt"))) {
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                char[] line = lines.get(i).toCharArray();
                for(int j = 0;j<line.length;j++){
                    int value = Integer.MAX_VALUE;
                    if(!String.valueOf(line[j]).equals(".")){
                        value = Integer.parseInt(String.valueOf(line[j]));
                    }
                    positions.add(new IntPosition(i, j, value));
                    if(value==0){
                        startPositions.add(new IntPosition(i, j, value));
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day10Y2024(positions,startPositions);
    }
    
}
