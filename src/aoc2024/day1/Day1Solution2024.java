package aoc2024.day1;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day1Solution2024 extends BaseDay{

    @Override
    protected Day1Y2024 getInputData() {
        return Day1Y2024.getDay1Data();
    }

    @Override
    public void runDaySolution() {
        Day1Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + calculateDistance((Day1Y2024)input));
    }

    private static Integer calculateDistance(Day1Y2024 input){
        List<Integer> firstList = input.getFirstList();
        List<Integer> secondList = input.getSecondList();
        Collections.sort(firstList);
        Collections.sort(secondList);
        return IntStream.range(0, firstList.size()).map(index->Math.abs(firstList.get(index)-secondList.get(index))).sum();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + calculateSimilarity((Day1Y2024)input));
    }

    private static Long calculateSimilarity(Day1Y2024 input){
        Set<Integer> firstList = new HashSet<Integer>(input.getFirstList());
        Map<Integer,Long> appearancesInSecondList = input.getSecondList().stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        return firstList.stream().filter(x->appearancesInSecondList.containsKey(x)).mapToLong(x->appearancesInSecondList.get(x)*x).sum();
    }
    
}
