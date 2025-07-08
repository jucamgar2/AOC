package aoc2024.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11Solution {
    
    public static void day11Solution(){
        Day11 input = Day11.getDay11Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input,6);
        System.out.println("--------------------------PART2--------------------------");
        part1Solution(input,25);
    }
    
    private static void part1Solution(Day11 input, Integer iterations){
        System.out.println("SOLUCION: " + getNumberOfStones(input, iterations));
    }

    private static Integer getNumberOfStones(Day11 input, Integer iterations){
        String stones = input.getStones();
        String[] separateStones = stones.split(" ");
        List<String> stonesList = new ArrayList<>(Arrays.asList(separateStones));
        for(int i = 0;i<iterations;i++){
            List<String> auxiliarList = new ArrayList<>();
            stonesList.stream().forEach(stone->transformStone(stone, auxiliarList));  
            stonesList = auxiliarList;
        }
        return stonesList.size();
    }

    private static void transformStone(String stone, List<String> stoneList){
        if(stone.equals("0")){
            stoneList.add("1");
        }else if(stone.length()%2==0){
            Integer firstNumber = Integer.parseInt(stone.substring(0, stone.length()/2));
            stoneList.add(firstNumber.toString());
            Integer secondNumber = Integer.parseInt(stone.substring(stone.length()/2, stone.length()));
            stoneList.add(secondNumber.toString());
        }else{
            Long newStone = Long.parseLong(stone)*2024;
            stoneList.add(newStone.toString());
        }
    }
    
}
