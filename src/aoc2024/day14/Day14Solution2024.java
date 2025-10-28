package aoc2024.day14;

import java.util.HashMap;
import java.util.Map;

import structure.BaseDay;
import utils.Robot;

public class Day14Solution2024 extends BaseDay{

    @Override
    protected Day14Y2024 getInputData(){
        return Day14Y2024.getDay14Data();
    }

    @Override
    public void runDaySolution(){
        Day14Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        input = Day14Y2024.getDay14Data();
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + getSafetyFactor((Day14Y2024)input));
    }

    private static Integer getSafetyFactor(Day14Y2024 input){
        input.getRobots().stream().forEach(robot->getRobotPositionsAfter100Seconds(robot, input.getMaxI(), input.getMaxJ()));
        return calculateSfetyFactor(input) ;
    }

    private static Integer calculateSfetyFactor(Day14Y2024 input){
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

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getTimeOfChristmasTree((Day14Y2024)input));
    }

    private static Integer getTimeOfChristmasTree(Day14Y2024 input){
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

    private static void moveAllRobots(Day14Y2024 input){
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
