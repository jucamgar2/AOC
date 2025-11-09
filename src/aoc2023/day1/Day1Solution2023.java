package aoc2023.day1;

import structure.BaseDay;

public class Day1Solution2023 extends BaseDay{

    @Override
    protected Day1Y2023 getInputData() {
        return Day1Y2023.getDay1Data();
    }
    
    @Override
    public void runDaySolution() {
        Day1Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: "+getSumOfCalibrationValues((Day1Y2023)input));
    }

    private Integer getSumOfCalibrationValues(Day1Y2023 input) {
        return input.getCalibrationDocument().stream()
                .mapToInt(calibration->getCalibrationValue(calibration))
                .sum();
    }

    private Integer getCalibrationValue(String calibration) {
        String num1 = "";
        String num2 = "";
        for(int i = 0;i<calibration.length();i++){
            char aux1 = calibration.charAt(i);
            char aux2 = calibration.charAt(calibration.length()-1-i);
            if(Character.isDigit(aux1)&&num1.isBlank()){
                num1 = String.valueOf(aux1);
            }
            if(Character.isDigit(aux2)&&num2.isBlank()){
                num2 = String.valueOf(aux2);
            }
        }
        Integer calibrated = parseCalibrationValue(num1, num2);  
        return calibrated;
    }

    private Integer parseCalibrationValue(String num1, String num2){
        if(num2.isBlank()){
            return Integer.parseInt(num1+num1);
        }else if(num1.isBlank()){
            return Integer.parseInt(num2+num2);
        }else{
            return Integer.parseInt(num1+num2);
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getCalibrationValueByWords((Day1Y2023) input));
    }

    private int getCalibrationValueByWords(Day1Y2023 input) {
        return input.getCalibrationDocument().stream()
                .map(calibration->parseNumbers(calibration))
                .mapToInt(calibration->getCalibrationValue(calibration))
                .sum();
    }

    private String parseNumbers(String calibration) {
        calibration = calibration.toLowerCase();
        calibration = calibration.replaceAll("one", "one1one");
        calibration = calibration.replaceAll("two", "two2two");
        calibration = calibration.replaceAll("three", "three3three");
        calibration = calibration.replaceAll("four", "four4four");
        calibration = calibration.replaceAll("five", "five5five");
        calibration = calibration.replaceAll("six", "six6six");
        calibration = calibration.replaceAll("seven", "seven7seven");
        calibration = calibration.replaceAll("eight", "eight8eight");
        calibration = calibration.replaceAll("nine", "nine9nine");
        return calibration;
    }
    
}
