package aoc2025.day2;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import structure.BaseDay;

public class Day2Solution2025 extends BaseDay {

    @Override
    protected Day2Y2025 getInputData() {
        return Day2Y2025.getDay2Data();
    }
    
    @Override
    public void runDaySolution() {
        Day2Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: "+ getSumOfInvalidIds((Day2Y2025) input, 1));
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: "+ getSumOfInvalidIds((Day2Y2025) input, 2));
    }

    private Long getSumOfInvalidIds(Day2Y2025 input, int part) {
        return input.getIdRanges().stream().mapToLong(range->getSumOfInvalidIdsInRange(range, part)).sum();
    }

    private Long getSumOfInvalidIdsInRange(String range, int part) {
        return LongStream.rangeClosed(Long.parseLong(range.split("-")[0]),Long.parseLong(range.split("-")[1]))
                .filter(id->isInvalid(id, part)).sum();
    }

    private Boolean isInvalid(Long id, int part) {
        String idString = id.toString();
        int stringSize = idString.length();
        if(part==1){
            String firstPart = idString.substring(0, stringSize/2);
            String secondPart = idString.substring(stringSize/2);
            return firstPart.equals(secondPart);
        }else{
            return IntStream.rangeClosed(1, stringSize/2)
                        .filter(num->stringSize%num==0)
                        .anyMatch(divisor->{
                            String subChain = idString.substring(0, divisor);
                            String repetitionChain = subChain.repeat(stringSize/divisor);
                            return idString.equals(repetitionChain);
                        });
        }
       
    }

   
    
}
