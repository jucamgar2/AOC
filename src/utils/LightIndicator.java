package utils;

import java.util.List;

public class LightIndicator {
    
    private String lightIndicator;

    private List<List<Integer>> buttons;

    private List<Integer> joltage;
    
    public String getLightIndicator(){
        return this.lightIndicator;
    }

    public List<List<Integer>> getButtons(){
        return this.buttons;
    }

    public List<Integer> getJoltage(){
        return this.joltage;
    }

    public LightIndicator(String lightIndicator, List<List<Integer>> buttons, List<Integer> joltage){
        this.lightIndicator = lightIndicator;
        this.buttons = buttons;
        this.joltage = joltage;
    }


}
