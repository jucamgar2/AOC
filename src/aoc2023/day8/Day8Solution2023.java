package aoc2023.day8;

import java.util.List;
import java.util.function.Supplier;

import structure.BaseDay;
import utils.Edge;
import utils.Node;

public class Day8Solution2023 extends BaseDay{

    @Override
    protected Day8Y2023 getInputData() {
        return Day8Y2023.readDay8Data();
    }

    @Override
    public void runDaySolution() {
        Day8Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getStepsToReachZZZ((Day8Y2023)input, "AAA", "ZZZ"));
    }

    private Integer getStepsToReachZZZ(Day8Y2023 input, String node, String endNode) {
        int counter = 0;
        if(endNode.equals("ZZZ")){
            while(!node.equals("ZZZ")){
                node = performInstructions(input, node);
                counter++;    
            }
        }else{
            while(node.charAt(node.length()-1)!='Z'){
                node = performInstructions(input, node);
                counter++;
            }
        }
       
        return counter*input.getInstructions().length();
    }

    private String performInstructions(Day8Y2023 input, String nodeLabel){
        Node node = input.getNetwork().getNode(nodeLabel);
        for(int i = 0;i<input.getInstructions().length();i++){
            String instruction = String.valueOf(input.getInstructions().charAt(i));
            Node destination = node.getEdges().stream()
                                        .filter(n->n.getLabel().equals(instruction))
                                        .findAny().orElse(new Edge(node, node, nodeLabel))
                                        .getTo();
            node = destination;
        }
        return node.getValue();
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getStepsToReachZZZFromAllStarts((Day8Y2023) input));
    }

    private Long getStepsToReachZZZFromAllStarts(Day8Y2023 input) {
        List<String> startNodes = input.getNetwork().getNodeLabels().stream()
                                    .filter(node->node.charAt(node.length()-1)=='A')
                                    .toList();
        List<Long> steps = startNodes.stream().map(label->Long.valueOf(getStepsToReachZZZ(input, label, ""))).toList();
        return lcmOfList(steps);
    }

    private long gcd(long a, long b) {
    while (b != 0) {
        long temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}
private long lcm(long a, long b) {
    return (a / gcd(a, b)) * b;
}

private long lcmOfList(List<Long> numbers) {
    long result = numbers.get(0);

    for (int i = 1; i < numbers.size(); i++) {
        result = lcm(result, numbers.get(i));
    }

    return result;
}
    
}
