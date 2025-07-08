package aoc2024.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day11 {

    private String stones;
    
    public String getStones(){
        return this.stones;
    }

    public Day11(String stones){
        this.stones = stones;
    }

    public static Day11 getDay11Data(){
        StringBuilder stones = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay11.txt"))) {
            br.lines().forEach(line->{
                stones.append(line);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day11(stones.toString());
    }
}
