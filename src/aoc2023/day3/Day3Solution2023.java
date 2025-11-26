package aoc2023.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import structure.BaseDay;
import utils.Position;

public class Day3Solution2023 extends BaseDay{

    @Override
    protected Day3Y2023 getInputData() {
        return Day3Y2023.getDay3Data();
    }

    @Override
    public void runDaySolution() {
        Day3Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN:"+ getSumOfPartNumbers((Day3Y2023) input));
    }

    private Integer getSumOfPartNumbers(Day3Y2023 input) {
        return getPartNums(input).stream().mapToInt(Integer::valueOf).sum();
    }

    private List<Integer> getPartNums(Day3Y2023 input){
        List<List<Position>> partNumberList = getAllNumbers(input);
        partNumberList = filterPartNumbers(partNumberList, input);
        return parsePartNums(partNumberList);
    }

    private List<List<Position>> getAllNumbers(Day3Y2023 input){
        List<List<Position>> partNumberList = new ArrayList<>();
        Integer maxJ = input.getMaxJ();
        Integer maxI = input.getMaxI();
        for(int i=0;i<=maxI; i++){
            Boolean digitBefore = false;
            List<Position> number = new ArrayList<>();
            for(int j = 0;j<=maxJ;j++){
                Position p = input.getPosition(i, j);
                if(Character.isDigit(p.getValue().charAt(0))){
                    if(!digitBefore){
                        addNumberToList(partNumberList, number);
                        number = new ArrayList<>();
                    }
                    digitBefore = true;
                    number.add(p);
                }else{
                    digitBefore = false;
                }
            }
            addNumberToList(partNumberList, number);
        }
        return partNumberList;
    }

    private List<Integer> parsePartNums(List<List<Position>> partNumberList) {
        return partNumberList.stream().map(number->parseNumber(number)).collect(Collectors.toList());
    }

    private Integer parseNumber(List<Position> number) {
        String parsedNum = "";
        for(Position num : number){
            parsedNum+=num.getValue();
        }
        return Integer.parseInt(parsedNum);
    }

    private List<List<Position>> filterPartNumbers(List<List<Position>> partNumberList, Day3Y2023 input) {
        return partNumberList.stream().filter(partNumber->isPartNumber(partNumber, input)).toList();
    }

    private Boolean isPartNumber(List<Position> partNumber, Day3Y2023 input) {
        return partNumber.stream().anyMatch(num->isCharacterNear(num, input));
    }

    private Boolean isCharacterNear(Position num, Day3Y2023 input) {
        List<Position> possibleNeighbors = new ArrayList<>();
        int i = num.getI();
        int j = num.getJ();
        Position up = input.getPosition(i-1, j);
        Position down = input.getPosition(i+1, j);
        Position left = input.getPosition(i, j-1);
        Position right = input.getPosition(i, j+1);
        Position ul = input.getPosition(i-1, j-1);
        Position ur = input.getPosition(i-1, j+1);
        Position dl = input.getPosition(i+1, j-1);
        Position dr = input.getPosition(i+1, j+1);
        possibleNeighbors.add(left);
        possibleNeighbors.add(down);
        possibleNeighbors.add(right);
        possibleNeighbors.add(up);
        possibleNeighbors.add(ul);
        possibleNeighbors.add(ur);
        possibleNeighbors.add(dl);
        possibleNeighbors.add(dr);
        return possibleNeighbors.stream()
            .filter(neighbor-> neighbor!=null && !neighbor.getValue().equals("."))
            .anyMatch(neighbor-> !Character.isDigit(neighbor.getValue().charAt(0)));
    }

    private static void addNumberToList(List<List<Position>> partNumberList, List<Position> number ){
        if(!number.isEmpty()){
            partNumberList.add(number);
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getSumOfGearRatios((Day3Y2023) input));
    }

    private Long getSumOfGearRatios(Day3Y2023 input) {
        List<List<Position>> numbers = getAllNumbers(input);
        return input.getPositions().stream().filter(pos->pos.getValue().equals("*"))
                .mapToLong(gear->getGearRatio(gear, numbers))
                .sum();
    }

    private Long getGearRatio(Position gear, List<List<Position>> numbers) {
        List<List<Position>> nearNumbers = numbers.stream().filter(number->isNumberNearToGear(gear, number)).toList();
        if(nearNumbers.size()==2){
            return (long) parseNumber(nearNumbers.get(0)) * parseNumber(nearNumbers.get(1));
        }else{
            return 0l;
        }
    }

    private Boolean isNumberNearToGear(Position gear, List<Position> number) {
        return number.stream().anyMatch(position->isNear(gear, position));
    }

    private Boolean isNear(Position gear, Position position) {
        int gearI = gear.getI();
        int gearJ = gear.getJ();
        int pI = position.getI();
        int pJ = position.getJ();
        return Math.abs(gearI - pI)  <= 1 && Math.abs(gearJ - pJ) <= 1;
    }
    
}
