package aoc2025.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import structure.BaseDay;
import utils.LightIndicator;

public class Day10Solution2025 extends BaseDay{

    @Override
    protected Day10Y2025 getInputData() {
        return Day10Y2025.getDay10Data();
    }

    @Override
    public void runDaySolution() {
        Day10Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getFewestButtonPresses((Day10Y2025) input));
    }

    private Integer getFewestButtonPresses(Day10Y2025 input) {
        return input.getLightIndicators().stream().mapToInt(indicator->getFewestPressesByIndicator(indicator)).sum();
    }

    private Integer getFewestPressesByIndicator(LightIndicator indicator) {
        List<Integer> presses = new ArrayList<>();
        int stateSize = indicator.getLightIndicator().length();
        int size = (int) Math.pow(2,indicator.getButtons().size());
        for(int i = 0; i<size;i++){
            String state = String.format("%" + indicator.getButtons().size() + "s",
            Integer.toBinaryString(i))
            .replace(' ', '0');
            String indicatorState = getIndicatorState(state,indicator.getButtons(), stateSize);
            if(indicatorState.equals(indicator.getLightIndicator())){
                int count = 0;
                for (int j = 0; j < state.length(); j++) {
                    if (state.charAt(j) == '1') {
                        count++;
                    }
                }
                presses.add(count);
            }
        }
        return presses.stream().min(Integer::compare).orElse(0);
    }

    private String getIndicatorState(String state, List<List<Integer>> buttons, int size){
        String inidicatorState = ".".repeat(size);
        for (int index = state.length()-1; index >=0; index--) {
            if (state.charAt(index) == '1') {
                inidicatorState = pressButton(buttons.get(index), inidicatorState);
            }
        }
        return inidicatorState;
    }

    private String pressButton(List<Integer> list, String inidicatorState) {
        StringBuilder sb = new StringBuilder(inidicatorState);
        for (int pos : list) {
            if (pos >= 0 && pos < sb.length()) {
                char c = sb.charAt(pos);
                sb.setCharAt(pos, c == '.' ? '#' : '.');
            }
        }
        return sb.toString();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getFewestPressesToConfigJoltage((Day10Y2025)input));
    }

    private Integer getFewestPressesToConfigJoltage(Day10Y2025 input) {
        return input.getLightIndicators().stream().mapToInt(indicator->getPressesToConfigJoltage(indicator)).sum();
    }

    private Integer getPressesToConfigJoltage(LightIndicator indicator) {
        List<Integer> expectedState = indicator.getJoltage();
        List<Integer> initialState = new ArrayList<>();
        expectedState.forEach(x->initialState.add(0));
        Queue<List<Integer>> queue = new ArrayDeque<>();
        Set<List<Integer>> visited = new HashSet<>();
        queue.add(initialState);
        visited.add(initialState);
        int presses = 0;
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for(int i = 0; i<levelSize; i++){
                List<Integer> current = queue.poll();
                if(current.equals(expectedState)){
                    return presses;
                }
                for(List<Integer> press : indicator.getButtons()){
                    List<Integer> next = generateNewState(current, press, expectedState);
                    if(next!=null && !visited.contains(next)){
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
            presses++;
        }
        return presses;
    }

    private List<Integer> generateNewState(List<Integer> actualState, List<Integer> press,List<Integer> expectedState){
        List<Integer> newState = new ArrayList<>(actualState);
        for (int joltIndex : press) {
            newState.set(joltIndex, newState.get(joltIndex) + 1);
        }
        for (int i = 0; i < newState.size(); i++) {
            if (newState.get(i) > expectedState.get(i)) {
                return null;
            }
        }

        return newState;
    }
}
