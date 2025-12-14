package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PositionBoard {

    private List<Position> positions;

    public List<Position> getPositions(){
        return this.positions;
    }

    public PositionBoard(List<Position> positions){
        this.positions = positions;
    }
    
    public Position getPosition(int i, int j){
        return positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }

    public List<Position> getEightNeighbors(Position p){
        int i = p.getI();
        int j = p.getJ();
        List<Position> possibleNeighbors = new ArrayList<>();
        Position up = getPosition(i-1, j);
        Position down = getPosition(i+1, j);
        Position left = getPosition(i, j-1);
        Position right = getPosition(i, j+1);
        Position ul = getPosition(i-1, j-1);
        Position ur = getPosition(i-1, j+1);
        Position dl = getPosition(i+1, j-1);
        Position dr = getPosition(i+1, j+1);
        possibleNeighbors.add(left);
        possibleNeighbors.add(down);
        possibleNeighbors.add(right);
        possibleNeighbors.add(up);
        possibleNeighbors.add(ul);
        possibleNeighbors.add(ur);
        possibleNeighbors.add(dl);
        possibleNeighbors.add(dr);
        return possibleNeighbors.stream().filter(pos->pos!=null).toList();
    }

    public int getMaxI(){
        return this.positions.stream().mapToInt(x->x.getI()).max().orElse(0);
    }

    public int getMaxJ(){
        return this.positions.stream().mapToInt(x->x.getJ()).max().orElse(0);
    }

    public static PositionBoard readPositionBoard(String ref){
        List<Position> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ref))) {
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                char[] line = lines.get(i).toCharArray();
                for(int j = 0;j<line.length;j++){
                    positions.add(new Position(i, j, String.valueOf(line[j])));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new PositionBoard(positions);
    }
}
