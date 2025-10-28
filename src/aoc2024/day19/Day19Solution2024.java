package aoc2024.day19;

import structure.BaseDay;

public class Day19Solution2024 extends BaseDay{

    @Override
    protected Day19Y2024 getInputData(){
        return Day19Y2024.getDay19Data();
    }

    @Override
    public void runDaySolution(){
        Day19Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + getNumberOfPossiblePatterns((Day19Y2024)input));
    }

    private static Long getNumberOfPossiblePatterns(Day19Y2024 input){
        return input.getExpectedDesigns().stream()
                .filter(design->isDesignPossible(input, design))
                .count();
    }

    private static Boolean isDesignPossible(Day19Y2024 input, String design){
        int n = design.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int i = 1; i<=n;i++){
            for(int j =0; j<i; j++){
                String sub = design.substring(j, i);
                if(dp[j] && input.getTowelPatterns().contains(sub)){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getTotalNumberOfDifferentWays((Day19Y2024)input));
    }

    private static Long getTotalNumberOfDifferentWays(Day19Y2024 input) {
        return input.getExpectedDesigns().stream()
                .filter(design->isDesignPossible(input, design))
                .mapToLong(design->getWaysToReachDesign(input,design))
                .sum();
    }

    private static Long getWaysToReachDesign(Day19Y2024 input, String design) {
        int n = design.length();
        long[] dp = new long[n+1];
        dp[0] = 1;

        for(int i = 1; i<=n;i++){
            for(int j =0; j<i; j++){
                String sub = design.substring(j, i);
                if(input.getTowelPatterns().contains(sub)){
                    dp[i] += dp[j];
                }
            }
        }
        return dp[n];
    }

    

}
