package aoc2024.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Day7Solution {

    public static void day7Solution(){
        Day7 input = Day7.getDay7Data();
        System.out.println(input.getData().size());
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
    }

    public static void part1Solution(Day7 input){
        Long res = findFinalCalibrationResult(input);
        System.out.println("SOLUCION: " + res);
    }

    public static Long findFinalCalibrationResult(Day7 input){
        return input.getData().entrySet().stream().filter(line->findLineCalibrationResult(line)>0)
                .mapToLong(line->line.getKey()).sum();
    }

    public static Long findLineCalibrationResult(Entry<Long, List<Integer>> line){
        Long key = line.getKey();
        List<Integer> value = line.getValue();
        List<Long> combinations = new ArrayList<>();
        combinations.add((long)value.get(0));
        for(int i = 1; i<value.size();i++){
            Integer number = value.get(i);
            combinations = makeCombinations(combinations, number, key);
        }
        return combinations.stream().filter(result->result.equals(key)).count();
    }

    public static List<Long> makeCombinations(List<Long> combinations, Integer number,Long key){
        List<Long> combinationsRes = new ArrayList<>();
        combinations.forEach(x->{
            combinationsRes.add(x*number);
            combinationsRes.add(x+number);
        });
        return combinationsRes;
    }

}