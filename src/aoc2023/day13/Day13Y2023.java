package aoc2023.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Position;
import utils.PositionBoard;

public class Day13Y2023 {

    private List<PositionBoard> mirrorMaps;

    public List<PositionBoard> getMirrorMaps(){
        return this.mirrorMaps;
    }

    public Day13Y2023(List<PositionBoard> mirrorMaps){
        this.mirrorMaps = mirrorMaps;
    }

    public static Day13Y2023 readDay13Data(){
        List<PositionBoard> mirrorMaps = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day13Data.txt"))) {
            List<String> lines = br.lines().toList();
            int i = 0;
            List<Position> positions = new ArrayList<>();
            int emptyLines = 0;
            for(int x = 0;x<lines.size();x++){
                if(!lines.get(x).isEmpty()){
                    char[] line = lines.get(x).toCharArray();
                    for(int j = 0;j<line.length;j++){
                        positions.add(new Position(i, j, String.valueOf(line[j])));
                    }
                    i++;
                }else{
                    mirrorMaps.add(new PositionBoard(positions));
                    i = 0;
                    positions = new ArrayList<>();
                    emptyLines++;
                }   
            }
            mirrorMaps.add(new PositionBoard(positions));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day13Y2023(mirrorMaps);
    }
    
}
