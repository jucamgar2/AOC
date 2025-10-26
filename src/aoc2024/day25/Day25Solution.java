package aoc2024.day25;

import java.util.List;
import java.util.stream.IntStream;

public class Day25Solution {
    
    public static void getDay25Solution(){
        Day25 input = Day25.getDay25Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day25 input) {
        System.out.println("SOLUCION: " + getLockKeyPairs(input));
    }

    private static Long getLockKeyPairs(Day25 input) {
        return input.getKeys().stream().mapToLong(key->getNumOfLocksForKe(key, input.getLocks())).sum();
    }

    private static Long getNumOfLocksForKe(List<Integer> key, List<List<Integer>> locks) {
        return locks.stream().filter(lock->keyFitsInLokc(key, lock)).count();
    }

    private static Boolean keyFitsInLokc(List<Integer> key, List<Integer> lock) {
        return IntStream.range(0, 5).allMatch(index-> key.get(index) + lock.get(index)<=5 );
    }

    private static void part2Solution(Day25 input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'part2Solution'");
    }
}
