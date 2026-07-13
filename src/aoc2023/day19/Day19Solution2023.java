package aoc2023.day19;

import java.util.HashMap;
import java.util.Map;

import structure.BaseDay;
import utils.PartRange;
import utils.RangeNum;

public class Day19Solution2023 extends BaseDay{

    @Override
    protected Day19Y2023 getInputData() {
        return Day19Y2023.readDay19Data();
    }

    @Override
    public void runDaySolution() {
        Day19Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getSumOfPartsAccepteds((Day19Y2023) input));
    }

    private Long getSumOfPartsAccepteds(Day19Y2023 input) {
        return input.getPartsList().stream()
                        .filter(parts->areAccepted(parts, input.getWorkflows()))
                        .mapToLong(parts->getSumOfParts(parts))
                        .sum();
    }

    private Boolean areAccepted(String parts, Map<String, String> workflows) {
        String workflow = "in";
        Map<String, Integer> partsWithValue = getPartsWithValue(parts);

        while(!workflow.equals("A") && !workflow.equals("R")){
            String[] workflowContent = workflows.get(workflow).split(",");
            for(int i = 0; i<workflowContent.length; i++){
                if(i==workflowContent.length-1){
                    workflow = workflowContent[i];
                }else{
                    String workflowStage = workflowContent[i];
                    workflow = checkCondition(workflowStage, partsWithValue);
                    if(!workflow.equals("")){
                        break;
                    }
                }
            }
        }
        return workflow.equals("A");
    }

    private Map<String, Integer> getPartsWithValue(String parts){
        Map<String, Integer> partsWithValue = new HashMap<>();
        String[] values = parts.split(",");
        for (String value : values) {
            String[] partValues = value.split("=");
            switch (partValues[0]) {
                case "x":
                    partsWithValue.put("x", Integer.parseInt(partValues[1]));
                    break;
                case "m":
                    partsWithValue.put("m", Integer.parseInt(partValues[1]));
                    break;
                case "a":
                    partsWithValue.put("a", Integer.parseInt(partValues[1]));
                    break;
                default:
                    partsWithValue.put("s", Integer.parseInt(partValues[1]));
                    break;
            }
        }
        return partsWithValue;
    }

    private String checkCondition(String workflowStage, Map<String, Integer> partsWithValue) {
        String possibleDestination = workflowStage.split(":")[1];
        String partType = String.valueOf(workflowStage.charAt(0));
        char condition = workflowStage.charAt(1);
        int conditionValue = Integer.parseInt(workflowStage.split(":")[0].substring(2));
        if(condition=='>'){
            if(partsWithValue.get(partType) > conditionValue){
                return possibleDestination;
            }
        }else{
             if(partsWithValue.get(partType) < conditionValue){
                return possibleDestination;
            }
        }
        return "";
    }

    private Long getSumOfParts(String parts) {
        return getPartsWithValue(parts).values().stream().mapToLong(Integer::longValue).sum();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getNumOfDistinctsParts((Day19Y2023) input));   
    }

    private Long getNumOfDistinctsParts(Day19Y2023 input) {
        PartRange part = new PartRange();
        part.x = new RangeNum(1, 4000);
        part.a = new RangeNum(1, 4000);
        part.m = new RangeNum(1, 4000);
        part.s = new RangeNum(1, 4000);
        return solve("in", part, input.getWorkflows());
    }

    private Long solve(String workFlowKey, PartRange partRange, Map<String, String> workflows){
        if(workFlowKey.equals("A")){
            return partRange.x.size()*
                    partRange.a.size()*
                    partRange.m.size()*
                    partRange.s.size();
        }
        if(workFlowKey.equals("R")){
            return 0l;
        }
        long result = 0;

        PartRange current = partRange.copy();

        String[] rules = workflows.get(workFlowKey).split(",");

        for(String rule : rules){
            if (!rule.contains(":")) {
                return result + solve(rule, current, workflows);
            }

            String[] split = rule.split(":");
            String destination = split[1];
            char variable = split[0].charAt(0);
            char operator = split[0].charAt(1);
            int value = Integer.parseInt(split[0].substring(2));
            PartRange accepted = current.copy();
            PartRange rejected = current.copy();
            RangeNum ok = getRange(accepted, variable);
            RangeNum fail = getRange(rejected, variable);
            if (operator == '<') {
                ok.setMax(Math.min(ok.getMax(), value - 1l));
                fail.setMin(Math.max(fail.getMin(), value));
            } else {
                ok.setMin(Math.max(ok.getMin(), value + 1l));
                fail.setMax(Math.min(fail.getMax(), value));
            }
            if (ok.getMin() <= ok.getMax()) {
                result += solve(destination, accepted, workflows);
            }
            if (fail.getMin() <= fail.getMax()) {
                current = rejected;
            } else {
                return result;
            }
        }
        return result;
    }

    private RangeNum getRange(PartRange partRange, char variable) {
        switch (variable) {
            case 'x':
                return partRange.x;
            case 'm':
                return partRange.m;
            case 'a':
                return partRange.a;
            default:
                return partRange.s;
        }
    }
    
}
