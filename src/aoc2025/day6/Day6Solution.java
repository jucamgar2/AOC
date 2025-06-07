package aoc2025.day6;

import java.util.ArrayList;
import java.util.List;

public class Day6Solution {
    public static void day5Solution(){
        Day6 input = Day6.getDay6Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        input = Day6.getDay6Data();
        part2Solution(input);
    }

    /*
    You start by making a map (your puzzle input) of the situation. For example:

    ....#.....
    .........#
    ..........
    ..#.......
    .......#..
    ..........
    .#..^.....
    ........#.
    #.........
    ......#...

    The map shows the current position of the guard with ^ (to indicate the guard is currently facing up from
    the perspective of the map). Any obstructions - crates, desks, alchemical reactors, etc. - are shown as #.

    Lab guards in 1518 follow a very strict patrol protocol which involves repeatedly following these steps:

    If there is something directly in front of you, turn right 90 degrees.
    Otherwise, take a step forward.

    By predicting the guard's route, you can determine which specific positions in the lab will be in the patrol path.
    Including the guard's starting position, the positions visited by the guard before leaving the area are marked with an X:

    ....#.....
    ....XXXXX#
    ....X...X.
    ..#.X...X.
    ..XXXXX#X.
    ..X.X.X.X.
    .#XXXXXXX.
    .XXXXXXX#.
    #XXXXXXX..
    ......#X..

    In this example, the guard will visit 41 distinct positions on your map.

    Predict the path of the guard. How many distinct positions will the guard visit before leaving the mapped area?
     */
    public static void part1Solution(Day6 input){
        findScapeWay(input);
        Long res = input.getPositions().stream().filter(x->x.getValue().equals("X")).count();
        System.out.println("SOLUCIÓN: " + res);
    }

    public static void findScapeWay(Day6 input){
        Position position = input.getCurrentPosition();
        Integer maxI = input.getPositions().stream().mapToInt(x->x.getI()).max().orElse(0);
        Integer maxJ = input.getPositions().stream().mapToInt(x->x.getJ()).max().orElse(0);
        Directions direction = Directions.UP;
        while(position!=null){
            visitPlaces(direction, position,maxI,maxJ, input);
            direction = findNewDirection(direction);
            position = input.getCurrentPosition();
            
        }
    }

    public static void visitPlaces(Directions direction, Position current,Integer maxI, Integer maxJ, Day6 input){
        switch (direction) {
            case UP:
                goUp(current, input);
                break;
            case RIGHT:
                goRight(current, maxJ, input);
                break;
            case DOWN:
                goDown(current, maxI, input);
                break;
            default:
                goLeft(current, input);
                break;
        }
    }

    public static void goUp(Position current, Day6 input){
        Integer currentI = current.getI();
        while(currentI>=0){
            current = input.getPosition(currentI, current.getJ());
            if(current.getValue().equals("#")){
                current = input.getPosition(currentI+1, current.getJ());
                current.setValue("^");
                break;
            }else{
                current.setValue("X");
                currentI--;
            }
        }
    }

    public static void goLeft(Position current, Day6 input){
        Integer currentJ = current.getJ();
        while(currentJ>=0){
            current = input.getPosition(current.getI(), currentJ);
            if(current.getValue().equals("#")){
                current = input.getPosition(current.getI(), currentJ+1);
                current.setValue("^");
                break;
            }else{
                current.setValue("X");
                currentJ--;
            }
        }
    }

    public static void goRight(Position current, Integer maxJ, Day6 input){
        Integer currentJ = current.getJ();
        while(currentJ<=maxJ){
            current = input.getPosition(current.getI(), currentJ);
            if(current.getValue().equals("#")){
                current = input.getPosition(current.getI(), currentJ-1);
                current.setValue("^");
                break;
            }else{
                current.setValue("X");
                currentJ++;
            }
        }
    }

    public static void goDown(Position current, Integer maxI, Day6 input){
        Integer currentI = current.getI();
        while(currentI<=maxI){
            current = input.getPosition(currentI, current.getJ());
            if(current.getValue().equals("#")){
                current = input.getPosition(currentI-1, current.getJ());
                current.setValue("^");
                break;
            }else{
                current.setValue("X");
                currentI++;
            }
        }
    }

    public static Directions findNewDirection(Directions directions){
        switch (directions) {
            case UP:
                return Directions.RIGHT;
            case RIGHT:
                return Directions.DOWN;
            case DOWN:
                return Directions.LEFT;
            default:
                return Directions.UP;
        }
    }

    /*
    Fortunately, they are pretty sure that adding a single new obstruction won't cause a time paradox. 
    They'd like to place the new obstruction in such a way that the guard will get stuck in a loop,
    making the rest of the lab safe to search.

    To have the lowest chance of creating a time paradox, The Historians would like to know all of 
    the possible positions for such an obstruction. The new obstruction can't be placed at the guard's 
    starting position - the guard is there right now and would notice.

    ....#.....
    ....+---+#
    ....|...|.
    ..#.|...|.
    ....|..#|.
    ....|...|.
    .#.O^---+.
    ........#.
    #.........
    ......#...

    It doesn't really matter what you choose to use as an obstacle so long as you and The Historians 
    can put it into position without the guard noticing. The important thing is having enough options 
    that you can find one that minimizes time paradoxes, and in this example, there are 6 different 
    positions you could choose.

    You need to get the guard stuck in a loop by adding a single new obstruction. How many different 
    positions could you choose for this obstruction?
    */

    public static void part2Solution(Day6 input){
        Integer res = findNumberOfLoops(input);
        System.out.println("SOLUCIÓN: " + res);
    }

    public static Integer findNumberOfLoops(Day6 input){
        /*
        Integer loopNumber = 0;
        Day6 scapeway =  input.deepCopy();
        for(Position position:scapeway.getPositions()){
            if(!position.getValue().equals("#")){
                List<Position> positionHistory = new ArrayList<>();
                Day6 mapToTest = input.deepCopy();
                Position newObstacle = mapToTest.getPosition(position.getI(), position.getJ());
                newObstacle.setValue("#");
                Integer maxI = input.getPositions().stream().mapToInt(x->x.getI()).max().orElse(0);
                Integer maxJ = input.getPositions().stream().mapToInt(x->x.getJ()).max().orElse(0);
                Directions direction = Directions.UP;
                Position currentPosition = mapToTest.getCurrentPosition();
                while(currentPosition!=null){
                    if(positionHistory.contains(currentPosition)||isDirectionBlocked(direction, currentPosition, mapToTest)){
                        if(!isDirectionBlocked(direction, currentPosition, mapToTest)){
                            loopNumber++;
                        }
                        break;
                    }
                    positionHistory.add(currentPosition);
                    visitPlaces(direction, currentPosition,maxI,maxJ, mapToTest);
                    direction = findNewDirection(direction);
                    currentPosition = mapToTest.getCurrentPosition();
                }
            }
        }
        return loopNumber;
         */
        Day6 scapeway = input.deepCopy();
    int maxI = input.getPositions().stream().mapToInt(x -> x.getI()).max().orElse(0);
    int maxJ = input.getPositions().stream().mapToInt(x -> x.getJ()).max().orElse(0);

    return (int) scapeway.getPositions().stream()
        .filter(position -> !position.getValue().equals("#"))
        .filter(position -> {
            System.out.println(position);
            Day6 mapToTest = input.deepCopy();
            Position newObstacle = mapToTest.getPosition(position.getI(), position.getJ());
            newObstacle.setValue("#");
            Directions direction = Directions.UP;
            Position currentPosition = mapToTest.getCurrentPosition();
            List<Position> positionHistory = new ArrayList<>();
            while(currentPosition != null){
                if(positionHistory.contains(currentPosition) || isDirectionBlocked(direction, currentPosition, mapToTest)){
                    return !isDirectionBlocked(direction, currentPosition, mapToTest);
                }
                positionHistory.add(currentPosition);
                visitPlaces(direction, currentPosition, maxI, maxJ, mapToTest);
                direction = findNewDirection(direction);
                currentPosition = mapToTest.getCurrentPosition();
            }
            return false;
        })
        .count();
    }

    public static boolean isDirectionBlocked(Directions direction, Position currentPosition, Day6 map){
        switch (direction) {
            case UP:
                return map.getPosition(currentPosition.getI()-1, currentPosition.getJ()).getValue().equals("#");
            case RIGHT:
                return map.getPosition(currentPosition.getI(), currentPosition.getJ()+1).getValue().equals("#");
            case DOWN:
                return map.getPosition(currentPosition.getI()+1, currentPosition.getJ()).getValue().equals("#");
            default:
                return map.getPosition(currentPosition.getI(), currentPosition.getJ()-1).getValue().equals("#");
        }
    }

}
