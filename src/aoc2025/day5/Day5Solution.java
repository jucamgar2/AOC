package aoc2025.day5;

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

    /*
    The first section specifies the page ordering rules, one per line. The first rule, 47|53, means that 
    if an update includes both page number 47 and page number 53, then page number 47 must be printed at some 
    point before page number 53. (47 doesn't necessarily need to be immediately before 53; other pages are 
    allowed to be between them.)

    The second section specifies the page numbers of each update. Because most safety manuals are different, 
    the pages needed in the updates are different too. The first update, 75,47,61,53,29, means that the update 
    consists of page numbers 75, 47, 61, 53, and 29.
    
    In the above example, the first update (75,47,61,53,29) is in the right order:

    75 is correctly first because there are rules that put each other page after it: 75|47, 75|61, 75|53, and 75|29.
    47 is correctly second because 75 must be before it (75|47) and every other page must be after it according to 47|61, 47|53, and 47|29.
    61 is correctly in the middle because 75 and 47 are before it (75|61 and 47|61) and 53 and 29 are after it (61|53 and 61|29).
    53 is correctly fourth because it is before page number 29 (53|29).
    29 is the only page left and so is correctly last.

    Because the first update does not include some page numbers, the ordering rules involving those missing page numbers are ignored.

    The second and third updates are also in the correct order according to the rules. Like the first update, they also do not include every page number, and so only some of the ordering rules apply - within each update, the ordering rules that involve missing page numbers are not used.

    The fourth update, 75,97,47,61,53, is not in the correct order: it would print 75 before 97, which violates the rule 97|75.

    The fifth update, 61,13,29, is also not in the correct order, since it breaks the rule 29|13.

    The last update, 97,13,75,29,47, is not in the correct order due to breaking several rules.

    For some reason, the Elves also need to know the middle page number of each update being printed. Because you are currently only printing the correctly-ordered updates, you will need to find the middle page number of each correctly-ordered update. In the above example, the correctly-ordered updates are:

    75,47,61,53,29
    97,61,53,29,13
    75,29,13

    These have middle page numbers of 61, 53, and 29 respectively. Adding these page numbers together gives 143.
    */
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

    /*
    While the Elves get to work printing the correctly-ordered updates, you have a little time to fix the rest of them.

    For each of the incorrectly-ordered updates, use the page ordering rules to put the page numbers in the right order.
     For the above example, here are the three incorrectly-ordered updates and their correct orderings:

    75,97,47,61,53 becomes 97,75,47,61,53.
    61,13,29 becomes 61,29,13.
    97,13,75,29,47 becomes 97,75,47,29,13.

    After taking only the incorrectly-ordered updates and ordering them correctly, their middle page numbers are 47, 29, and 47.
     Adding these together produces 123.

    Find the updates which are not in the correct order. What do you get if you add up the middle page numbers after correctly 
    ordering just those updates?
     */

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