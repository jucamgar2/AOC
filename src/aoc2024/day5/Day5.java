package aoc2024.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {
    
    private List<List<Integer>> listOfUpdates;

    private Map<Integer,List<Integer>> updateBeforeNumber;

    private Map<Integer,List<Integer>> updateAfterNumber;

    public Day5(List<List<Integer>> listOfUpdates, 
                Map<Integer, List<Integer>> updateBeforeNumber, 
                Map<Integer, List<Integer>> updateAfterNumber) {
        this.listOfUpdates = listOfUpdates;
        this.updateBeforeNumber = updateBeforeNumber;
        this.updateAfterNumber = updateAfterNumber;
    }

    public List<List<Integer>> getListOfUpdates() {
        return listOfUpdates;
    }
    public void setListOfUpdates(List<List<Integer>> listOfUpdates) {
        this.listOfUpdates = listOfUpdates;
    }

    public Map<Integer, List<Integer>> getUpdateBeforeNumber() {
        return updateBeforeNumber;
    }

    public void setUpdateBeforeNumber(Map<Integer, List<Integer>> updateBeforeNumber) {
        this.updateBeforeNumber = updateBeforeNumber;
    }

    public Map<Integer, List<Integer>> getUpdateAfterNumber() {
        return updateAfterNumber;
    }

    public void setUpdateAfterNumber(Map<Integer, List<Integer>> updateAfterNumber) {
        this.updateAfterNumber = updateAfterNumber;
    }

    public static Day5 getDay5Data(){
        List<List<Integer>> listOfUpdates = new ArrayList<>();
        Map<Integer,List<Integer>> updateBeforeNumber = new HashMap<>();
        Map<Integer,List<Integer>> updateAfterNumber = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Day5Data.txt"))) {
            br.lines().forEach(line-> {
                if(line.length()>2&&line.charAt(2)=='|'){
                    processUpdateOrder(line, updateBeforeNumber, updateAfterNumber);
                }else if(line.length()>2&&line.charAt(2)==','){
                    processUpdateList(line, listOfUpdates);
                }
            });
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day5(listOfUpdates, updateBeforeNumber, updateAfterNumber);
    }

    private static void processUpdateList(String line, List<List<Integer>> listOfUpdates){
        String[] numberArray = line.split(",");
        List<Integer> updates = new ArrayList<>();
        for(String number:numberArray){
            updates.add(Integer.parseInt(number));
        }
        listOfUpdates.add(updates);
    }

    private static void processUpdateOrder(String line,
                    Map<Integer,List<Integer>> updateBeforeNumber,
                    Map<Integer,List<Integer>> updateAfterNumber){
        String[] numberArray = line.split("\\|");
        Integer firstNumber = Integer.parseInt(numberArray[0]);
        Integer secondNumber = Integer.parseInt(numberArray[1]);
        if(updateAfterNumber.containsKey(firstNumber)){
            updateAfterNumber.get(firstNumber).add(secondNumber);
        }else{
            List<Integer> updateAfer = new ArrayList<>();
            updateAfer.add(secondNumber);
            updateAfterNumber.put(firstNumber,updateAfer);
        }

        if(updateBeforeNumber.containsKey(secondNumber)){
            updateBeforeNumber.get(secondNumber).add(firstNumber);
        }else{
            List<Integer> updateBefore = new ArrayList<>();
            updateBefore.add(firstNumber);
            updateBeforeNumber.put(secondNumber, updateBefore);
        }
    }

}