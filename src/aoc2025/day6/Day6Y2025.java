package aoc2025.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Y2025 {

    private List<List<Integer>> operands;

    private List<String> operations;

    private List<String> operandsWithSpaces;

    public List<List<Integer>> getOperands(){
        return this.operands;
    }

    public List<String> getOperations(){
        return this.operations;
    }

    public List<String> getOperandWithSpaces(){
        return this.operandsWithSpaces;
    }

    public Day6Y2025(List<List<Integer>> operands, List<String> operations, List<String> operandsWithSpaces){
        this.operands = operands;
        this.operations = operations;
        this.operandsWithSpaces = operandsWithSpaces;
    }
    
    public static Day6Y2025 getDay6Data(){
        List<List<Integer>> operands = new ArrayList<>();
        List<String> operations = new ArrayList<>();
        List<String> operandsWithSpaces = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/TestDay6.txt"))) {
            br.lines().forEach(line->{
                if(line.charAt(0)=='*'||line.charAt(0)=='+'){
                    Pattern p = Pattern.compile("[+*] *");
                    Matcher m = p.matcher(line);
                    while (m.find()) {
                        operations.add(m.group());
                    }
                }else{
                    String[] objects = line.trim().split("\\s+");
                    operandsWithSpaces.add(line);
                    for(int i = 0; i<objects.length;i++){
                        if(operands.size()>i){
                            operands.get(i).add(Integer.valueOf(objects[i]));
                        }else{
                            List<Integer> operand = new ArrayList<>();
                            operand.add(Integer.parseInt(objects[i]));
                            operands.add(operand);
                        }
                    }
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day6Y2025(operands, operations, operandsWithSpaces);
    }
}
