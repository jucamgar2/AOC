package aoc2023.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6Y2023 {

    private List<Integer> distance;

    private List<Integer> time;

    public List<Integer> getDistance(){
        return this.distance;
    }

    public List<Integer> getTime(){
        return this.time;
    }

    public Day6Y2023(List<Integer> distance, List<Integer> time){
        this.distance = distance;
        this.time = time;
    }

    public static Day6Y2023 readDay6Data(){
        List<Integer> distance = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/TestDay6.txt"))) {
            br.lines().forEach(line->{
                if(line.contains("Time")){
                    line = line.replace("Time:", "").strip().replaceAll("\\s+", " ");
                    Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).forEach(time::add);
                }else{
                    line = line.replace("Distance:", "").strip().replaceAll("\\s+", " ");
                    Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).forEach(distance::add);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day6Y2023(distance, time);
    } 
    
}
