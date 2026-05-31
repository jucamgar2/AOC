package aoc2023.day16;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import structure.BaseDay;
import utils.Position;
import utils.PositionBoard;
import utils.Tour;
import utils.TourDirection;

public class Day16Solution2023 extends BaseDay{

    @Override
    protected Day16Y2023 getInputData() {
        return Day16Y2023.readDay16Data();
    }

    @Override
    public void runDaySolution() {
        Day16Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + countEnergizedTiles((Day16Y2023) input));
    }

    private Integer countEnergizedTiles(Day16Y2023 input) {
        PositionBoard board = input.getCavern();
        Set<Tour> visited = new HashSet<>();
        Set<Position> energized = new HashSet<>();
        energizeTiles(board, visited, energized, board.getPosition(0, 0), TourDirection.RIGHT);
        return energized.size();
    }

    private void energizeTiles(PositionBoard board, Set<Tour> visited, Set<Position> energized, Position first, TourDirection direction) {
        Stack<Tour> remainingPaths = new Stack<>();
        processFirstPosition(remainingPaths, energized, first, direction);

        while(!remainingPaths.isEmpty()){
            Tour current = remainingPaths.pop();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            energized.add(current.getPosition());
            Position next = getNextPosition(current, board);
            if(next!=null){
                energized.add(next);
                if(next.getValue().equals(".") ){
                    remainingPaths.push(new Tour(next, current.getDirection()));
                }else{
                    changeDirection(board, current, next, remainingPaths);
                }
            }
            remainingPaths.removeIf(tour -> tour.getPosition() == null);
        }
    }

    private void changeDirection(PositionBoard board, Tour current, Position next, Stack<Tour> remainingPaths) {
        switch (next.getValue()) {
            case "-":
                energizeHorizontalSplitter( next, current, remainingPaths);
                break;
            case "|":
                energizeVerticalSplitter( next, current, remainingPaths);
                break;
            case "/":
                energizeRightMirror( next, current, remainingPaths);
                break;
            case "\\":
                energizeLeftMirror( next, current, remainingPaths);
                break;
            default:
                break;
        }
    }

    private void energizeLeftMirror( Position next, Tour current, 
            Stack<Tour> remainingPaths) {

        switch (current.getDirection()) {
            case RIGHT:
                remainingPaths.push(new Tour(next, TourDirection.DOWN));
                break;
            case LEFT:
                remainingPaths.push(new Tour(next, TourDirection.UP));
                break;
            case UP:
                remainingPaths.push(new Tour(next, TourDirection.LEFT));
                break;
            default:
                remainingPaths.push(new Tour(next, TourDirection.RIGHT));
                break;
        }
    }

    private void energizeRightMirror(Position next, Tour current, 
            Stack<Tour> remainingPaths) {

        switch (current.getDirection()) {
            case RIGHT:
                remainingPaths.push(new Tour(next, TourDirection.UP));
                break;
            case LEFT:
                remainingPaths.push(new Tour(next, TourDirection.DOWN));
                break;
            case UP:
                remainingPaths.push(new Tour(next, TourDirection.RIGHT));
                break;
            default:
                remainingPaths.push(new Tour(next, TourDirection.LEFT));
                break;
        }
    }

    private void energizeVerticalSplitter(Position next, Tour current,
            Stack<Tour> remainingPaths) {

        if(current.getDirection().equals(TourDirection.UP) || 
            current.getDirection().equals(TourDirection.DOWN)){
            remainingPaths.push(new Tour(next, current.getDirection()));
        }else{
            remainingPaths.push(new Tour(next, TourDirection.UP));
            remainingPaths.push(new Tour(next, TourDirection.DOWN));
        }
    }

    private void energizeHorizontalSplitter(Position next, Tour current, 
            Stack<Tour> remainingPaths) {

        if(current.getDirection().equals(TourDirection.LEFT) || 
            current.getDirection().equals(TourDirection.RIGHT)){
            remainingPaths.push(new Tour(next, current.getDirection()));
        }else{
            remainingPaths.push(new Tour(next, TourDirection.LEFT));
            remainingPaths.push(new Tour(next, TourDirection.RIGHT));
        }
    }

    private Position getNextPosition(Tour current, PositionBoard board) {
        int i = current.getPosition().getI();
        int j = current.getPosition().getJ();
        switch (current.getDirection()) {
            case RIGHT:
                return board.getPosition(i, j+1);
            case LEFT:
                return board.getPosition(i, j-1);
            case UP:
                return board.getPosition(i-1, j);
            default:
                return board.getPosition(i+1, j);
        }
    }

    private void processFirstPosition(Stack<Tour> remainingPaths,
            Set<Position> energized, Position first, TourDirection direction) {

        energized.add(first);

        Tour firstTour = new Tour(first, direction);

        if(first.getValue().equals(".")){
            remainingPaths.push(firstTour);
        } else {
            changeDirection(
                    null,
                    firstTour,
                    first,
                    remainingPaths);
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + findBestConfiguration((Day16Y2023) input));
    }

    private Integer findBestConfiguration(Day16Y2023 input) {
        PositionBoard board = input.getCavern();

        int rows = board.getMaxI();
        int cols = board.getMaxJ();

        int max = 0;

        for(int j = 0; j < cols; j++){

            max = Math.max(max,
                calculateConfiguration(
                    board,
                    board.getPosition(0, j),
                    TourDirection.DOWN));
        }

        for(int j = 0; j < cols; j++){

            max = Math.max(max,
                calculateConfiguration(
                    board,
                    board.getPosition(rows - 1, j),
                    TourDirection.UP));
        }

        for(int i = 0; i < rows; i++){

            max = Math.max(max,
                calculateConfiguration(
                    board,
                    board.getPosition(i, 0),
                    TourDirection.RIGHT));
        }

        for(int i = 0; i < rows; i++){

            max = Math.max(max,
                calculateConfiguration(
                    board,
                    board.getPosition(i, cols - 1),
                    TourDirection.LEFT));
        }

        return max;
    }

    private Integer calculateConfiguration(PositionBoard board, Position start, TourDirection direction) {
        Set<Tour> visited = new HashSet<>();
        Set<Position> energized = new HashSet<>();

        energizeTiles(
                board,
                visited,
                energized,
                start,
                direction);

        return energized.size();
    }
    
}
