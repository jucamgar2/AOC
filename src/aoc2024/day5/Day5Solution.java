package aoc2024.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day5Solution {

    public static void day5Solution(){
        Day5 input = Day5.getDay5Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }
    
    public static void part1Solution(Day5 input){
        Integer res = input.getListOfUpdates().stream().filter(list->isUpdateListValid(list, input)).mapToInt(list->list.get(list.size()/2)).sum();
        System.out.println("SOLUCIÓN: " + res);
    }

    public static boolean isUpdateListValid(List<Integer> listOfupdates, Day5 input){
        for(Integer number: listOfupdates){
            int position = listOfupdates.indexOf(number)+1;
            List<Integer> updated = listOfupdates.subList(0, position);
            List<Integer> toUpdate = listOfupdates.subList(position, listOfupdates.size());
            List<Integer> mustBeAlreadyUpdated = filterMapList(input.getUpdateBeforeNumber(), listOfupdates, number);
            List<Integer> toBeUpdated = filterMapList(input.getUpdateAfterNumber(), listOfupdates, number);
            if(!meetFirstCondition(updated, mustBeAlreadyUpdated) 
                || !meetSecondCondtion(toUpdate,toBeUpdated)){
                    return false;
            }
        }
        return true;
    }

    public static List<Integer> filterMapList(Map<Integer,List<Integer>> mapToFilter, List<Integer> listOfUpdates, Integer number){
        List<Integer> filteredList = new ArrayList<>();
        if(mapToFilter.get(number)!=null){
            filteredList = mapToFilter.get(number).stream().filter(x->listOfUpdates.contains(x)).toList();
        }
        return filteredList;
    }

    public static boolean meetFirstCondition(List<Integer>updated, List<Integer> mustBeAlreadyUpdated){
        if(mustBeAlreadyUpdated.isEmpty() || updated.containsAll(mustBeAlreadyUpdated)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean meetSecondCondtion(List<Integer> toUpdate, List<Integer> toBeUpdated){
        if(toBeUpdated.isEmpty()|| toUpdate.containsAll(toBeUpdated)){
            return true;
        }else{
            return false;
        }
    }

    public static void part2Solution(Day5 input){
        Integer res = input.getListOfUpdates().stream().filter(list->!isUpdateListValid(list, input))
            .map(list->orderLists(list,input))
            .mapToInt(list->list.get(list.size()/2)).sum();
        System.out.println("SOLUCIÓN: " + res);    
    }

    public static List<Integer> orderLists(List<Integer> listToOrder, Day5 input){
        if(isUpdateListValid(listToOrder, input)){
            return listToOrder;
        }
        List<Integer> orderedList = new ArrayList<>();
        for(Integer number:listToOrder){
            int position = listToOrder.indexOf(number)+1;
            List<Integer> updated = listToOrder.subList(0, position);
            List<Integer> toUpdate = listToOrder.subList(position, listToOrder.size());
            List<Integer> mustBeAlreadyUpdated = filterMapList(input.getUpdateBeforeNumber(), listToOrder, number);
            List<Integer> toBeUpdated = filterMapList(input.getUpdateAfterNumber(), listToOrder, number);
            if(!meetFirstCondition(updated, mustBeAlreadyUpdated) 
                    || !meetSecondCondtion(toUpdate,toBeUpdated)){
                if(position == 1){
                    orderedList.addAll(mustBeAlreadyUpdated);
                    orderedList.add(number);
                    toUpdate = toUpdate.stream().filter(x->!orderedList.contains(x)).toList();
                    orderedList.addAll(toUpdate);
                }else{
                    orderedList.addAll(updated);
                    orderedList.remove(number);
                    mustBeAlreadyUpdated = mustBeAlreadyUpdated.stream().filter(x->!orderedList.contains(x)).toList();
                    orderedList.addAll(mustBeAlreadyUpdated);
                    orderedList.add(number);
                    toUpdate = toUpdate.stream().filter(x->!orderedList.contains(x)).toList();
                    orderedList.addAll(toUpdate);
                }
                return orderLists(orderedList, input);
            }
        }
        return orderedList; 
    }

    
}