package utils;

public class Robot {
    
    private Integer i;

    private Integer j;

    private Integer di;

    private Integer dj;

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

    public Integer getDi() {
        return di;
    }

    public Integer getDj() {
        return dj;
    }

    public Robot(int i, int j, int di, int dj){
        this.i = i;
        this.j = j;
        this.di = di;
        this.dj = dj;
    }
}
