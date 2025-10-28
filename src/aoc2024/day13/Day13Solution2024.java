package aoc2024.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import structure.BaseDay;
import utils.Game;
import utils.GameSolution;

public class Day13Solution2024 extends BaseDay{

    @Override
    protected Day13Y2024 getInputData(){
        return Day13Y2024.getDay13Data();
    }
    
    @Override
    public void runDaySolution(){
        Day13Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + getMinimunCostForMaximunWin((Day13Y2024)input));
    }

    public static Integer getMinimunCostForMaximunWin(Day13Y2024 input){
        return input.getGames().stream().mapToInt(game->getGameMinimunCostToWin(game)).sum();
    }

    public static Integer getGameMinimunCostToWin(Game game){
        List<GameSolution> solutins = new ArrayList<>();
        IntStream.range(1, 100).forEach(i->IntStream.range(1,100)
        .forEach(j->{
            if(isGameSolution(game, i, j)){
                GameSolution solution = new GameSolution(i, j);
                solutins.add(solution);
            }
        }));
        return solutins.stream().mapToInt(gameSolution-> gameSolution.getGameSolutionCost()).min().orElse(0);
    }

    public static boolean isGameSolution(Game game, Integer i, Integer j){
        boolean isXslution = (game.getaMoveX()*i + game.getbMoveX()*j) == game.getxObj();
        if(isXslution){
            boolean isYsolution = (game.getaMoveY()*i + game.getbMoveY()*j) == game.getyObj();
            if(isYsolution){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getMinimunCostForMaximunWinBigObj((Day13Y2024)input));
    }

    public static Long getMinimunCostForMaximunWinBigObj(Day13Y2024 input){
        return input.getGames().stream().mapToLong(game->getGameMinimunCostToWinBigObj(game)).sum();
    }

    public static Long getGameMinimunCostToWinBigObj(Game game){
        Long a = game.getaMoveX().longValue();
        Long b = game.getbMoveX().longValue();
        Long c = game.getaMoveY().longValue();
        Long d = game.getbMoveY().longValue();
        Long x = game.getxObj() + 10000000000000L;
        Long y = game.getyObj().longValue() + 10000000000000L;
        long det = a*d - c*b;
        long rhs = y*a - c*x;
        if(det == 0 ){
            return 0l;
        }
        long j = rhs / det;
        if (rhs % det != 0) {
            return 0l;
        }
        long numI = x - b * j;
        if (numI % a != 0) {
            return 0l;
        }
        long i = numI / a;
        if(i<0 && j <0){
            i = Math.abs(i);
            j = Math.abs(j);
        }else if(i<0 || j<0){
            return 0l;
        }
        return 3 * i + j;
    }

}
