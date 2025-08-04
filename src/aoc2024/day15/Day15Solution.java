package aoc2024.day15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc2024.day6.Position;

public class Day15Solution {

    public static void day15Solution(){
        Day15 input = Day15.getDay15Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        input = Day15.getDay15Data();
        part2Solution(input);
    }

    private static void part1Solution(Day15 input) {
        System.out.println("SOLUCION: " + getSumOfCoordinates(input));
    }

    private static Long getSumOfCoordinates(Day15 input) {
        executeInstructions(input);
        return input.getPositions().stream().filter(x->x.getValue().equals("O")).mapToLong(box-> 100*box.getI() + box.getJ()).sum();
    }

    private static void executeInstructions(Day15 input){
        Position robot = input.getRobotPosition();
        for(char instruction: input.getInstructions().toCharArray()){
            if(instruction == '>'){
                robot = goRight(input, robot);
            }else if(instruction == '<'){
                robot = goLeft(input, robot);
            }else if(instruction == 'v'){
                robot = goDown(input, robot);
            }else if(instruction == '^'){
                robot = goUp(input, robot);
            }
        }
    }

    private static Position goLeft(Day15 input, Position robot){
        int robotJ = robot.getJ();
        Position leftPosition =input.getPosition(robot.getI(), robotJ-1);
        if(!leftPosition.getValue().equals("#")){
            if(leftPosition.getValue().equals(".")){
                robot.setJ(robotJ-1);
                leftPosition.setJ(robotJ);
            }else{
                int j = leftPosition.getJ()-1;
                int i = robot.getI();
                leftPosition = input.getPosition(i, j);
                while (!leftPosition.getValue().equals(".") && !leftPosition.getValue().equals("#")) {
                    j--;
                    leftPosition = input.getPosition(i, j);
                }
                if(leftPosition.getValue().equals(".")){
                    int jDistance = robot.getJ()-j;
                    for(int x=0; x<=jDistance;x++){
                        if(x==0){
                            input.getPosition(i, robotJ-x).setValue(".");
                        }else if(x==1){
                            input.getPosition(i, robotJ-x).setValue("@");
                            robot = input.getRobotPosition();
                        }else{
                            input.getPosition(i, robotJ-x).setValue("O");
                        }
                    }
                }
            }
        }
        return robot;
    }

    private static Position goRight(Day15 input, Position robot){
        int robotJ = robot.getJ();
        Position rightPosition = input.getPosition(robot.getI(), robotJ+1);
        if(!rightPosition.getValue().equals("#")){
            if(rightPosition.getValue().equals(".")){
                robot.setJ(robotJ+1);
                rightPosition.setJ(robotJ);
            }else{
                int j = rightPosition.getJ()+1;
                int i = robot.getI();
                rightPosition = input.getPosition(i, j);
                while (!rightPosition.getValue().equals(".") && !rightPosition.getValue().equals("#")) {
                    j++;
                    rightPosition = input.getPosition(i, j);
                }
                if(rightPosition.getValue().equals(".")){
                    int jDistance = j-robot.getJ();
                    for(int x=0; x<=jDistance;x++){
                        if(x==0){
                            input.getPosition(i, robotJ+x).setValue(".");
                        }else if(x==1){
                            input.getPosition(i, robotJ+x).setValue("@");
                            robot = input.getRobotPosition();
                        }else{
                            input.getPosition(i, robotJ+x).setValue("O");
                        }
                    }
                }
            }
        }
        return robot;
    }

    private static Position goUp(Day15 input, Position robot){
        int robotI = robot.getI();
        Position upPosition = input.getPosition(robotI-1, robot.getJ());
        if(!upPosition.getValue().equals("#")){
            if(upPosition.getValue().equals(".")){
                robot.setI(robotI-1);
                upPosition.setI(robotI);
            }else{
                int i = upPosition.getI()-1;
                int j = robot.getJ();
                upPosition = input.getPosition(i, j);
                while (!upPosition.getValue().equals(".") && !upPosition.getValue().equals("#")) {
                    i--;
                    upPosition = input.getPosition(i, j);
                }
                if(upPosition.getValue().equals(".")){
                    int iDistance = robot.getI()-i;
                    for(int x=0; x<=iDistance;x++){
                        if(x==0){
                            input.getPosition(robotI-x, j).setValue(".");
                        }else if(x==1){
                            input.getPosition(robotI-x, j).setValue("@");
                            robot = input.getRobotPosition();
                        }else{
                            input.getPosition(robotI-x, j).setValue("O");
                        }
                    }
                }
            }
        }
        return robot;
    }

    private static Position goDown(Day15 input, Position robot){
        int robotI = robot.getI();
        Position downPosition = input.getPosition(robotI+1, robot.getJ());
        if(!downPosition.getValue().equals("#")){
            if(downPosition.getValue().equals(".")){
                robot.setI(robotI+1);
                downPosition.setI(robotI);
            }else{
                int i = downPosition.getI()+1;
                int j = robot.getJ();
                downPosition = input.getPosition(i, j);
                while (!downPosition.getValue().equals(".") && !downPosition.getValue().equals("#")) {
                    i++;
                    downPosition = input.getPosition(i, j);
                }
                if(downPosition.getValue().equals(".")){
                    int iDistance = i-robot.getI();
                    for(int x=0; x<=iDistance;x++){
                        if(x==0){
                            input.getPosition(robotI+x, j).setValue(".");
                        }else if(x==1){
                            input.getPosition(robotI+x, j).setValue("@");
                            robot = input.getRobotPosition();
                        }else{
                            input.getPosition(robotI+x, j).setValue("O");
                        }
                    }
                }
            }
        }
        return robot;
    }

    private static void part2Solution(Day15 input) {
        System.out.println("SOLUCION: " + getSumOfCoordinatesDubbleMap(input));
    }

    private static Long getSumOfCoordinatesDubbleMap(Day15 input) {
        input = generateDubbleMap(input);
        executeInstructionsDubbleMap(input);
        return input.getPositions().stream().filter(x->x.getValue().equals("[")).mapToLong(box-> 100*box.getI() + box.getJ()).sum();
    }

    private static Day15 generateDubbleMap(Day15 input) {
        List<Position> newPositions = new ArrayList<>();
        for(Position position: input.getPositions()){
            int i = position.getI();
            int j = position.getJ();
            String value1 = "";
            String value2 = "";
            if(position.getValue().equals("#")){
                value1 = "#";
                value2 = "#";  
            }else if(position.getValue().equals(".")){
                value1 = ".";
                value2 = ".";  
            }else if(position.getValue().equals("@")){
                value1 = "@";
                value2 = ".";  
            }else{
                value1 = "[";
                value2 = "]";  
            }
            Position p1 = new Position(i, j*2, value1);
            Position p2 = new Position(i, j*2+1, value2);
            newPositions.add(p1);
            newPositions.add(p2);
        }
        return new Day15(newPositions, input.getInstructions());
    }

    private static void executeInstructionsDubbleMap(Day15 input) {
        Position robot = input.getRobotPosition();
        for(char instruction: input.getInstructions().toCharArray()){
            if(instruction == '>'){
                robot = goRightDm(input, robot);
            }else if(instruction == '<'){
                robot = goLeftDm(input, robot);
            }else if(instruction == 'v'){
                robot = goDownDm(input, robot);
            }else if(instruction == '^'){
                robot = goUpDm(input, robot);
            }
        }
    }

    private static Position goLeftDm(Day15 input, Position robot){
        int robotJ = robot.getJ();
        Position leftPosition =input.getPosition(robot.getI(), robotJ-1);
        if(!leftPosition.getValue().equals("#")){
            if(leftPosition.getValue().equals(".")){
                robot.setJ(robotJ-1);
                leftPosition.setJ(robotJ);
            }else{
                int j = leftPosition.getJ()-2;
                int i = robot.getI();
                leftPosition = input.getPosition(i, j);
                while (!leftPosition.getValue().equals(".") && !leftPosition.getValue().equals("#")) {
                    j-=2;
                    leftPosition = input.getPosition(i, j);
                }
                if(leftPosition.getValue().equals(".")){
                    int jDistance = robot.getJ()-j;
                    for(int x=0; x<=jDistance;x++){
                        if(x==0){
                            input.getPosition(i, robotJ-x).setValue(".");
                        }else if(x==1){
                            input.getPosition(i, robotJ-x).setValue("@");
                            robot = input.getRobotPosition();
                        }else{
                            if(x%2==0){
                                input.getPosition(i, robotJ-x).setValue("]");
                            }else{
                                input.getPosition(i, robotJ-x).setValue("[");
                            }
                            
                        }
                    }
                }
            }
        }
        return robot;
    }

    private static Position goRightDm(Day15 input, Position robot){
        int robotJ = robot.getJ();
        Position rightPosition = input.getPosition(robot.getI(), robotJ+1);
        if(!rightPosition.getValue().equals("#")){
            if(rightPosition.getValue().equals(".")){
                robot.setJ(robotJ+1);
                rightPosition.setJ(robotJ);
            }else{
                int j = rightPosition.getJ()+2;
                int i = robot.getI();
                rightPosition = input.getPosition(i, j);
                while (!rightPosition.getValue().equals(".") && !rightPosition.getValue().equals("#")) {
                    j+=2;
                    rightPosition = input.getPosition(i, j);
                }
                if(rightPosition.getValue().equals(".")){
                    int jDistance = j-robot.getJ();
                    for(int x=0; x<=jDistance;x++){
                        if(x==0){
                            input.getPosition(i, robotJ+x).setValue(".");
                        }else if(x==1){
                            input.getPosition(i, robotJ+x).setValue("@");
                            robot = input.getRobotPosition();
                        }else{
                            if(x%2==0){
                                input.getPosition(i, robotJ+x).setValue("[");
                            }else{
                                input.getPosition(i, robotJ+x).setValue("]");
                            }
                            
                        }
                    }
                }
            }
        }
        return robot;
    }

    private static Position goUpDm(Day15 input, Position robot){
        int robotI = robot.getI();
        Position upPosition = input.getPosition(robotI-1, robot.getJ());
        if(!upPosition.getValue().equals("#")){
            if(upPosition.getValue().equals(".")){
                robot.setI(robotI-1);
                upPosition.setI(robotI);
            }else{
                moveUp(robot, input);
            }
        }
        robot = input.getRobotPosition();
        return robot;
    }

    private static void moveUp(Position robot, Day15 input){
        Set<Position> involvedPositions = new HashSet<Position>();
        int robotI = robot.getI();
        int robotJ = robot.getJ();
        int i = robot.getI()-1;
        involvedPositions.add(input.getPosition(i, robot.getJ()));
        Boolean canMove = false;
        Boolean canContinue = true;
        while(canContinue && !canMove){
            int currentI = i;
            if(currentI==robotI-1){
                Position up = input.getPosition(currentI, robot.getJ());
                if(up.getValue().equals("[")){
                    involvedPositions.add(input.getPosition(currentI, robot.getJ()+1));
                }else{
                    involvedPositions.add(input.getPosition(currentI, robot.getJ()-1));
                }
            }else{
                List<Integer> js = involvedPositions.stream().filter(pos->pos.getI().equals(currentI+1)).map(pos->pos.getJ()).toList();
                List<Position> boxesInLevel = new ArrayList<>();
                for(int j: js){
                    Position p = input.getPosition(currentI, j);
                    if(p.getValue().equals("[")){
                        boxesInLevel.add(p);
                        boxesInLevel.add(input.getPosition(currentI, j+1));
                    }else if(p.getValue().equals("]")){
                        boxesInLevel.add(p);
                        boxesInLevel.add(input.getPosition(currentI, j-1));
                    }else if(p.getValue().equals("#")){
                        canContinue = false;
                        break;
                    }
                }
                if(!canContinue){
                    break;
                }
                if(boxesInLevel.isEmpty()){
                    canContinue= false;
                    canMove = true;
                    break;
                }else{
                    involvedPositions.addAll(boxesInLevel);
                }
            }
            if(canContinue&&!canMove){
                i--;
            }
            
        }
        if(canMove){
            for(int x = i; x<=robotI;x++){
                final int currentX = x;
                if(currentX==robotI){
                    input.getPosition(currentX, robotJ).setValue(".");
                }else if(currentX==robotI-1){
                    input.getPosition(currentX,robotJ).setValue("@");
                }else{
                    involvedPositions.stream().filter(pos->pos.getI().equals(currentX+1)).forEach(pos->{
                        int posJ = pos.getJ();
                        input.getPosition(currentX, posJ).setValue(pos.getValue());
                        input.getPosition(currentX+1, posJ).setValue(".");
                    });
                }
            }
        }
    }

    private static Position goDownDm(Day15 input, Position robot){
        int robotI = robot.getI();
        Position downPosition = input.getPosition(robotI+1, robot.getJ());
        if(!downPosition.getValue().equals("#")){
            if(downPosition.getValue().equals(".")){
                robot.setI(robotI+1);
                downPosition.setI(robotI);
            }else{
                moveDown(robot, input);
            }
        }
        robot = input.getRobotPosition();
        return robot;
    }

    private static void moveDown(Position robot, Day15 input){
        Set<Position> involvedPositions = new HashSet<Position>();
        int robotI = robot.getI();
        int robotJ = robot.getJ();
        int i = robotI + 1;
        involvedPositions.add(input.getPosition(i, robotJ));
        Boolean canMove = false;
        Boolean canContinue = true;
        while(canContinue && !canMove){
            int currentI = i;
            if(currentI == robotI + 1){
                Position down = input.getPosition(currentI, robotJ);
                if(down.getValue().equals("[")){
                    involvedPositions.add(input.getPosition(currentI, robotJ + 1));
                }else{
                    involvedPositions.add(input.getPosition(currentI, robotJ - 1));
                }
            }else{
                List<Integer> js = involvedPositions.stream().filter(pos -> pos.getI().equals(currentI - 1)).map(pos -> pos.getJ()).toList();
                List<Position> boxesInLevel = new ArrayList<>();
                for(int j: js){
                    Position p = input.getPosition(currentI, j);
                    if(p.getValue().equals("[")){
                        boxesInLevel.add(p);
                        boxesInLevel.add(input.getPosition(currentI, j + 1));
                    }else if(p.getValue().equals("]")){
                        boxesInLevel.add(p);
                        boxesInLevel.add(input.getPosition(currentI, j - 1));
                    }else if(p.getValue().equals("#")){
                        canContinue = false;
                        break;
                    }
                }
                if(!canContinue){
                    break;
                }
                if(boxesInLevel.isEmpty()){
                    canContinue = false;
                    canMove = true;
                    break;
                }else{
                    involvedPositions.addAll(boxesInLevel);
                }
            }
            if(canContinue && !canMove){
                i++;
            }
        }
        if(canMove){
            for(int x = i; x >= robotI; x--){
                final int currentX = x;
                if(currentX == robotI){
                    input.getPosition(currentX, robotJ).setValue(".");
                }else if(currentX == robotI + 1){
                    input.getPosition(currentX, robotJ).setValue("@");
                }else{
                    involvedPositions.stream().filter(pos -> pos.getI().equals(currentX - 1)).forEach(pos -> {
                        int posJ = pos.getJ();
                        input.getPosition(currentX, posJ).setValue(pos.getValue());
                        input.getPosition(currentX - 1, posJ).setValue(".");
                    });
                }
            }
        }
    }

}
