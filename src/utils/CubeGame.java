package utils;

public class CubeGame {
    
    private int id;

    private int maxGreens;

    private int maxBlues;

    private int maxReds;

    public int getId(){
        return this.id;
    }

    public int getMaxGreens(){
        return this.maxGreens;
    }

    public int getMaxBlues(){
        return this.maxBlues;
    }

    public int getMaxReds(){
        return this.maxReds;
    }

    public CubeGame(int id, int maxGreens, int maxBlues,int maxReds){
        this.id = id;
        this.maxBlues = maxBlues;
        this.maxGreens = maxGreens;
        this.maxReds = maxReds;
    }

}
