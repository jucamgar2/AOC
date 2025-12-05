package aoc2025.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import structure.BaseDay;

public class Day5Solution2025 extends BaseDay{

    @Override
    protected Day5Y2025 getInputData() {
        return Day5Y2025.getDay5Data();
    }

    @Override
    public void runDaySolution() {
        Day5Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN: " + countAvaliableIngredients((Day5Y2025)input));
    }

    private Long countAvaliableIngredients(Day5Y2025 input) {
        return input.getIds().stream().filter(ingredient->isavailable(ingredient, input)).count();
    }

    private Boolean isavailable(Long ingredient, Day5Y2025 input) {
        return input.getIdRanges().stream().anyMatch(range->{
            Long minorRange = Long.parseLong(range.split("-")[0]);
            Long maxRange = Long.parseLong(range.split("-")[1]);
            return ingredient>=minorRange && ingredient<=maxRange;
        });
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: "+getTotalFreshIngredients((Day5Y2025) input));
    }

    private Long getTotalFreshIngredients(Day5Y2025 input) {
        List<String> reducedRanges = reduceRanges(input.getIdRanges());
        return reducedRanges.stream()
                    .mapToLong(range->{
                        Long r1 = Long.parseLong(range.split("-")[0]);
                        Long r2 = Long.parseLong(range.split("-")[1]);
                        return r2-r1+1;
                    })
                    .sum();
    }

    private List<String> reduceRanges(List<String> ranges){
        List<String> newRanges = new ArrayList<>();
        ranges.sort(Comparator.comparingLong(x -> Long.parseLong(x.split("-")[0])));
        String actualRange = ranges.get(0);
        for(int i = 1;i<ranges.size();i++){
            String nextRange = ranges.get(i);
            Long a1 = Long.parseLong(actualRange.split("-")[0]);
            Long a2 = Long.parseLong(actualRange.split("-")[1]);
            Long b1 = Long.parseLong(nextRange.split("-")[0]);
            Long b2 = Long.parseLong(nextRange.split("-")[1]);
            if(b1<=a2+1 && a1<=b2+ 1){
                actualRange = a1+"-"+Math.max(a2,b2);
            }else{
                newRanges.add(a1+"-"+a2);
                actualRange = nextRange;
            }
        }
        newRanges.add(actualRange);
        return newRanges;
    }
    
}
