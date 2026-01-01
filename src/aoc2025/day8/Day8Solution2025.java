package aoc2025.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import structure.BaseDay;
import utils.SpaceComparation;
import utils.SpacePosition;

public class Day8Solution2025 extends BaseDay{

    @Override
    protected Day8Y2025 getInputData() {
        return Day8Y2025.getDay8Data();
    }

    @Override
    public void runDaySolution() {
        Day8Y2025 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getLargestCircuitValue((Day8Y2025) input, 10));
    }

    private Integer getLargestCircuitValue(Day8Y2025 input, Integer wires) {
        List<SpaceComparation> distances = new ArrayList<>(getDistances(input));
        distances.sort(Comparator.comparing(SpaceComparation::getDistance));
        List<Set<SpacePosition>> boxes = new ArrayList<>();
        for(int i=0;i<wires;i++){
            SpaceComparation best = distances.get(i);
            SpacePosition a = best.getP1();
            SpacePosition b = best.getP2();
            int aIndex = -1;
            int bIndex = -1;
            for(int j=0;j<boxes.size();j++){
                Set<SpacePosition> box = boxes.get(j);
                if(box.contains(a)){
                    aIndex = j;
                }
                if(box.contains(b)){
                    bIndex = j;
                }
            }
            makeBoxConnections(aIndex, bIndex, boxes, a, b);
        }
        return boxes.stream()
                    .map(Set::size)
                    .sorted(Comparator.reverseOrder())
                    .limit(3)
                    .reduce(1, (a,b)->a*b);
    }

    private List<SpaceComparation> getDistances(Day8Y2025 input) {
        return IntStream.range(0, input.getJunctions().size())
                    .boxed()
                    .flatMap(index->IntStream.range(index+1, input.getJunctions().size())
                    .mapToObj(i->{
                        SpacePosition p1 = input.getJunctions().get(index);
                        SpacePosition p2 = input.getJunctions().get(i);
                        return new SpaceComparation(calculateDistance(p1, p2), p1, p2);
                    })).toList();
    }

    private Long calculateDistance(SpacePosition p1, SpacePosition p2){
        long dx = (long) p1.getX() - p2.getX();
        long dy = (long) p1.getY() - p2.getY();
        long dz = (long) p1.getZ() - p2.getZ();
        return dx*dx + dy*dy + dz*dz;
    }

    private void makeBoxConnections(int aIndex, int bIndex, List<Set<SpacePosition>> boxes, SpacePosition a, SpacePosition b){
        if(aIndex == -1 && bIndex == -1){
            Set<SpacePosition> newBox = new HashSet<>();
            newBox.add(a);
            newBox.add(b);
            boxes.add(newBox);
        }else if(aIndex == -1){
            boxes.get(bIndex).add(a);
        }else if(bIndex == -1){
            boxes.get(aIndex).add(b);
        }else if(aIndex != bIndex){
            boxes.get(bIndex).addAll(boxes.get(aIndex));
            boxes.remove(aIndex);
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("SOLUCIÓN: " + getLastUnionOfFullBox((Day8Y2025) input));
    }

    private Long getLastUnionOfFullBox(Day8Y2025 input) {
        List<SpaceComparation> distances = new ArrayList<>(getDistances(input));
        distances.sort(Comparator.comparing(SpaceComparation::getDistance));
        List<Set<SpacePosition>> boxes = new ArrayList<>();
        long lastAx = 0;
        long lastBx = 0;
        int maxBox = 0;
        int totalJuncions = input.getJunctions().size();
        int distanceIndex = 0;
        while (maxBox<totalJuncions) {
            SpaceComparation best = distances.get(distanceIndex);
            SpacePosition a = best.getP1();
            SpacePosition b = best.getP2();
            int aIndex = -1;
            int bIndex = -1;
            for(int j=0;j<boxes.size();j++){
                Set<SpacePosition> box = boxes.get(j);
                if(box.contains(a)){
                    aIndex = j;
                }
                if(box.contains(b)){
                    bIndex = j;
                }
            }
            makeBoxConnections(aIndex, bIndex, boxes, a, b);
            lastAx = a.getX();
            lastBx = b.getX();
            maxBox = boxes.stream().map(x->x.size()).max(Integer::compare).orElse(0);
            distanceIndex++;
        }
        return lastAx * lastBx;
    }


    
}
