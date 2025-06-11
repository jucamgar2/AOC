package aoc2024.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    private List<Integer> firsList;

    private List<Integer> secondList;

    public List<Integer> getFirstList(){
        return this.firsList;
    }

    public List<Integer> getSecondList(){
        return this.secondList;
    }

    public Day1(List<Integer> firstList, List<Integer> secondList){
        this.firsList = firstList;
        this.secondList = secondList;
    }

    public static Day1 getDay1Data(){
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day1Data.txt"))) {
            br.lines().forEach(line->{
                line = line.replace("   ", ";");
                String[] numbers = line.split(";");
                firstList.add(Integer.parseInt(numbers[0]));
                secondList.add(Integer.parseInt(numbers[1]));
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day1(firstList, secondList);
    }
}
