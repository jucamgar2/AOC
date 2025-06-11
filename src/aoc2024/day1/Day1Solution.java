package aoc2024.day1;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1Solution {

    public static void day1Solution(){
        Day1 input = Day1.getDay1Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day1 input){
        System.out.println("SOLUCION: " + calculateDistance(input));
    }

    private static Integer calculateDistance(Day1 input){
        List<Integer> firstList = input.getFirstList();
        List<Integer> secondList = input.getSecondList();
        Collections.sort(firstList);
        Collections.sort(secondList);
        return IntStream.range(0, firstList.size()).map(index->Math.abs(firstList.get(index)-secondList.get(index))).sum();
    }

    private static void part2Solution(Day1 input){
        System.out.println("SOLUCION: " + calculateSimilarity(input));
    }

    private static Long calculateSimilarity(Day1 input){
        Set<Integer> firstList = new HashSet<Integer>(input.getFirstList());
        Map<Integer,Long> appearancesInSecondList = input.getSecondList().stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        return firstList.stream().filter(x->appearancesInSecondList.containsKey(x)).mapToLong(x->appearancesInSecondList.get(x)*x).sum();
    }
    
}
