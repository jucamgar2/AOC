package aoc2020.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Y2020 {

    private List<Integer> expenseReport;

    public List<Integer> getExpenseReport(){
        return this.expenseReport;
    }

    public Day1Y2020(List<Integer> expenseReport){
        this.expenseReport = expenseReport;
    }

    public static Day1Y2020 getDay1Data(){
        List<Integer> expenseReport = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2020/Day1Data.txt"))) {
            br.lines().forEach(line->{
                expenseReport.add(Integer.parseInt(line));
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day1Y2020(expenseReport);
    }
    
}
