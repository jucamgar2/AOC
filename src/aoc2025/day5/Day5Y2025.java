package aoc2025.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5Y2025 {
    
    private List<String> idRanges;

    private List<Long> ids;

    public List<String> getIdRanges(){
        return this.idRanges;
    }

    public List<Long> getIds(){
        return this.ids;
    }

    public Day5Y2025(List<String> idRanges, List<Long> ids ){
        this.idRanges = idRanges;
        this.ids = ids;
    }

    public static Day5Y2025 getDay5Data(){
        List<String> idranges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/TestDay5.txt"))) {
            br.lines().forEach(line->{
                if(!line.trim().isEmpty()){
                    if(line.contains("-")){
                        idranges.add(line);
                    }else{
                        ids.add(Long.parseLong(line));
                    }
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day5Y2025(idranges, ids);
    }
}
