package aoc2025.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12Y2025 {

    private Map<String, String> shapes;

    private List<List<String>> regions;

    public Map<String, String> getShapes(){
        return this.shapes;
    }

    public List<List<String>> getRegions(){
        return regions;
    }

    public Day12Y2025(Map<String, String> shapes, List<List<String>> regions){
        this.shapes = shapes;
        this.regions = regions;
    }

    public static Day12Y2025 readDay12Data(){
        Map<String, String> shapes = new HashMap<>();
        List<List<String>> regions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2025/Day12Data.txt"))) {
            StringBuilder shape = new StringBuilder();
            boolean newShape = false;
            String actualId = "0";
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.equals("")){
                    if(line.contains("x")){
                        if(actualId!=null){
                            shapes.put(actualId, shape.toString());
                            shape.delete(0, shape.length());
                            actualId = null;
                        }
                        String[] region = line.split(":");
                        String[] shapeArray = region[1].trim().split(" ");
                        List<String> regionShapes = new ArrayList<>();
                        regionShapes.add(region[0]);
                        regionShapes.addAll(Arrays.asList(shapeArray));
                        regions.add(regionShapes);
                    }else{
                        if(newShape){
                            shapes.put(actualId, shape.toString());
                            shape.delete(0, shape.length());
                            actualId = line.replace(":", "");
                            newShape=false;
                        }else{
                            if(!line.contains(":")){
                                shape.append(line).append("/");
                            }
                        }
                    }
                }else{
                    newShape = true;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day12Y2025(shapes, regions);
    }
    
}
