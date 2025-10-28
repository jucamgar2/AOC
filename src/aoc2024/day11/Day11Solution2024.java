package aoc2024.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import structure.BaseDay;

public class Day11Solution2024 extends BaseDay{

    @Override
    protected Day11Y2024 getInputData(){
        return Day11Y2024.getDay11Data();
    }
    
    @Override
    public void runDaySolution(){
        Day11Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part1Solution(input);
    }
    
    @Override
    protected void part1Solution(Object input){
        System.out.println("SOLUCION: " + getNumberOfStones((Day11Y2024)input, 6));
    }

    private static Integer getNumberOfStones(Day11Y2024 input, Integer iterations){
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

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getNumberOfStones((Day11Y2024)input, 25));
    }
    
}
