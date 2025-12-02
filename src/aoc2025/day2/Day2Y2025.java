package aoc2025.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2Y2025 {

    private List<String> idRanges;

    public List<String> getIdRanges(){
        return this.idRanges;
    }

    public Day2Y2025(List<String> idRanges){
        this.idRanges = idRanges;
    }

    public static Day2Y2025 getDay2Data(){
        List<String> idRanges = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/Day2Data.txt"))) {
            br.lines().forEach(line->{
                String[] ranges = line.split(",");
                for(String range: ranges){
                    idRanges.add(range);
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day2Y2025(idRanges);
    }
}
