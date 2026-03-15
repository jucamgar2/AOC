package aoc2023.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day7Y2023 {

    private List<String> hands;

    private List<Integer> bids;

    public List<String> getHands(){
        return this.hands;
    }

    public List<Integer> getBids(){
        return this.bids;
    }

    public Day7Y2023(List<String> hands, List<Integer> bids){
        this.hands = hands;
        this.bids = bids;
    }

    public static Day7Y2023 readDay7Data(){
        List<String> hands = new ArrayList<>();
        List<Integer> bids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/TestDay7.txt"))) {
            br.lines().forEach(line->{
                hands.add(line.split(" ")[0]);
                bids.add(Integer.valueOf(line.split(" ")[1]));
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day7Y2023(hands, bids);
    }
    
}
