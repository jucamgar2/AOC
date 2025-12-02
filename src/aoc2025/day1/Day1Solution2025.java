package aoc2025.day1;

import structure.BaseDay;

public class Day1Solution2025 extends BaseDay {

    @Override
    protected Day1Y2025 getInputData() {
        return Day1Y2025.getDay1Data();
    }

    @Override
    public void runDaySolution() {
        Day1Y2025 input = Day1Y2025.getDay1Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        input = Day1Y2025.getDay1Data();
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCION: " + getActualPassword((Day1Y2025) input));
    }

    private int getActualPassword(Day1Y2025 input) {
        int password = 0;
        for (int instruction : input.getInstructions()) {
            int newState = (input.getActualState() + instruction) % 100;
            if (newState < 0) {
                newState += 100;
            }
            input.setActualState(newState);
            if (newState == 0) {
                password++;
            }
        }
        return password;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCION: " + getActualPasswordMethod2((Day1Y2025) input));
    }

    private Integer getActualPasswordMethod2(Day1Y2025 input) {
        int password = 0;
        for (int instruction : input.getInstructions()) {
            int start = input.getActualState();
            password += hitsThroughZero(start, instruction);
            int newState = (start + instruction) % 100;
            if (newState < 0) newState += 100;
            input.setActualState(newState);
        }

        return password;
    }

    private int hitsThroughZero(int currentPosition, int instruction) {
        int s = ((currentPosition % 100) + 100) % 100;
        int distance = Math.abs(instruction);
        int firstZeroHit;
        if (instruction > 0) { 
            firstZeroHit = (100 - s) % 100;
        } else {
            firstZeroHit = s % 100;
        }
        if (firstZeroHit == 0){
            firstZeroHit = 100;
        } 
        if (distance < firstZeroHit){
            return 0;
        }
        return 1 + (distance - firstZeroHit) / 100;
    }

}
