package aoc2024.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

    private Map<Long, List<Integer>> data;

    public Day7(Map<Long,List<Integer>> data){
        this.data = data;
    }

    public Map<Long, List<Integer>> getData(){
        return this.data;
    }

    public static Day7 getDay7Data(){
        Map<Long,List<Integer>> data = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader("Day7Data.txt"))){
            br.lines().forEach(line->{
                List<Integer> combinationNumbers = new ArrayList<>();
                Long numberResult = Long.parseLong(line.split(":")[0]);
                String[] numbers = line.split(":")[1].trim().split("\\s+");
                for(String number : numbers){
                    if(!number.isEmpty()){
                        combinationNumbers.add(Integer.parseInt(number));
                    }
                }
                data.put(numberResult, combinationNumbers);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day7(data);
    }
    
    
}
