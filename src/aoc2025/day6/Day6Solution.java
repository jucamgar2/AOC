package aoc2025.day6;


public class Day6Solution {
    public static void day5Solution(){
        Day6 input = Day6.getDay6Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
    }

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


}
