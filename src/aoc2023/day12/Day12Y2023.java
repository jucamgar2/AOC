package aoc2023.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12Y2023 {

    private List<String> springs;

    private List<List<Integer>> damagedSprings;

    public List<String> getSprings(){
        return this.springs;
    }

    public List<List<Integer>> getDamagedSprings(){
        return this.damagedSprings;
    }

    public Day12Y2023(List<String> springs, List<List<Integer>> damagedSprings){
        this.damagedSprings = damagedSprings;
        this.springs = springs;
    }

    public static Day12Y2023 readDay12Data(){
        List<String> springs = new ArrayList<>();
        List<List<Integer>> damagedSprings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day12Data.txt"))) {
            br.lines().forEach(line->{
                springs.add(line.split(" ")[0]);
                List<Integer> damagedSpring = new ArrayList<>();
                Arrays.asList(line.split(" ")[1].split(",")).forEach(num->damagedSpring.add(Integer.valueOf(num)));
                damagedSprings.add(damagedSpring);
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day12Y2023(springs, damagedSprings);
    } 
    
}
