package aoc2020.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.PasswordCondition;

public class Day2Y2020 {
    
    private List<PasswordCondition> listOfPasswords;

    public List<PasswordCondition> getListOfPasswords(){
        return this.listOfPasswords;
    }

    public Day2Y2020(List<PasswordCondition> listOfPasswords){
        this.listOfPasswords = listOfPasswords;
    }

    public static Day2Y2020 getDay2Data(){
        List<PasswordCondition> listOfPasswords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2020/Day2Data.txt"))) {
            br.lines().forEach(line->{
                String[] division = line.split(":");
                String password = division[1].trim();
                String[] conditionDivision = division[0].split(" ");
                String character = conditionDivision[1];
                String limit1 = conditionDivision[0].split("-")[0];
                String limit2 = conditionDivision[0].split("-")[1];
                PasswordCondition pc = new PasswordCondition(Integer.parseInt(limit1), Integer.parseInt(limit2), character.charAt(0), password);
                listOfPasswords.add(pc);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day2Y2020(listOfPasswords);
    }
}
