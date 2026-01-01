package utils;

public class SpaceComparation implements Comparable<SpaceComparation>{

    private Long distance;

    private SpacePosition p1;

    private SpacePosition p2;

    public Long getDistance(){
        return this.distance;
    }

    public SpacePosition getP1(){
        return this.p1;
    }
    
    public SpacePosition getP2(){
        return this.p2;
    }

    public SpaceComparation(Long distance, SpacePosition p1, SpacePosition p2){
        this.distance = distance;
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public int compareTo(SpaceComparation o) {
        return Long.compare(this.distance, o.distance);
    }

    public String toString(){
        return this.getDistance().toString();
    }

}
