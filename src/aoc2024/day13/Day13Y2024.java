package aoc2024.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Game;

public class Day13Y2024 {
 
    private List<Game> games;

    public Day13Y2024(List<Game> games){
        this.games = games;
    }

    public List<Game> getGames(){
        return this.games;
    }

    public static Day13Y2024 getDay13Data(){
        List<Game> games = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay13.txt"))) {
            List<String> lines = br.lines().toList();
            for(int i=0;i<lines.size();i+=4){
                String[] buttonA = lines.get(i).replaceAll("Button A: ", "")
                .replaceAll("X", "")
                .replaceAll("Y", "")
                .replaceAll(",","")
                .replaceAll("\\+", "")
                .split(" ");
                Integer aMoveX = Integer.parseInt(buttonA[0]);
                Integer aMoveY = Integer.parseInt(buttonA[1]);
                String[] buttonB = lines.get(i+1).replaceAll("Button B: ", "")
                .replaceAll("X", "")
                .replaceAll("Y", "")
                .replaceAll(",","")
                .replaceAll("\\+", "")
                .split(" ");
                Integer bMoveX = Integer.parseInt(buttonB[0]);
                Integer bMoveY = Integer.parseInt(buttonB[1]);
                String[] prize = lines.get(i+2).replaceAll("Prize: ", "")
                .replaceAll("X", "")
                .replaceAll("Y", "")
                .replaceAll("\\=", "")
                .replaceAll(",", "")
                .split(" ");
                Integer xObj = Integer.parseInt(prize[0]);
                Integer yObj = Integer.parseInt(prize[1]);
                Game game = new Game(aMoveX, aMoveY, bMoveX, bMoveY, xObj, yObj);
                games.add(game);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Day13Y2024(games);
    }

}
