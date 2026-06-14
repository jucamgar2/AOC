package utils;

public record State(int row, int col, Direction dir, int steps, int cost) implements Comparable<State> {

    @Override
    public int compareTo(State other) {
        return Integer.compare(this.cost(), other.cost());
    }
}
