package aoc2023.day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import utils.BroadcasterModule;
import utils.ConjunctionModule;
import utils.FlipFlopModule;
import utils.PulseModule;

public class Day20Y2023 {
    
    private Map<String, PulseModule> modules;

    public Map<String, PulseModule> getModules(){
        return this.modules;
    }

    public Day20Y2023(Map<String, PulseModule> modules){
        this.modules = modules;
    }

    public static Day20Y2023 readDay20Data(){
        Map<String, PulseModule> modules = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day20Data.txt"))) {
            br.lines().forEach(line->{
                if(line.startsWith("broadcaster")){
                    BroadcasterModule broadcaster = new BroadcasterModule();
                    broadcaster.name = "broadcaster";
                    String[] outputs = Arrays.stream(line.split(">")[1].split(","))
                        .map(String::trim)
                        .toArray(String[]::new);
                    broadcaster.outputs = Arrays.asList(outputs);
                    modules.put(broadcaster.name, broadcaster);
                }else if(line.startsWith("%")){
                    FlipFlopModule flipflop = new FlipFlopModule();
                    flipflop.name = line.split("-")[0].replace("%", "").trim();
                    String[] outputs = Arrays.stream(line.split(">")[1].split(","))
                        .map(String::trim)
                        .toArray(String[]::new);
                    flipflop.outputs = Arrays.asList(outputs);
                    modules.put(flipflop.name, flipflop);
                }else if(line.startsWith("&")){
                    ConjunctionModule conjuntion = new ConjunctionModule();
                    conjuntion.name = line.split("-")[0].replace("&", "").trim();
                    String[] outputs = Arrays.stream(line.split(">")[1].split(","))
                        .map(String::trim)
                        .toArray(String[]::new);
                    conjuntion.outputs = Arrays.asList(outputs);
                    modules.put(conjuntion.name, conjuntion);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        Day20Y2023 input = new Day20Y2023(modules);
        input.initConjunctionMemories();
        return input;
    }

    public void initConjunctionMemories(){
        for (PulseModule module : modules.values()) {
            for (String output : module.outputs) {

                PulseModule destination = modules.get(output);

                if (destination instanceof ConjunctionModule conjunction) {
                    conjunction.memory.put(module.name, false);
                }
            }
        }
    }

}
