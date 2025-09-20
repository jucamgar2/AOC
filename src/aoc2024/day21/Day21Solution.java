package aoc2024.day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day21Solution {

    public static void day21Solution(){
        Day21 input = Day21.getDay21Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        input = Day21.getDay21Data();
        part2Solution(input);
    }

    private static void part1Solution(Day21 input) {
        System.out.println("SOLUCIÓN: " + getSumOfComplexities(input, 2));
    }

    private static long getSumOfComplexities(Day21 input, int maxDepth) {
        NumericPad numericPad = new NumericPad();
        MovePad movePad = new MovePad();
        Map<NodePair, List<String>> numericPadPaths = numericPad.getGraph().buildPathsForNodePair();
        Map<NodePair, List<String>> movePadPaths = movePad.getMovePad().buildPathsForNodePair();
        long total = 0;
        Map<String, Long> cache = new HashMap<>();
        for (String keys : input.getCodes()) {
            List<String> numericSequences = buildSeq(keys, numericPadPaths);
            long best = Long.MAX_VALUE;
            for (String seq : numericSequences) {
                best = Math.min(best, shortestSeq(seq, maxDepth, cache, movePadPaths));
            }
            int numPart = Integer.parseInt(keys.substring(0, keys.length() - 1));
            total += best * numPart;
        }
        return total;
    }

    private static long shortestSeq(String keys, int depth,
                                Map<String, Long> cache,
                                Map<NodePair, List<String>> padPaths) {
        if (depth == 0) {
            return keys.length();
        }
        String cacheKey = keys + "|" + depth;
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        long total = 0;
        String[] subKeys = keys.split("A");
        for (String subKey : subKeys) {
            subKey+="A";
            if (subKey.isEmpty()) continue;
            List<String> sequences = buildSeq(subKey, padPaths);
            long best = Long.MAX_VALUE;
            for (String seq : sequences) {
                best = Math.min(best, shortestSeq(seq, depth - 1, cache, padPaths));
            }
            total += best;
        }
        cache.put(cacheKey, total);
        return total;
    }

    private static List<String> buildSeq(String code,
                                     Map<NodePair, List<String>> padPaths) {
        List<String> sequences = new ArrayList<>();
        for (int i = 0; i < code.length(); i++) {
            String from, to;
            if (i == 0) {
                from = "A";
                to = String.valueOf(code.charAt(i));
            } else {
                from = String.valueOf(code.charAt(i - 1));
                to = String.valueOf(code.charAt(i));
            }
            NodePair pair = new NodePair(new Node(from), new Node(to));
            List<String> paths = padPaths.get(pair);
            if (i == 0) {
                sequences.addAll(paths);
            } else {
                List<String> newSeq = new ArrayList<>();
                for (String existing : sequences) {
                    for (String path : paths) {
                        newSeq.add(existing + path);
                    }
                }
                sequences = newSeq;
            }
        }
        int minLen = sequences.stream().mapToInt(String::length).min().orElse(Integer.MAX_VALUE);
        return sequences.stream()
                .filter(s -> s.length() == minLen)
                .collect(Collectors.toList());
    }

    private static void part2Solution(Day21 input) {
        System.out.println("SOLUCIÓN: " + getSumOfComplexities(input, 25));
    }
}
