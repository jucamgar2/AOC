package utils;

import java.util.Objects;

public class Tour {
    
    private Position position;

    private TourDirection direction;

    public Position getPosition(){
        return this.position;
    }

    public TourDirection getDirection(){
        return this.direction;
    }

    public Tour(Position position, TourDirection direction){
        this.position = position;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Tour other = (Tour) obj;

        return position.equals(other.position)
                && direction == other.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }
}
