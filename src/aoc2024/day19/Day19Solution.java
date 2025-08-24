package aoc2024.day19;


public class Day19Solution {

    public static void day19Solution(){
        Day19 input = Day19.getDay19Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day19 input) {
        System.out.println("SOLUCION: " + getNumberOfPossiblePatterns(input));
    }

    private static Long getNumberOfPossiblePatterns(Day19 input){
        return input.getExpectedDesigns().stream()
                .filter(design->isDesignPossible(input, design))
                .count();
    }

    private static Boolean isDesignPossible(Day19 input, String design){
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

    private static void part2Solution(Day19 input) {
        System.out.println("SOLUCION: " + getTotalNumberOfDifferentWays(input));
    }

    private static Long getTotalNumberOfDifferentWays(Day19 input) {
        return input.getExpectedDesigns().stream()
                .filter(design->isDesignPossible(input, design))
                .mapToLong(design->getWaysToReachDesign(input,design))
                .sum();
    }

    private static Long getWaysToReachDesign(Day19 input, String design) {
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
