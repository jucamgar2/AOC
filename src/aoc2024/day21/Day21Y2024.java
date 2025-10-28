package aoc2024.day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day21Y2024 {

    private List<String> codes;

    public List<String> getCodes(){
        return this.codes;
    }

    public Day21Y2024(List<String> codes){
        this.codes = codes;
    }

    public static Day21Y2024 getDay21Data(){
        List<String> codes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day21Data.txt"))) {
            br.lines().forEach(line->{
                codes.add(line);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day21Y2024(codes);
    }
    
}
