package aoc2024.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    
    private List<List<Integer>> reports;

    public List<List<Integer>> getReports(){
        return this.reports;
    }

    public Day2(List<List<Integer>> reports){
        this.reports = reports;
    }

    public static Day2 getDay2Data(){
        List<List<Integer>> reports = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day2Data.txt"))) {
            br.lines().forEach(line->{
                List<Integer> report = new ArrayList<>();
                String[] numbers = line.split(" ");
                for(String number: numbers){
                    report.add(Integer.parseInt(number));
                }
                reports.add(report);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day2(reports);
    }
}
