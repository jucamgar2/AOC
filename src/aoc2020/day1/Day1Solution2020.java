package aoc2020.day1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day1Solution2020 extends BaseDay{

    @Override
    protected Day1Y2020 getInputData() {
        return Day1Y2020.getDay1Data();
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + findMultiplyOf2020Sum((Day1Y2020)input));
    }

    private Long findMultiplyOf2020Sum(Day1Y2020 input) {
        Set<Integer> substractResult = new HashSet<>();
        return input.getExpenseReport().stream().filter(num->{
            Integer substraction = 2020-num;
            if(substractResult.contains(substraction)){
                return true;
            }else{
                substractResult.add(num);
                return false;
            }
        })
        .mapToLong(num->(2020-num)*num)
        .findFirst().orElse(0);
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + findMultiplyOf3Sum2020((Day1Y2020)input));
    }

    private Integer findMultiplyOf3Sum2020(Day1Y2020 input) {
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        IntStream.range(0, input.getExpenseReport().size()).forEach(index1->{
            IntStream.range(index1, input.getExpenseReport().size()).forEach(index2 ->{
                Integer num = input.getExpenseReport().get(index1);
                Integer num2 = input.getExpenseReport().get(index2);
                if(visited.keySet().contains(num)){
                    visited.get(num).add(num);
                    visited.put(0, visited.get(num));
                }else if(visited.keySet().contains(num2)){
                    visited.get(num2).add(num2);
                    visited.put(0, visited.get(num2));
                }else{
                    Integer substraction = 2020-num-num2;
                    visited.put(substraction, new HashSet<>(Arrays.asList(num, num2)));
                }
            });
        });
        System.out.println(visited.get(0));
        return visited.get(0).stream().reduce(1, (a,b)->a*b);
    }

    @Override
    public void runDaySolution() {
        Day1Y2020 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }
    
}
