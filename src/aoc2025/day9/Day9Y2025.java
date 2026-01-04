package aoc2025.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Position;

public class Day9Y2025 {
    
    private List<Position> possibleLocations;
    
    public List<Position> getPossibleLocations(){
        return this.possibleLocations;
    }

    public Day9Y2025(List<Position> possibleLocations){
        this.possibleLocations = possibleLocations;
    }

    public static Day9Y2025 getDay9Data(){
        List<Position> possibleLocations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/Day9Data.txt"))) {
            br.lines().forEach(line->{
                String[] coord = line.trim().split(",");
                Position newPosition = new Position(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]), "");
                possibleLocations.add(newPosition);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day9Y2025(possibleLocations);
    }
}
