package aoc2023.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import utils.Position;

public class Day17Y2023 {

    private int [][] lavaPool;
   
    public int [][] getLavaPoll(){
        return this.lavaPool;
    }

    public Day17Y2023(int [][] lavaPool){
        this.lavaPool = lavaPool;
    }



    public static Day17Y2023 readDay17Data(){
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/TestDay17.txt"))) {
            List<String> lines = br.lines().toList();
            int rows = lines.size();
            int columns = lines.get(0).length();
            int[][] lavaPool = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                char[] line = lines.get(i).toCharArray();

                for (int j = 0; j < columns; j++) {
                    lavaPool[i][j] = line[j] - '0';
                }
            }
            return new Day17Y2023(lavaPool);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}