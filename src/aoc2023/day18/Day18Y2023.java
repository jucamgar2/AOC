package aoc2023.day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day18Y2023 {

    private List<String> directions;

    private List<Integer> distances;

    private List<String> colors;
    
    public List<String> getDirections(){
        return this.directions;
    }

    public List<Integer> getDistances(){
        return this.distances;
    }

    public List<String> getColors(){
        return this.colors;
    }

    public Day18Y2023(List<String> directions, List<Integer> distances, List<String> colors){
        this.directions = directions;
        this.distances = distances;
        this.colors = colors;
    }

    public static Day18Y2023 readDay18Data(){
        List<String> directions = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day18Data.txt"))) {
            br.lines().forEach(line->{
                String [] data = line.split(" ");
                directions.add(data[0]);
                distances.add(Integer.valueOf(data[1]));
                colors.add(data[2]);
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day18Y2023(directions, distances, colors);
    }
    
}
