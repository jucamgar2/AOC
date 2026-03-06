package aoc2023.day6;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import structure.BaseDay;

public class Day6Solution2023 extends BaseDay{

    @Override
    protected Day6Y2023 getInputData() {
        return Day6Y2023.readDay6Data();
    }

    @Override
    public void runDaySolution() {
        Day6Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + multiplyNumOfWins((Day6Y2023) input));
    }

    private Long multiplyNumOfWins(Day6Y2023 input) {
        return IntStream.range(0, input.getDistance().size())
            .mapToLong(index->getNumWaysToWin((input.getDistance().get(index)).longValue(), input.getTime().get(index).longValue()))
            .reduce((a,b)-> a*b).orElse(0);
    }

    private Long getNumWaysToWin(Long distance, Long time) {
        Long result = LongStream.range(0, time/2+1).map(t->t*(time-t)).filter(dist->dist>distance).count();
        if(time%2==0){
            return result*2-1;
        }else{
            return result*2;
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getWaysToWinOneRace((Day6Y2023) input));
    }

    private Long getWaysToWinOneRace(Day6Y2023 input) {
        StringBuilder timeBuilder = new StringBuilder();
        input.getTime().forEach(n->timeBuilder.append(n.toString()));
        Long time = Long.valueOf(timeBuilder.toString());
        StringBuilder distanceBuilder = new StringBuilder();
        input.getDistance().forEach(n->distanceBuilder.append(n.toString()));
        Long distance = Long.valueOf(distanceBuilder.toString());
        return getNumWaysToWin(distance, time);
    }


    
}
