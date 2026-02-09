package aoc2025.day11;

import structure.BaseDay;
import utils.Node;

public class Day11Solution2025 extends BaseDay{

    @Override
    protected Day11Y2025 getInputData() {
        return Day11Y2025.getDay11Data();
    }

    @Override
    public void runDaySolution() {
        Day11Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        //part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getDifferentPathsFromYouToOut((Day11Y2025)input));
    }

    private Integer getDifferentPathsFromYouToOut(Day11Y2025 input) {
        Node from = input.getGraph().getNode("you");
        Node to = input.getGraph().getNode("out");
        return input.getGraph().findAllPathsFromTo(from, to).size();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: "+ getSpecificPaths((Day11Y2025) input));
    }

    private Long getSpecificPaths(Day11Y2025 input) {
        Long fromDac = (long)input.getGraph().countPaths("svr", "dac");
        Long dacFft = (long) input.getGraph().countPaths("dac", "fft");
        Long fftTo = (long) input.getGraph().countPaths("fft", "out");
        Long fromFft = (long) input.getGraph().countPaths("svr", "fft");
        Long fftDac = (long) input.getGraph().countPaths("fft", "dac");
        Long dacTo = (long) input.getGraph().countPaths("dac", "out");
        Long path1 = fromDac*dacFft*fftTo;
        Long path2 = fromFft*fftDac*dacTo;
        return path1 +path2;
    }

   
    
}
