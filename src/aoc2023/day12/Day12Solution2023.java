package aoc2023.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day12Solution2023 extends BaseDay{

    @Override
    protected Day12Y2023 getInputData() {
        return Day12Y2023.readDay12Data();
    }

    @Override
    public void runDaySolution() {
        Day12Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getSumOfCounts((Day12Y2023) input, false));
    }

    private Long getSumOfCounts(Day12Y2023 input, boolean unfolded) {
        return IntStream.range(0, input.getSprings().size())
                .mapToLong(index->getNumOfArrangements(input.getSprings().get(index), input.getDamagedSprings().get(index), unfolded))
                .sum();
    }

    private Long getNumOfArrangements(String spring, List<Integer> damagedSpring, boolean unfolded) {
        if(unfolded){
            spring = spring+"?"+spring+"?"+spring+"?"+spring+"?"+spring;
            List<Integer> aux = new ArrayList<>(damagedSpring);
            for(int i = 0; i<4;i++){
                damagedSpring.addAll(aux);
            }
        }
        Map<String, Long> memo = new HashMap<>();
        return dfs(spring, damagedSpring, 0, 0, 0, memo);
    }

    private Long dfs(String s, List<Integer> groups, int i, int g, int len, Map<String, Long> memo) {
        String key = i + "-" + g + "-" + len;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (i == s.length()) {
            if (len == 0 && g == groups.size()) {
                return 1L;
            }
            if (g == groups.size() - 1 && len == groups.get(g)) {
                return 1L;
            }
            return 0L;
        }
        long total = 0;
        char c = s.charAt(i);
        if (c == '.' || c == '?') {
            if (len == 0) {
                total += dfs(s, groups, i + 1, g, 0, memo);
            } else {
                if (g < groups.size() && len == groups.get(g)) {
                    total += dfs(s, groups, i + 1, g + 1, 0, memo);
                }
            }
        }
        if (c == '#' || c == '?') {
            if (g < groups.size()) {
                if (len < groups.get(g)) {
                    total += dfs(s, groups, i + 1, g, len + 1, memo);
                }
            }
        }

        memo.put(key, total);
        return total;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getSumOfCounts((Day12Y2023) input, true));
    }
    
}
