package aoc2025.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3Y2025 {

    private List<String> banks;

    public List<String> getBanks(){
        return this.banks;
    }

    public Day3Y2025(List<String> banks){
        this.banks = banks;
    }

    public static Day3Y2025 getDay3Data(){
        List<String> banks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/TestDay3.txt"))) {
            br.lines().forEach(line->{
                banks.add(line);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day3Y2025(banks);
    }
   
}
