package aoc2023.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9Y2023 {
    
    private List<List<Integer>> hisotiries;

    public List<List<Integer>> getHistories(){
        return this.hisotiries;
    }

    public Day9Y2023(List<List<Integer>> histories){
        this.hisotiries = histories;
    }

    public static Day9Y2023 readDay9Data(){
        List<List<Integer>> histories = new ArrayList<>();
         try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day9Data.txt"))) {
            br.lines().forEach(line->{
                List<Integer> history = new ArrayList<>();
                Arrays.asList(line.split(" ")).forEach(num->history.add(Integer.valueOf(num)));
                histories.add(history);
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day9Y2023(histories);
    }
}
