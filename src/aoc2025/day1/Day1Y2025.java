package aoc2025.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Y2025{

        private List<Integer> instructions;

    private int actualState;

    public List<Integer> getInstructions(){
        return this.instructions;
    }

    public int getActualState(){
        return this.actualState;
    }

    public void setActualState(int actualState){
        this.actualState = actualState;
    }

    public Day1Y2025(List<Integer> instructions, int actualState){
        this.actualState = actualState;
        this.instructions = instructions;
    }

    public static Day1Y2025 getDay1Data(){
        List<Integer> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/TestDay1.txt"))) {
            br.lines().forEach(line->{
                if(line.charAt(0)=='R'){
                    instructions.add(Integer.parseInt(line.substring(1)));
                }else{
                    instructions.add(-Integer.parseInt(line.substring(1)));
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day1Y2025(instructions, 50);
    }


    
}
