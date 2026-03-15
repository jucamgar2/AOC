package aoc2023.day7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import structure.BaseDay;

public class Day7Solution2023 extends BaseDay{

    Map<Character, Integer> order = Map.ofEntries(
        Map.entry('A', 12),
        Map.entry('K', 11),
        Map.entry('Q', 10),
        Map.entry('J', 9),
        Map.entry('T', 8),
        Map.entry('9', 7),
        Map.entry('8', 6),
        Map.entry('7', 5),
        Map.entry('6', 4),
        Map.entry('5', 3),
        Map.entry('4', 2),
        Map.entry('3', 1),
        Map.entry('2', 0)
    );

    Comparator<String> handComparator = (a, b) ->
        IntStream.range(0, a.length())
            .map(i -> Integer.compare(
                order.get(a.charAt(i)),
                order.get(b.charAt(i))
            ))
            .filter(c -> c != 0)
            .findFirst()
            .orElse(0);

    Map<Character, Integer> orderForJoker = Map.ofEntries(
        Map.entry('A', 12),
        Map.entry('K', 11),
        Map.entry('Q', 10),
        Map.entry('T', 9),
        Map.entry('9', 8),
        Map.entry('8', 7),
        Map.entry('7', 6),
        Map.entry('6', 5),
        Map.entry('5', 4),
        Map.entry('4', 3),
        Map.entry('3', 2),
        Map.entry('2', 1),
        Map.entry('J', 0)
    );

    Comparator<String> handComparatorforJoker = (a, b) ->
        IntStream.range(0, a.length())
            .map(i -> Integer.compare(
                orderForJoker.get(a.charAt(i)),
                orderForJoker.get(b.charAt(i))
            ))
            .filter(c -> c != 0)
            .findFirst()
            .orElse(0);

    @Override
    protected Day7Y2023 getInputData() {
        return Day7Y2023.readDay7Data();
    }

    @Override
    public void runDaySolution() {
        Day7Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getTotalWinnings((Day7Y2023) input));
    }

    private Long getTotalWinnings(Day7Y2023 input) {
        List<String> orderedHands = orderHands(input.getHands());
        return IntStream.range(0, input.getHands().size())
                    .mapToLong(i->(orderedHands.indexOf(input.getHands().get(i))+1)*input.getBids().get(i))
                    .sum();
    }

    private List<String> orderHands(List<String> hands) {
        List<String> orderedHands = new ArrayList<>();
        List<String> fiveOfKind = new ArrayList<>();
        List<String> fourOfKind = new ArrayList<>();
        List<String> fullHouse = new ArrayList<>();
        List<String> threeOfKind = new ArrayList<>();
        List<String> twoPair = new ArrayList<>();
        List<String> onePair = new ArrayList<>();
        List<String> highCard = new ArrayList<>();   
        hands.stream().forEach(hand->{
            Map<Character, Integer> count = countCharacters(hand);
            Integer max = count.values().stream().max(Integer::compareTo).orElse(0);
            Integer secondMax = count.values().stream().filter(x->!x.equals(max)).max(Integer::compareTo).orElse(0);
            switch (max) {
                case 5:
                    fiveOfKind.add(hand);
                    break;
                case 4:
                    fourOfKind.add(hand);
                    break;
                case 3:
                    if (secondMax==2) {
                        fullHouse.add(hand);
                    }else{
                        threeOfKind.add(hand);
                    }
                    break;
                case 2:
                    long numOf2 = count.values().stream().filter(x->x==2).count();
                    if (numOf2==2) {
                        twoPair.add(hand);
                    }else{
                        onePair.add(hand);
                    }       
                    break;
                default:
                    highCard.add(hand);
                    break;
            }
        });
        highCard.sort(handComparator);
        onePair.sort(handComparator);
        twoPair.sort(handComparator);
        threeOfKind.sort(handComparator);
        fullHouse.sort(handComparator);
        fourOfKind.sort(handComparator);
        fiveOfKind.sort(handComparator);
        orderedHands.addAll(highCard);
        orderedHands.addAll(onePair);
        orderedHands.addAll(twoPair);
        orderedHands.addAll(threeOfKind);
        orderedHands.addAll(fullHouse);
        orderedHands.addAll(fourOfKind);
        orderedHands.addAll(fiveOfKind);
        return orderedHands;
    }

    public static Map<Character, Integer> countCharacters(String text) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getTotalWinningsWithJoker((Day7Y2023) input));
    }

    private Long getTotalWinningsWithJoker(Day7Y2023 input) {
        List<String> orderedHands = orderHandsWithJoker(input.getHands());
        return IntStream.range(0, input.getHands().size())
                    .mapToLong(i->(orderedHands.indexOf(input.getHands().get(i))+1)*input.getBids().get(i))
                    .sum();
    }

    private List<String> orderHandsWithJoker(List<String> hands) {
        List<String> orderedHands = new ArrayList<>();
        List<String> fiveOfKind = new ArrayList<>();
        List<String> fourOfKind = new ArrayList<>();
        List<String> fullHouse = new ArrayList<>();
        List<String> threeOfKind = new ArrayList<>();
        List<String> twoPair = new ArrayList<>();
        List<String> onePair = new ArrayList<>();
        List<String> highCard = new ArrayList<>();   
        hands.stream().forEach(hand->{
            Map<Character, Integer> count = countCharacters(hand);
            int jokers = count.getOrDefault('J', 0);
            count.remove('J');
            List<Integer> values = new ArrayList<>(count.values());
            values.sort(Comparator.reverseOrder());
            int max = values.isEmpty() ? 0 : values.get(0);
            int secondMax = values.size() > 1 ? values.get(1) : 0;
            if (jokers > 0) {
                max += jokers;
            }
            switch (max) {
                case 5:
                    fiveOfKind.add(hand);
                    break;
                case 4:
                    fourOfKind.add(hand);
                    break;
                case 3:
                    if (secondMax==2) {
                        fullHouse.add(hand);
                    }else{
                        threeOfKind.add(hand);
                    }
                    break;
                case 2:
                    long numOf2 = count.values().stream().filter(x->x==2).count();
                    if (numOf2==2) {
                        twoPair.add(hand);
                    }else{
                        onePair.add(hand);
                    }       
                    break;
                default:
                    highCard.add(hand);
                    break;
            }
        });
        highCard.sort(handComparatorforJoker);
        onePair.sort(handComparatorforJoker);
        twoPair.sort(handComparatorforJoker);
        threeOfKind.sort(handComparatorforJoker);
        fullHouse.sort(handComparatorforJoker);
        fourOfKind.sort(handComparatorforJoker);
        fiveOfKind.sort(handComparatorforJoker);
        orderedHands.addAll(highCard);
        orderedHands.addAll(onePair);
        orderedHands.addAll(twoPair);
        orderedHands.addAll(threeOfKind);
        orderedHands.addAll(fullHouse);
        orderedHands.addAll(fourOfKind);
        orderedHands.addAll(fiveOfKind);
        return orderedHands;
    }

}
