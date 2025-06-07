package aoc2025.day6;

public class Position {

    private Integer i;

    private Integer j;

    private String value;

    public Position(Integer i, Integer j, String value){
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString(){
        return "{" + this.i + ", " + this.j +  ", " + this.value + "}";
    }

    public Position clone() {
        return new Position(this.i, this.j, this.value);
    }

}
