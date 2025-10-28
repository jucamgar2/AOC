package aoc2024.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3Y2024 {

    private String program;

    public Day3Y2024(String program){
        this.program = program;
    }

    public String getProgram(){
        return this.program;
    }

    public static Day3Y2024 getDay3Data(){
        String program = "";
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day3Data.txt"))) {
            String line;
            while((line = br.readLine())!=null){
                program += line;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day3Y2024(program);
    }
    
}
