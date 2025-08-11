package aoc2024.day16;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import aoc2024.day6.Position;

public class Day16Solution {

    public static void day16Solution(){
        Day16 input = Day16.getDay16Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day16 input){
        System.out.println("SOLUCION: " + getLowestScoreReindeer(input));
    }

    private static int getLowestScoreReindeer(Day16 input) {
        return dijkstraWithDirection(input, input.getSPosition(), input.getEPosition());
    }

    private static int dijkstraWithDirection(Day16 input, Position start, Position end) {
        record State(Position pos, String dir) {}

        Map<State, Integer> dist = new HashMap<>();
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        State startState = new State(start, "R");
        dist.put(startState, 0);
        pq.add(startState);

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int currentCost = dist.get(current);

            if (current.pos.equals(end)) {
                return currentCost; 
            }

            for (Position next : getNeighbors(input, current.pos)) {
                String newDir = getDirection(current.pos, next);
                int costAdd;

                if (newDir.equals(current.dir)) {
                    costAdd = 1; 
                } else if (areDirectionOpposites(newDir, current.dir)) {
                    costAdd = 2001; 
                } else {
                    costAdd = 1001;
                }

                State nextState = new State(next, newDir);
                int newCost = currentCost + costAdd;

                if (newCost < dist.getOrDefault(nextState, Integer.MAX_VALUE)) {
                    dist.put(nextState, newCost);
                    pq.add(nextState);
                }
            }
        }
        return -1; 
    }

    private static List<Position> getNeighbors(Day16 input, Position current) {
        List<Position> neighbors = new ArrayList<>();
        int i = current.getI();
        int j = current.getJ();
        Position up = input.getPosition(i - 1, j);
        Position down = input.getPosition(i + 1, j);
        Position left = input.getPosition(i, j - 1);
        Position right = input.getPosition(i, j + 1);

        neighbors.add(left);
        neighbors.add(up);
        neighbors.add(down);
        neighbors.add(right);

        return neighbors.stream().filter(x -> x != null && !x.getValue().equals("#")).toList();
    }

    private static String getDirection(Position current, Position next) {
        int di = current.getI() - next.getI();
        int dj = current.getJ() - next.getJ();
        if (di == -1) return "D";
        if (di == 1) return "U";
        if (dj == -1) return "R";
        return "L";
    }

    private static boolean areDirectionOpposites(String d1, String d2) {
        return (d1.equals("R") && d2.equals("L")) ||
               (d1.equals("L") && d2.equals("R")) ||
               (d1.equals("U") && d2.equals("D")) ||
               (d1.equals("D") && d2.equals("U"));
    }

    private static void part2Solution(Day16 input){
        System.out.println("SOLUCION: " + getNumOfGoodTilesOptimized(input) );
    }

    private static int getNumOfGoodTilesOptimized(Day16 input) {
        record State(Position pos, String dir) {}

        Map<State, Integer> dist = new HashMap<>();
        Map<State, List<State>> parents = new HashMap<>();
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        State startState = new State(input.getSPosition(), "R");
        dist.put(startState, 0);
        pq.add(startState);

        int bestCost = Integer.MAX_VALUE;
        List<State> endStates = new ArrayList<>();

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int currentCost = dist.get(current);

            if (current.pos.equals(input.getEPosition())) {
                if (currentCost < bestCost) {
                    bestCost = currentCost;
                    endStates.clear();
                    endStates.add(current);
                } else if (currentCost == bestCost) {
                    endStates.add(current);
                }
                continue;
            }

            for (Position next : getNeighbors(input, current.pos)) {
                String newDir = getDirection(current.pos, next);
                int costAdd;
                if (newDir.equals(current.dir)) {
                    costAdd = 1;
                } else if (areDirectionOpposites(newDir, current.dir)) {
                    costAdd = 2001;
                } else {
                    costAdd = 1001;
                }

                State nextState = new State(next, newDir);
                int newCost = currentCost + costAdd;

                int prevCost = dist.getOrDefault(nextState, Integer.MAX_VALUE);
                if (newCost < prevCost) {
                    dist.put(nextState, newCost);
                    pq.add(nextState);
                    parents.put(nextState, new ArrayList<>(List.of(current)));
                } else if (newCost == prevCost) {
                    parents.get(nextState).add(current);
                }
            }
        }

        Set<Position> goodTiles = new HashSet<>();
        Deque<State> stack = new ArrayDeque<>(endStates);

        while (!stack.isEmpty()) {
            State state = stack.pop();
            goodTiles.add(state.pos);
            for (State parent : parents.getOrDefault(state, Collections.emptyList())) {
                stack.push(parent);
            }
        }

        return goodTiles.size();
    }
    
}
