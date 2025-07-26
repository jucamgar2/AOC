package aoc2024.day13;

public class GameSolution {

    private Integer xPressed;

    private Integer yPressed;

    public GameSolution(Integer xPressed, Integer yPressed){
        this.xPressed = xPressed;
        this.yPressed = yPressed;
    }

    public Integer getGameSolutionCost(){
        return this.xPressed*3 + this.yPressed;
    }
    
}
