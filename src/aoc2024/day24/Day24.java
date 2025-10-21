package aoc2024.day24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day24 {

    private Map<String, Integer> values;

    private Map<String, BitOperation> operations;

    public Map<String, Integer> getValues(){
        return this.values;
    }

    public Map<String, BitOperation> getOperations(){
        return this.operations;
    }
    
    public Day24(Map<String, Integer> values, Map<String, BitOperation> operations){
        this.values = values;
        this.operations = operations;
    }

    public static Day24 getDay24Data(){
        Map<String, Integer> values = new HashMap<>();
        Map<String, BitOperation> operations = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay24.txt"))) {
            br.lines().forEach(line->{
                if(line.contains(":")){
                    String[] value = line.split(":");
                    values.put(value[0], Integer.parseInt(value[1].trim()));
                }else if(line.contains("->")){
                    String[] operation = line.split("->");
                    operations.put(operation[1].trim(),new BitOperation(operation[0]));
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day24(values, operations);
    }
}
