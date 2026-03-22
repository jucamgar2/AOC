package aoc2023.day8;

import java.io.BufferedReader;
import java.io.FileReader;

import utils.Graph;

public class Day8Y2023 {
    
    private String instructions;

    private Graph network;

    public String getInstructions(){
        return this.instructions;
    }

    public Graph getNetwork(){
        return this.network;
    }

    public Day8Y2023(String instructions, Graph network){
        this.instructions = instructions;
        this.network = network;
    }

    public static Day8Y2023 readDay8Data(){
        Graph network = new Graph();
        StringBuilder instructions = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day8Data.txt"))) {
            br.lines().forEach(line->{
                if(!line.equals("")){
                    if(instructions.isEmpty()){
                        instructions.append(line);
                    }else{
                        String[] networkLine = line.split(" = ");
                        String node = networkLine[0];
                        String left = networkLine[1].split(",")[0].replace("(", "").trim();
                        String right = networkLine[1].split(",")[1].replace(")", "").trim();
                        network.addEdge(node, left, "L");
                        network.addEdge(node, right, "R");
                    }
                }
        });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day8Y2023(instructions.toString(), network);
    }

}
