package aoc2023.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day15Solution2023 extends BaseDay{

    @Override
    protected Day15Y2023 getInputData() {
        return Day15Y2023.readDay15Data();
    }

    @Override
    public void runDaySolution() {
        Day15Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getSumOfEncrypt((Day15Y2023)input));
    }

    private Long getSumOfEncrypt(Day15Y2023 input) {
        return input.getMessages().stream().mapToLong(message->getEncrypt(message)).sum();
    }

    private int getEncrypt(String message) {
        int res = 0;
        for(int i = 0;i<message.length();i++){
            char c = message.charAt(i);
            res += c;
            res = (res*17)%256;
        }
        return res;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solucion: " + getFocusingPower((Day15Y2023) input));
    }

    private Long getFocusingPower(Day15Y2023 input) {
        Map<Integer, List<String>> boxes = getInitialBoxes();
        for(String lens: input.getMessages()){
            String label = lens.split("-|=")[0];
            int hash = getEncrypt(label);
            List<String> box = boxes.get(hash);
            Integer indexOfLabelIfExists = IntStream.range(0, box.size())
                                                .filter(index->box.get(index).split("=")[0].equals(label))
                                                .findFirst().orElse(9999);
            if(lens.contains("=")){
                if(indexOfLabelIfExists==9999){
                    box.add(lens);
                }else{
                    box.set(indexOfLabelIfExists, lens);
                }
            }else{
                if(indexOfLabelIfExists!=9999){
                    box.remove((int) indexOfLabelIfExists);
                }
            }
        }
        return calculateFocusingPower(boxes);
    }

    private Long calculateFocusingPower(Map<Integer , List<String>> boxes) {
        Long focusingPower = 0l;
        for(Integer boxKey: boxes.keySet()){
            for(int i = 0; i<boxes.get(boxKey).size(); i++){
                focusingPower += (boxKey+1)*(i+1)*(Integer.parseInt(boxes.get(boxKey).get(i).split("=")[1]));
            }
        }
        return focusingPower;
    }

    private Map<Integer, List<String>> getInitialBoxes() {
        Map<Integer, List<String>> boxes = new HashMap<>();
        for(int i = 0; i<256; i++){
            boxes.put(i, new ArrayList<>());
        }
        return boxes;
    }



}
