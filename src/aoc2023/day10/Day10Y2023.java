package aoc2023.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import utils.Direction;
import utils.Graph;
import utils.Position;
import utils.PositionBoard;

public class Day10Y2023 {
    
    private Graph pipes;

    private PositionBoard positionBoard;

    public Graph getPipes(){
        return this.pipes;
    }

    public PositionBoard getPositionBoard(){
        return this.positionBoard;
    }

    public Day10Y2023(Graph pipes, PositionBoard positionBoard){
        this.pipes = pipes;
        this.positionBoard = positionBoard;
    }

    private static Set<Direction> getConnections(char c) {
        return switch (c) {
            case '|' -> Set.of(Direction.NORTH, Direction.SOUTH);
            case '-' -> Set.of(Direction.EAST, Direction.WEST);
            case 'L' -> Set.of(Direction.NORTH, Direction.EAST);
            case 'J' -> Set.of(Direction.NORTH, Direction.WEST);
            case '7' -> Set.of(Direction.SOUTH, Direction.WEST);
            case 'F' -> Set.of(Direction.SOUTH, Direction.EAST);
            case 'S' -> Set.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
            default  -> Set.of(); 
        };
    }

    public static Day10Y2023 readDay10Data(){
        Graph pipes = new Graph();
        List<Position> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/TestDay10.txt"))) {
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                char[] line = lines.get(i).toCharArray();
                for(int j = 0;j<line.length;j++){
                    positions.add(new Position(i, j, String.valueOf(line[j]+","+i+","+j)));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        buildGraph(pipes, positions);
        return new Day10Y2023(pipes, new PositionBoard(positions));
    }

    private static void buildGraph(Graph graph, List<Position> positions) {
        PositionBoard pb = new PositionBoard(positions);
        for (Position p : positions) {
            char current = p.getValue().charAt(0);
            if (current == '.') continue;
            graph.addNode(p.getValue());
            Set<Direction> dirs = getConnections(current);
            for (Direction d : dirs) {
                Position neighbor = pb.getPosition(p.getI() + d.getDi(), p.getJ() + d.getDj());
                if (neighbor == null) continue;
                char neighborChar = neighbor.getValue().charAt(0);
                if (neighborChar == '.') continue;
                Set<Direction> neighborDirs = getConnections(neighborChar);
                if (neighborDirs.contains(d.opposite())) {
                    graph.addEdge(p.getValue(), neighbor.getValue(), String.valueOf(current));
                    graph.addEdge(neighbor.getValue(), p.getValue(), String.valueOf(neighborChar));
                }
            }
        }
    }
}
