package aoc2020.day2;

import structure.BaseDay;
import utils.PasswordCondition;

public class Day2Solution2020 extends BaseDay {

    @Override
    protected Day2Y2020 getInputData() {
        return Day2Y2020.getDay2Data();
    }

    @Override
    public void runDaySolution() {
        Day2Y2020 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: "+ getNumOfValidPasswordsByCount((Day2Y2020) input));    
    }

    private Long getNumOfValidPasswordsByCount(Day2Y2020 input) {
        return input.getListOfPasswords().stream()
            .filter(password->isValidByCount(password))
            .count();
    }

    private Boolean isValidByCount(PasswordCondition password) {
        Long charCount = password.getPassword().chars().mapToObj(c->(char) c).filter(c->c==password.getCharacter()).count();
        return charCount >= password.getLimit1() && charCount <= password.getLimit2();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: "+ getNumOfValidPasswordsByPosition((Day2Y2020) input));
    }

    private Long getNumOfValidPasswordsByPosition(Day2Y2020 input) {
        return input.getListOfPasswords().stream()
            .filter(password->isValidByPosition(password))
            .count();
    }

    private Boolean isValidByPosition(PasswordCondition password) {
        Boolean isLimit1Correct = password.getPassword().charAt(password.getLimit1()-1) == password.getCharacter();
        Boolean isLimit2Correct = password.getPassword().charAt(password.getLimit2()-1) == password.getCharacter();
        return isLimit1Correct^isLimit2Correct;
    }


    
}
