package utils;

import java.util.List;

public class Shape {
    private List<boolean[][]> variants;
    private int area;

    public Shape( List<boolean[][]> variants, int area){
        this.variants = variants;
        this.area = area;
    }

    public List<boolean[][]> getVariants(){
        return this.variants;
    }

    public int getArea(){
        return this.area;
    }


}
