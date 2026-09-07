package aoc2023.day22;

import java.util.ArrayList;
import java.util.List;

import utils.Point;

public class Brick {
    int id;
    int x1;
    int y1;
    int z1;
    int x2;
    int y2;
    int z2;

    public Brick(int id, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public int getId() {
        return id;
    }

    public int minX() {
        return Math.min(x1, x2);
    }

    public int maxX() {
        return Math.max(x1, x2);
    }

    public int minY() {
        return Math.min(y1, y2);
    }

    public int maxY() {
        return Math.max(y1, y2);
    }

    public int minZ() {
        return Math.min(z1, z2);
    }

    public int maxZ() {
        return Math.max(z1, z2);
    }

    public void moveDown(int amount) {
        z1 -= amount;
        z2 -= amount;
    }

    public List<Point> getCubes() {
        List<Point> cubes = new ArrayList<>();
        for (int x = minX(); x <= maxX(); x++) {
            for (int y = minY(); y <= maxY(); y++) {
                for (int z = minZ(); z <= maxZ(); z++) {
                    cubes.add(new Point(x, y, z));
                }
            }
        }
        return cubes;
    }

    @Override
    public String toString() {
        return id + ": "
                + x1 + "," + y1 + "," + z1
                + "~"
                + x2 + "," + y2 + "," + z2;
    }
}

