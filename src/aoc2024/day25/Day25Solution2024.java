package aoc2024.day25;

import java.util.List;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day25Solution2024 extends BaseDay{
    
    @Override
    protected Day25Y2024 getInputData(){
        return Day25Y2024.getDay25Data();
    }

    @Override
    public void runDaySolution(){
        Day25Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + getLockKeyPairs((Day25Y2024)input));
    }

    private static Long getLockKeyPairs(Day25Y2024 input) {
        return input.getKeys().stream().mapToLong(key->getNumOfLocksForKe(key, input.getLocks())).sum();
    }

    private static Long getNumOfLocksForKe(List<Integer> key, List<List<Integer>> locks) {
        return locks.stream().filter(lock->keyFitsInLokc(key, lock)).count();
    }

    private static Boolean keyFitsInLokc(List<Integer> key, List<Integer> lock) {
        return IntStream.range(0, 5).allMatch(index-> key.get(index) + lock.get(index)<=5 );
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("RETO CONSEGUIDO");
    }
}
