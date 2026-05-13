package aoc2023.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day15Y2023 {

    private List<String> messages;

    public List<String> getMessages(){
        return this.messages;
    }

    public Day15Y2023(List<String> messages){
        this.messages = messages;
    }

    public static Day15Y2023 readDay15Data(){
        List<String> messages = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day15Data.txt"))) {
            br.lines().forEach(line->{
                messages.addAll(Arrays.asList(line.split(",")));
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day15Y2023(messages);
    }
    
}
