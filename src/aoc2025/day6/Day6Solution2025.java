package aoc2025.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day6Solution2025 extends BaseDay{

    @Override
    protected Day6Y2025 getInputData() {
        return Day6Y2025.getDay6Data();
    }

    @Override
    public void runDaySolution() {
        Day6Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        Day6Y2025 day6 = (Day6Y2025) input;
        System.out.println("SOLUCIÓN: " + getGrandTotal(day6.getOperands(), day6.getOperations()));
    }

    private Long getGrandTotal(List<List<Integer>> operands, List<String> operations) {
        return IntStream.range(0,operands.size())
                        .mapToLong(index->getOperationResult(index, operands, operations))
                        .sum();
    }

    private Long getOperationResult(int index, List<List<Integer>> operands, List<String> operations) {
        if(operations.get(index).trim().equals("*")){
            return operands.get(index).stream().mapToLong(Long::valueOf).reduce(1L,(a,b)-> a*b);
        }else{
            return operands.get(index).stream().mapToLong(Long::valueOf).sum();
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: "+ getGrandTotalRL((Day6Y2025) input));
    }

    private Long getGrandTotalRL(Day6Y2025 input) {
        List<List<Integer>> newOperands = transformOperands(input);
        return getGrandTotal(newOperands, input.getOperations());
    }
    private List<List<Integer>> transformOperands(Day6Y2025 input) {
        List<List<String>> columns = new ArrayList<>();
        int index = 0;
        for(String op: input.getOperations()){
            List<String> column = new ArrayList<>();
            final int currentIndex = index;
            if(index+op.length()<input.getOperandWithSpaces().get(0).length()){
                input.getOperandWithSpaces().forEach(operand->column.add(operand.substring(currentIndex, currentIndex+op.length()-1)));
            }else{
                input.getOperandWithSpaces().forEach(operand->column.add(operand.substring(currentIndex, currentIndex+op.length())));
            }
            columns.add(column);
            index+=op.length();
        }
        return transformColumns(columns);
    }

    private List<List<Integer>> transformColumns(List<List<String>> columns) {
        return columns.stream().map(column->transformPL(column)).toList();
    }

    private List<Integer> transformPL(List<String> column) {
        List<Integer> transformedDigits = new ArrayList<>();
        for(int i = column.get(0).length()-1; i>=0;i--){
            String newDigit = "";
            for(String operand:column){
                newDigit += operand.charAt(i);
            }
            transformedDigits.add(Integer.parseInt(newDigit.strip()));
        }
        return transformedDigits;
    }

}
