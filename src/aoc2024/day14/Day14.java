package aoc2024.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day14 {

    private List<Robot> robots;

    private Integer maxI;

    private Integer maxJ;

    public List<Robot> getRobots() {
        return robots;
    }

    public Integer getMaxI() {
        return maxI;
    }

    public Integer getMaxJ() {
        return maxJ;
    }

    public Day14(List<Robot> robots, int maxI, int maxJ){
        this.robots = robots;
        this.maxI = maxI;
        this.maxJ = maxJ;
    }

    public static Day14 getDay14Data(){
        List<Robot> robots = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay14.txt"))) {
            br.lines().forEach(line->{
                String[] components = line.split(" ");
                String[] positions = components[0].replaceAll("p\\=", "").split(",");
                int i = Integer.parseInt(positions[0]);
                int j = Integer.parseInt(positions[1]);
                String[] speeds = components[1].replaceAll("v\\=", "").split(",");
                int di = Integer.parseInt(speeds[0]);
                int dj = Integer.parseInt(speeds[1]);
                Robot robot = new Robot(i, j, di, dj);
                robots.add(robot);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day14(robots, 101, 103);
    }
    
}
