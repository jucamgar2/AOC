package utils;

public class IntPosition {
    private Integer i;

    private Integer j;

    private Integer value;

    public IntPosition(Integer i, Integer j, Integer value){
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String toString(){
        return "{" + this.i + ", " + this.j +  ", " + this.value + "}";
    }

    public IntPosition clone() {
        return new IntPosition(this.i, this.j, this.value);
    }
}
