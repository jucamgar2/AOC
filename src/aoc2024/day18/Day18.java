package aoc2024.day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aoc2024.day6.Position;

public class Day18 {

    private List<Position> bytesToFall;

    private List<Position> grid;
    
    public List<Position> getBytesToFall(){
        return this.bytesToFall;
    }

    public Position getPosition(int i, int j){
        return this.grid.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public List<Position> getGrid(){
        return this.grid;
    }

    public Day18(List<Position> grid, List<Position> bytesToFall){
        this.grid = grid;
        this.bytesToFall = bytesToFall;
    }

    public Position getEndPosition(){
        int maxI = this.grid.stream().mapToInt(pos->pos.getI()).max().orElse(0);
        int maxJ = this.grid.stream().mapToInt(pos->pos.getJ()).max().orElse(0);
        return getPosition(maxI, maxJ);
    }

    public static Day18 getDay18Data(){
        List<Position> grid = new ArrayList<>();
        List<Position> bytesToFall = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay18.txt"))) {
            br.lines().forEach(line->{
                String[] pos = line.split(",");
                Position p = new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), null);
                bytesToFall.add(p);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        int gridSize = 6;
        if (bytesToFall.size()>=1024) {
            gridSize = 70;
        }
        for(int i = 0; i<=gridSize;i++){
            for(int j = 0; j<= gridSize; j++){
                Position pos = new Position(i, j, ".");
                grid.add(pos);
            }
        }
        return new Day18(grid, bytesToFall);
    }
}
