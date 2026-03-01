package aoc2023.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4Y2023 {
    
    private List<List<Integer>> cards;

    private List<List<Integer>> winnerNumbers;

    public List<List<Integer>> getCards(){
        return this.cards;
    }

    public List<List<Integer>> getWinnerNumbers(){
        return this.winnerNumbers;
    }

    public Day4Y2023(List<List<Integer>> cards, List<List<Integer>> winnerNumbers){
        this.cards = cards;
        this.winnerNumbers = winnerNumbers;
    }

    public static Day4Y2023 readDay4Data(){
        List<List<Integer>> cards = new ArrayList<>();
        List<List<Integer>> winnerNumbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day4Data.txt"))) {
            br.lines().forEach(line->{
                String numbers = line.split(": ")[1];
                String[] cardsArray = numbers.split("\\|")[0].split(" ");
                String[] winnerArray = numbers.split("\\|")[1].split(" ");
                cards.add(Arrays.asList(cardsArray).stream()
                        .filter(x->!"".equals(x))
                        .map(x->x.trim())
                        .map(Integer::valueOf)
                        .toList());
                winnerNumbers.add(Arrays.asList(winnerArray).stream()
                        .filter(x->!"".equals(x))
                        .map(x->x.trim())
                        .map(Integer::valueOf)
                        .toList());
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day4Y2023(cards, winnerNumbers);
    }
}
