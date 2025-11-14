package aoc2023.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.CubeGame;

public class Day2Y2023 {

    private List<CubeGame> games;

    public List<CubeGame> getGames(){
        return this.games;
    }

    public Day2Y2023(List<CubeGame> games){
        this.games = games;
    }

    public static Day2Y2023 getDay2Data(){
        List<CubeGame> games = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day2Data.txt"))) {
            br.lines().forEach(line->{
                String[] game = line.split(":");
                int id = Integer.parseInt(game[0].replace("Game", "").trim());
                int maxGreen = 0;
                int maxBlue = 0;
                Integer maxRed = 0;
                for(String rep: game[1].split(";")){
                    for(String color: rep.split(",")){
                        if(color.contains("blue")){
                            int blue = Integer.parseInt(color.replace("blue", "").trim());
                            if(blue>maxBlue){
                                maxBlue = blue;
                            }
                        }else if(color.contains("red")){
                            int red = Integer.parseInt(color.replace("red", "").trim());
                            if(red>maxRed){
                                maxRed = red;
                            }
                        }else{
                            int green = Integer.parseInt(color.replace("green", "").trim());
                            if(green>maxGreen){
                                maxGreen = green;
                            }
                        }
                    }
                }
                CubeGame cubeGame = new CubeGame(id, maxGreen, maxBlue, maxRed);
                games.add(cubeGame);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day2Y2023(games);
    }
    
}
