package utils;

public class RangeNum {

    long min;
    long max;

    public RangeNum(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public long size() {
        return Math.max(0, max - min + 1);
    }

    public RangeNum copy() {
        return new RangeNum(min, max);
    }

    public long getMin(){
        return this.min;
    }

    public void setMin(long min){
        this.min = min;
    }

    public long getMax(){
        return this.max;
    }

    public void setMax(long max){
        this.max = max;
    }

}
