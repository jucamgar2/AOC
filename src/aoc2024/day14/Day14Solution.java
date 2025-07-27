package aoc2024.day14;

import java.util.HashMap;
import java.util.Map;

public class Day14Solution {

    public static void day14Solution(){
        Day14 input = Day14.getDay14Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        input = Day14.getDay14Data();
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day14 input) {
        System.out.println("SOLUCION: " + getSafetyFactor(input));
    }

    private static Integer getSafetyFactor(Day14 input){
        input.getRobots().stream().forEach(robot->getRobotPositionsAfter100Seconds(robot, input.getMaxI(), input.getMaxJ()));
        return calculateSfetyFactor(input) ;
    }

    private static Integer calculateSfetyFactor(Day14 input){
        int midI = input.getMaxI()/2;
        int midJ = input.getMaxJ()/2;
        int[] counts = new int[4];
        input.getRobots().stream().forEach(robot->classifyRobot(robot, counts, midI, midJ));
        return counts[0]*counts[1]*counts[2]*counts[3];
    }
    
    private static void classifyRobot(Robot robot, int[] counts, int midI, int midJ){
        int i = robot.getI();
        int j = robot.getJ();
        if(i<midI && j<midJ){
            counts[0]++;
        }else if(i<midI && j>midJ){
            counts[1]++;
        }else if(i>midI && j<midJ){
            counts[2]++;
        }else if(i>midI && j>midJ){
            counts[3]++;
        }
    }

    private static void getRobotPositionsAfter100Seconds(Robot robot, int maxI, int maxJ){
        int i = robot.getI();
        int j = robot.getJ();
        int di = robot.getDi();
        int dj = robot. getDj();
        i = i + (di*100);
        j = j + (dj*100);
        i = ((i % maxI) + maxI) % maxI;
        j = ((j % maxJ) + maxJ) % maxJ;
        robot.setI(i);
        robot.setJ(j);
    }

    private static void part2Solution(Day14 input) {
        System.out.println("SOLUCION: " + getTimeOfChristmasTree(input));
    }

    private static Integer getTimeOfChristmasTree(Day14 input){
        Map<Integer,Integer> entropyByTime = new HashMap<>();
        for(int second = 1; second<101*103;second++){
            moveAllRobots(input);
            int safety = calculateSfetyFactor(input);
            if(!entropyByTime.containsKey(safety)){
                entropyByTime.put(safety, second);
            }
        }
        int minEntropy = entropyByTime.keySet().stream().mapToInt(x->x.intValue()).min().orElse(0);
        return entropyByTime.get(minEntropy);
    }

    private static void moveAllRobots(Day14 input){
        input.getRobots().forEach(robot->moveRobot(robot, input.getMaxI(), input.getMaxJ()));
    }

    private static void moveRobot(Robot robot, int maxI, int maxJ){
        int i = robot.getI();
        int j = robot.getJ();
        int di = robot.getDi();
        int dj = robot. getDj();
        i = i + (di);
        j = j + (dj);
        i = ((i % maxI) + maxI) % maxI;
        j = ((j % maxJ) + maxJ) % maxJ;
        robot.setI(i);
        robot.setJ(j);
    }
    
}
