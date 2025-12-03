package aoc2025.day3;

import java.util.stream.IntStream;

import structure.BaseDay;

public class Day3Solution2025 extends BaseDay{

    @Override
    protected Day3Y2025 getInputData() {
        return Day3Y2025.getDay3Data();
    }

    @Override
    public void runDaySolution() {
        Day3Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getTotalOutputJoltage((Day3Y2025) input));
    }

    private Long getTotalOutputJoltage(Day3Y2025 input) {
        return input.getBanks().stream().mapToLong(bank->getBankJoltage(bank)).sum();
    }

    private Integer getBankJoltage(String bank) {
        return IntStream.range(0, bank.length())
                .flatMap(index->IntStream.range(index+1, bank.length())
                                .map(num->Integer.parseInt(String.valueOf(bank.charAt(index))+String.valueOf(bank.charAt(num)))))
                .sorted()
                .max().orElse(0);
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getTotalOutputJoltage12batteries((Day3Y2025) input));
    }

    private Long getTotalOutputJoltage12batteries(Day3Y2025 input) {
        return input.getBanks().stream().mapToLong(bank->getBankJoltage12Batteries(bank)).sum();
    }

    private Long getBankJoltage12Batteries(String bank) {
        String bankJoltage = "";
        String batteries = bank.toString();
        while (bankJoltage.length()<12) {
            String maxJoltage = findMaxJoltageInBatterie(batteries,bankJoltage.length());
            bankJoltage+=maxJoltage.split("-")[1];
            batteries = batteries.substring(Integer.parseInt(maxJoltage.split("-")[0])+1);
        }
        return Long.parseLong(bankJoltage);
    }

    private String findMaxJoltageInBatterie(String batteries, Integer joltageSize){
        int emptyPlaces = 12-joltageSize;
        String finalJoltage = "";
        Integer maxJoltage = 0;
        for(int i = 0;i<=batteries.length()-emptyPlaces;i++){
            Integer joltage = Integer.parseInt(String.valueOf(batteries.charAt(i)));
            if(joltage>maxJoltage){
                maxJoltage = joltage;
                if(joltage==9){
                    return  i+"-"+joltage.toString();
                }
            }
        }
        finalJoltage = batteries.indexOf(maxJoltage.toString())+"-"+maxJoltage;
        return finalJoltage;
    }

}
