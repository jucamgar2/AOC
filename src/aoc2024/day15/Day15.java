package aoc2024.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aoc2024.day6.Position;

public class Day15 {

    private List<Position> positions;

    private String instructions;

    public List<Position> getPositions(){
        return this.positions;
    }

    public String getInstructions(){
        return this.instructions;
    }

    public Position getRobotPosition(){
        return this.positions.stream().filter(x->x.getValue().equals("@")).findAny().orElse(null);
    }

    public Position getPosition(int i, int j){
        return this.positions.stream().filter(positions->positions.getI() == i && positions.getJ()==j).findFirst().orElse(null);
    }
    
    public Day15(List<Position> positions, String instructions){
        this.positions = positions;
        this.instructions = instructions;
    }

    public static Day15 getDay15Data(){
        List<Position> positions = new ArrayList<>();
        StringBuilder instructions = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay15.txt"))) {
            List<String> lines = br.lines().toList();
            for(int i = 0;i<lines.size();i++){
                if(lines.get(i).charAt(0)=='#'){
                    char[] line = lines.get(i).toCharArray();
                    for(int j = 0;j<line.length;j++){
                        positions.add(new Position(i, j, String.valueOf(line[j])));
                    }
                }else{
                    instructions.append(lines.get(i));
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day15(positions, instructions.toString());
    }

    public void printBoard() {
        int maxI = positions.stream().mapToInt(Position::getI).max().orElse(0);
        int maxJ = positions.stream().mapToInt(Position::getJ).max().orElse(0);

        String[][] board = new String[maxI + 1][maxJ + 1];
        for (Position pos : positions) {
            board[pos.getI()][pos.getJ()] = pos.getValue();
        }

        for (int i = 0; i <= maxI; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= maxJ; j++) {
                sb.append(board[i][j] != null ? board[i][j] : " ");
            }
            System.out.println(sb.toString());
        }
    }
}
