package aoc2023.day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day22Y2023 {

    private List<Brick> bricks;

    public List<Brick> getBricks(){
        return this.bricks;
    }

    public Day22Y2023(List<Brick> bricks){
        this.bricks = bricks;
    }

    public static Day22Y2023 readDay22Data(){
        List<Brick> bricks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/TestDay22.txt"))) {
            int id = 0;
            String brick = br.readLine();
            while(brick != null){
                String[] brickSpace = brick.split("~");
                int x1 = Integer.parseInt(brickSpace[0].split(",")[0]);
                int y1 = Integer.parseInt(brickSpace[0].split(",")[1]);
                int z1 = Integer.parseInt(brickSpace[0].split(",")[2]);
                int x2 = Integer.parseInt(brickSpace[1].split(",")[0]);
                int y2 = Integer.parseInt(brickSpace[1].split(",")[1]);
                int z2 = Integer.parseInt(brickSpace[1].split(",")[2]);
                bricks.add(new Brick(id, x1, y1, z1, x2, y2, z2));
                brick = br.readLine();
                id++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day22Y2023(bricks);
    }
    
}
