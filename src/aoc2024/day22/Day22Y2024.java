package aoc2024.day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day22Y2024 {
    
    private List<Long> secretCodes;

    public List<Long> getSecretCodes(){
        return this.secretCodes;
    }

    public Day22Y2024(List<Long> secretCodes){
        this.secretCodes = secretCodes;
    } 

    public static Day22Y2024 getDay22Data(){
        List<Long> secretCodes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay22.txt"))) {
            br.lines().forEach(line->{
                secretCodes.add(Long.parseLong(line));
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day22Y2024(secretCodes);
    }
}
