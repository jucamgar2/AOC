package utils;

public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    private int di;
    private int dj;

    public int getDi(){
        return this.di;
    }

    public int getDj(){
        return this.dj;
    }

    Direction(int di, int dj) {
        this.di = di;
        this.dj = dj;
    }

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST  -> WEST;
            case WEST  -> EAST;
        };
    }
}
