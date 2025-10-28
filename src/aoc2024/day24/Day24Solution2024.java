package aoc2024.day24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import structure.BaseDay;
import utils.BitOperation;

public class Day24Solution2024 extends BaseDay{

    @Override
    protected Day24Y2024 getInputData(){
        return Day24Y2024.getDay24Data();
    }
    
    @Override
    public void runDaySolution(){
        Day24Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        input = getInputData();
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + getZwiresOutput((Day24Y2024)input));
    }

    private static Long getZwiresOutput(Day24Y2024 input) {
        Map<String,Integer> finalValues = getFinalWireValues(input.getValues(), input.getOperations());
        Map<String, Integer> sortedMap =  finalValues.entrySet().stream()
            .filter(entry -> entry.getKey() != null && entry.getKey().toLowerCase().startsWith("z"))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (v1, v2) -> v1, 
                    () -> new TreeMap<>(Comparator.reverseOrder())
            ));
        String output = sortedMap.values().stream()
        .map(String::valueOf)
        .collect(Collectors.joining());
        return Long.parseLong(output, 2);
    }

    private static Map<String, Integer> getFinalWireValues(Map<String, Integer> finalValues, Map<String, BitOperation> operations) {
        while (!operations.isEmpty()) {
            Map<String, BitOperation> solvedOperations = new HashMap<>();
            for(String wire : operations.keySet()) {
                BitOperation operation = operations.get(wire);
                if(finalValues.containsKey(operation.getWire1()) && finalValues.containsKey(operation.getWire2())){
                    Integer value = getOperationValue(operation, finalValues);
                    finalValues.put(wire, value);
                    solvedOperations.put(wire, operation);
                }
            }
            solvedOperations.keySet().stream().forEach(key->operations.remove(key, solvedOperations.get(key)));
        }
        return finalValues;
    }

    private static Integer getOperationValue(BitOperation operation, Map<String, Integer> finalValues) {
        Integer wire1 = finalValues.get(operation.getWire1());
        Integer wire2 = finalValues.get(operation.getWire2());
        switch (operation.getOperation()) {
            case "OR":
                return (wire1 == 1 || wire2 == 1)? 1:0;
            case "AND":
                return (wire1 == 1 && wire2 == 1)? 1:0;           
            case "XOR":
                return (wire1 + wire2 ==1)? 1:0;      
            default:
                return 0;
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getWiresToSwap((Day24Y2024)input));
    }

    private static String getWiresToSwap(Day24Y2024 input) {
        Map<String, BitOperation> operations = input.getOperations();
        List<String> swaps = findSwaps(operations);
        return String.join(",", swaps);
    }

    private static List<String> findSwaps(Map<String, BitOperation> ops) {
        List<String> swaps = new ArrayList<>();
        for (int round = 0; round < 4; round++) {
            int baseline = progress(ops);
            for (String a : ops.keySet()) {
                for (String b : ops.keySet()) {
                    if (a.equals(b)) continue;
                    BitOperation tmp = ops.get(a);
                    ops.put(a, ops.get(b));
                    ops.put(b, tmp);
                    int newScore = progress(ops);
                    if (newScore > baseline) {
                        swaps.add(a);
                        swaps.add(b);
                        baseline = newScore;
                        break;
                    }
                    tmp = ops.get(a);
                    ops.put(a, ops.get(b));
                    ops.put(b, tmp);
                }
            }
        }
        Collections.sort(swaps);
        return swaps;
    }

    private static int progress(Map<String, BitOperation> ops) {
        int bit = 0;
        while (verifyZ("z" + String.format("%02d", bit), bit, ops)) {
            bit++;
        }
        return bit;
    }

    private static boolean verifyZ(String wire, int bit, Map<String, BitOperation> ops) {
        BitOperation op = ops.get(wire);
        if (op == null) return false;
        if (!op.getOperation().equals("XOR")) return false;

        String left = op.getWire1();
        String right = op.getWire2();

        if (bit == 0) {
            return Set.of(left, right).equals(Set.of("x00", "y00"));
        }
        return (verifyIntermediateXor(left, bit, ops) && verifyCarry(right, bit, ops))
            || (verifyIntermediateXor(right, bit, ops) && verifyCarry(left, bit, ops));
    }

    private static boolean verifyIntermediateXor(String wire, int bit, Map<String, BitOperation> ops) {
        BitOperation op = ops.get(wire);
        if (op == null || !op.getOperation().equals("XOR")) return false;
        return Set.of(op.getWire1(), op.getWire2())
            .equals(Set.of("x" + String.format("%02d", bit), "y" + String.format("%02d", bit)));
    }

    private static boolean verifyDirectCarry(String wire, int bit, Map<String, BitOperation> ops) {
        BitOperation op = ops.get(wire);
        if (op == null || !op.getOperation().equals("AND")) return false;
        return Set.of(op.getWire1(), op.getWire2())
            .equals(Set.of("x" + String.format("%02d", bit), "y" + String.format("%02d", bit)));
    }

    private static boolean verifyCarry(String wire, int bit, Map<String, BitOperation> ops) {
        BitOperation op = ops.get(wire);
        if (op == null) return false;

        if (bit == 1) {
            return op.getOperation().equals("AND")
                && Set.of(op.getWire1(), op.getWire2()).equals(Set.of("x00", "y00"));
        }

        if (!op.getOperation().equals("OR")) return false;
        return (verifyDirectCarry(op.getWire1(), bit - 1, ops) && verifyReCarry(op.getWire2(), bit - 1, ops))
            || (verifyDirectCarry(op.getWire2(), bit - 1, ops) && verifyReCarry(op.getWire1(), bit - 1, ops));
    }

    private static boolean verifyReCarry(String wire, int bit, Map<String, BitOperation> ops) {
        BitOperation op = ops.get(wire);
        if (op == null || !op.getOperation().equals("AND")) return false;
        return (verifyIntermediateXor(op.getWire1(), bit, ops) && verifyCarry(op.getWire2(), bit, ops))
            || (verifyIntermediateXor(op.getWire2(), bit, ops) && verifyCarry(op.getWire1(), bit, ops));
    }

}
