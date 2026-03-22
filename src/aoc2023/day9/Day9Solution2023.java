package aoc2023.day9;

import java.util.ArrayList;
import java.util.List;

import structure.BaseDay;

public class Day9Solution2023 extends BaseDay{

    @Override
    protected Day9Y2023 getInputData() {
        return Day9Y2023.readDay9Data();
    }

    @Override
    public void runDaySolution() {
        Day9Y2023 input = Day9Y2023.readDay9Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getSumOfExtrapolated((Day9Y2023) input, "front"));
    }

    private Long getSumOfExtrapolated(Day9Y2023 input, String direction) {
        return input.getHistories().stream().mapToLong(history->getExtrapolated(history, direction)).sum();
    }

    private Long getExtrapolated(List<Integer> history, String direction) {
        Long extrapolated = 0l;
        List<List<Integer>> sequences = new ArrayList<>();
        sequences.add(history);
        List<Integer> lastSequence = history;
        while(!lastSequence.stream().allMatch(x->x==0)){
            List<Integer> newSequence = new ArrayList<>();
            for(int i = 0; i<lastSequence.size()-1; i++){
                newSequence.add(lastSequence.get(i+1)-lastSequence.get(i));
            }
            sequences.add(newSequence);
            lastSequence = newSequence;
        }
        if(direction.equals("front")){
            for(List<Integer> sequence: sequences){
                extrapolated+= sequence.get(sequence.size()-1);
            }
        }else{
            for(int i = sequences.size()-1;i>=0;i--){
                extrapolated = sequences.get(i).get(0)-extrapolated;
            }
        }
        return extrapolated;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getSumOfExtrapolated((Day9Y2023) input, "back"));
    }


    
}
