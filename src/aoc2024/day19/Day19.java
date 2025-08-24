package aoc2024.day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day19 {

    private List<String> towelPatterns;

    private List<String> expectedDesigns;

    public List<String> getTowelPatterns(){
        return this.towelPatterns;
    }

    public List<String> getExpectedDesigns(){
        return this.expectedDesigns;
    }

    public Day19(List<String> towelPatterns, List<String> expectedDesigns){
        this.towelPatterns = towelPatterns;
        this.expectedDesigns = expectedDesigns;
    }

    public static Day19 getDay19Data(){
        List<String> towelPatterns = new ArrayList<>();
        List<String> expectedDesigns = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay19.txt"))) {
            List<String> lines = new ArrayList<>(br.lines().toList());
            for(int i = 0; i<lines.size(); i++){
                String line = lines.get(i);
                if(i==0){
                    String[] patterns = line.split(",");
                    towelPatterns = Arrays.stream(patterns).map(String::trim).toList();
                }else if(i>1){
                    expectedDesigns.add(line);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day19(towelPatterns, expectedDesigns);
    }


    
}
