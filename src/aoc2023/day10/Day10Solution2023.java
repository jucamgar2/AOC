package aoc2023.day10;

import java.util.ArrayList;
import java.util.List;

import structure.BaseDay;
import utils.Edge;
import utils.Graph;
import utils.Node;
import utils.Position;
import utils.PositionBoard;

public class Day10Solution2023 extends BaseDay{

    @Override
    protected Day10Y2023 getInputData() {
        return Day10Y2023.readDay10Data();
    }

    @Override
    public void runDaySolution() {
        Day10Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getSteptsToFarthestPoint((Day10Y2023) input));
    }

    private Integer getSteptsToFarthestPoint(Day10Y2023 input) {
        Graph pipes = input.getPipes();
        String startLabel = input.getPipes().getNodeLabels().stream()
                .filter(label->label.startsWith("S"))
                .findFirst().orElse("");
        Node startNode = pipes.getNode(startLabel);
        List<Node> loop = getLoop(startNode);
        return loop.size()/2;
    }

    private List<Node> getLoop(Node start) {
        List<Node> loop = new ArrayList<>();
        List<Node> neighbors = start.getEdges()
                                    .stream()
                                    .map(Edge::getTo)
                                    .toList();
        if (neighbors.isEmpty()) return loop;
        Node prev = start;
        Node current = neighbors.get(0);
        loop.add(start);
        while (!current.equals(start)) {
            loop.add(current);
            Node next = null;
            for (Edge edge : current.getEdges()) {
                Node candidate = edge.getTo();
                if (!candidate.equals(prev)) {
                    next = candidate;
                    break;
                }
            }
            prev = current;
            current = next;
        }
        return loop;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getTilesEnclosed((Day10Y2023)input));
    }

    private Integer getTilesEnclosed(Day10Y2023 input) {
        Graph pipes = input.getPipes();
        PositionBoard positionBoard = input.getPositionBoard();
        List<Position> positions = positionBoard.getPositions();
        String startLabel = input.getPipes().getNodeLabels().stream()
                .filter(label->label.startsWith("S"))
                .findFirst().orElse("");
        Node startNode = pipes.getNode(startLabel);
        List<Node> loop = getLoop(startNode);
        List<Position> loppPos = loop.stream()
                .map(n->positionBoard.getPosition(Integer.valueOf(n.getValue().split(",")[1]), Integer.valueOf(n.getValue().split(",")[2])))
                .toList();
        startNode.setValue("");
        int maxI = positions.stream().mapToInt(Position::getI).max().orElse(0);
        int maxJ = positions.stream().mapToInt(Position::getJ).max().orElse(0);
        int insideCount = 0;
        for (int i = 0; i <= maxI; i++) {
            boolean inside = false;
            char pendingCorner = 0;
            for (int j = 0; j <= maxJ; j++) {
                Position p = positionBoard.getPosition(i, j);
                if (p == null) continue;
                char c = p.getValue().charAt(0);
                if (loppPos.contains(p)) {
                    if (c == 'S') c = '7';
                    if (c == '|') {
                        inside = !inside;
                    } else if (c == 'F' || c == 'L') {
                        pendingCorner = c;
                    } else if (c == 'J') {
                        if (pendingCorner == 'F') inside = !inside;
                        pendingCorner = 0;
                    } else if (c == '7') {
                        if (pendingCorner == 'L') inside = !inside;
                        pendingCorner = 0;
                    }
                } else {
                    if (inside) insideCount++;
                }
            }
        }
        return insideCount;
    }


}
