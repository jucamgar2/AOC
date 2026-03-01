package aoc2023.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day4Solution2023 extends BaseDay{

    @Override
    protected Day4Y2023 getInputData() {
        return Day4Y2023.readDay4Data();
    }

    @Override
    public void runDaySolution() {
        Day4Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solucion: " + getWorthPoints((Day4Y2023) input));
    }

    private Long getWorthPoints(Day4Y2023 input) {
        return IntStream.range(0, input.getCards().size())
                    .map(index->getNumOfWins(index, input))
                    .mapToLong(this::countPoints)
                    .sum();
    }

    

    private int getNumOfWins(int index, Day4Y2023 input) {
        List<Integer> winner = input.getWinnerNumbers().get(index);
        return (int) input.getCards().get(index).stream().filter(winner::contains).count();
    }

    private Long countPoints(int wins) {
        if(wins==0){
            return 0l;
        }else{
            return (long) Math.pow(2, wins-1);
        }

    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solucion: " + getNumOfScratchcards((Day4Y2023) input));
    }

    private Integer getNumOfScratchcards(Day4Y2023 input) {
        List<Integer> numOfCards = new ArrayList<>();
        input.getCards().forEach(x->numOfCards.add(1));
        return IntStream.range(0, numOfCards.size())
                    .map(x->{
                        applyEffectsOfWins(x, input, numOfCards);
                        return numOfCards.get(x);
                    })
                    .sum();
    }

    private void applyEffectsOfWins(Integer index, Day4Y2023 input, List<Integer> numOfCards){
        int wins = getNumOfWins(index, input);
        int cards = numOfCards.get(index);
        for(int i = index+1; i<=index+wins; i++){
            int num = numOfCards.get(i);
            num+=cards;
            numOfCards.set(i, num);
        }
    }


    
}
