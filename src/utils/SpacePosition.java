package utils;

public class SpacePosition {

    private Integer x;

    private Integer y;

    private Integer z;

    public Integer getX(){
        return this.x;
    }

    public Integer getY(){
        return this.y;
    }

    public Integer getZ(){
        return this.z;
    }

    public SpacePosition(Integer x, Integer y, Integer z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString(){
        return "{" + this.x + ", " + this.y + ", " + this.z+"}";
    }
    
}
