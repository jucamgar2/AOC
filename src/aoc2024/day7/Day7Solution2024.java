package aoc2024.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import structure.BaseDay;

public class Day7Solution2024 extends BaseDay{

    @Override
    protected Day7Y2024 getInputData(){
        return Day7Y2024.getDay7Data();
    }

    @Override
    public void runDaySolution(){
        Day7Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input){
        Long res = findFinalCalibrationResult((Day7Y2024)input);
        System.out.println("SOLUCION: " + res);
    }

    public static Long findFinalCalibrationResult(Day7Y2024 input){
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

    @Override
    protected void part2Solution(Object input){
        Long res = findFinalCalibrationResultWith3Operations((Day7Y2024)input);
        System.out.println("SOLUCION: " + res);
    }

    public static Long findFinalCalibrationResultWith3Operations(Day7Y2024 input){
        return input.getData().entrySet().stream().filter(line->findLineCalibrationResult3Ops(line)>0)
                .mapToLong(line->line.getKey()).sum();
    }

    public static Long findLineCalibrationResult3Ops(Entry<Long, List<Integer>> line){
        Long key = line.getKey();
        List<Integer> value = line.getValue();
        List<Long> combinations = new ArrayList<>();
        combinations.add((long)value.get(0));
        for(int i = 1; i<value.size();i++){
            Integer number = value.get(i);
            combinations = makeCombinations3Ops(combinations, number, key);
        }
        return combinations.stream().filter(result->result.equals(key)).count();
    }

    public static List<Long> makeCombinations3Ops(List<Long> combinations, Integer number,Long key){
        List<Long> combinationsRes = new ArrayList<>();
        combinations.forEach(x->{
            combinationsRes.add(x*number);
            combinationsRes.add(x+number);
            combinationsRes.add(Long.parseLong(x.toString()+number.toString()));
        });
        return combinationsRes;
    }


}