package aoc2023.day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19Y2023 {

    private Map<String, String> workflows;

    private List<String> partsList;

    public Map<String, String> getWorkflows(){
        return this.workflows;
    }

    public List<String> getPartsList(){
        return this.partsList;
    }

    public Day19Y2023(Map<String, String> workflows, List<String> partList){
        this.workflows = workflows;
        this.partsList = partList;
    }

    public static Day19Y2023 readDay19Data(){
        Map<String, String> workflows = new HashMap<>();
        List<String> partsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day19Data.txt"))) {
            List<String> lines = br.lines().toList();
            boolean emptyLine=false;
            for(int i = 0; i<lines.size();i++){
                String line = lines.get(i);
                if(line.isBlank()){
                    emptyLine=true;
                }else if(!emptyLine){
                    String workflowName = line.split("\\{")[0];
                    String workflowContent = line.split("\\{")[1].replace("}", "");
                    workflows.put(workflowName, workflowContent);
                }else{
                    partsList.add(line.replace("{", "").replace("}", ""));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day19Y2023(workflows, partsList);
    }
    
}
