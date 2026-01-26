package aoc2025.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.LightIndicator;

public class Day10Y2025 {

    private List<LightIndicator> lightIndicators;

    public List<LightIndicator> getLightIndicators(){
        return this.lightIndicators;
    }

    public Day10Y2025(List<LightIndicator> lightIndicators){
        this.lightIndicators = lightIndicators;
    }
    
    public static Day10Y2025 getDay10Data(){
        List<LightIndicator> lightIndicators = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/TestDay10.txt"))) {
            br.lines().forEach(line->{
                List<List<Integer>> buttons = new ArrayList<>();
                List<Integer> joltages = new ArrayList<>();
                String[] subLine = line.split("\\]");
                String light = subLine[0].replace("[", "");
                String buttonsParsed = subLine[1].split("\\{")[0].replace("(", "").replace(")", "#");
                String[] buttonsArray = buttonsParsed.split("#");
                for(String button:buttonsArray){
                    String[] btts = button.split(",");
                    List<Integer> buttonList = new ArrayList<>();
                    for(String bt:btts){
                        if(!bt.equals(" ")){
                            buttonList.add(Integer.parseInt(bt.trim()));
                        }
                    }
                    if(!buttonList.isEmpty()){
                        buttons.add(buttonList);
                    }
                }
                String[] joltagesArray = subLine[1].split("\\{")[1].replace("}", "").split(",");
                for(String joltage: joltagesArray){
                    joltages.add(Integer.parseInt(joltage.trim()));
                }
                lightIndicators.add(new LightIndicator(light, buttons, joltages));
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day10Y2025(lightIndicators);
    }
}
