package utils;

public class PartRange {
    public RangeNum x;
    public RangeNum m;
    public RangeNum a;
    public RangeNum s;    

    public PartRange copy() {
        PartRange copy = new PartRange();
        copy.x = x.copy();
        copy.m = m.copy();
        copy.a = a.copy();
        copy.s = s.copy();
        return copy;
    }
}
