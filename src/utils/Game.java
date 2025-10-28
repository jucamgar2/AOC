package utils;

public class Game {

    private Integer aMoveX;

    private Integer aMoveY;

    private Integer bMoveX;

    private Integer bMoveY;

    private Integer xObj;

    private Integer yObj;
    
    public Game(Integer aMoveX, Integer aMoveY, Integer bMoveX, Integer bMoveY, Integer xObj, Integer yObj) {
        this.aMoveX = aMoveX;
        this.aMoveY = aMoveY;
        this.bMoveX = bMoveX;
        this.bMoveY = bMoveY;
        this.xObj = xObj;
        this.yObj = yObj;
    }

    public Integer getaMoveX() {
        return aMoveX;
    }

    public Integer getaMoveY() {
        return aMoveY;
    }

    public Integer getbMoveX() {
        return bMoveX;
    }

    public Integer getbMoveY() {
        return bMoveY;
    }

    public Integer getxObj() {
        return xObj;
    }

    public Integer getyObj() {
        return yObj;
    }
    
}
