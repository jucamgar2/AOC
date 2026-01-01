package aoc2025.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.SpacePosition;

public class Day8Y2025 {

    private List<SpacePosition> junctions;

    public List<SpacePosition> getJunctions(){
        return this.junctions;
    }

    public Day8Y2025(List<SpacePosition> junctions){
        this.junctions = junctions;
    }

    public static Day8Y2025 getDay8Data(){
        List<SpacePosition> junctions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/TestDay8.txt"))) {
            br.lines().forEach(line->{
                String[] coords = line.split(",");
                Integer x = Integer.parseInt(coords[0]);
                Integer y = Integer.parseInt(coords[1]);
                Integer z = Integer.parseInt(coords[2]);
                junctions.add(new SpacePosition(x,y,z));
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day8Y2025(junctions);
    }
    
}
