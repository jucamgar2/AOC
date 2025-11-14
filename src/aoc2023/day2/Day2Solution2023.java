package aoc2023.day2;

import structure.BaseDay;
import utils.CubeGame;

public class Day2Solution2023 extends BaseDay{

    @Override
    protected Day2Y2023 getInputData() {
        return Day2Y2023.getDay2Data();
    }

    @Override
    public void runDaySolution() {
        Day2Y2023 input = Day2Y2023.getDay2Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + getSumOfPossibleGames((Day2Y2023)input));
    }

    private Integer getSumOfPossibleGames(Day2Y2023 input) {
        return input.getGames().stream().filter(game-> isGamePossible(game)).mapToInt(game->game.getId()).sum();
    }

    private Boolean isGamePossible(CubeGame game){
        return game.getMaxReds()<=12 && game.getMaxGreens()<=13 && game.getMaxBlues()<=14;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getSumOfPower((Day2Y2023)input));
    }

    private Long getSumOfPower(Day2Y2023 input) {
        return input.getGames().stream().mapToLong(game->getGamePower(game)).sum();
    }

    private Long getGamePower(CubeGame game){
        return 1L* game.getMaxBlues()*game.getMaxGreens()*game.getMaxReds();
    }

}
