package utils;

public class PasswordCondition {

    private Integer limit1;

    private Integer limit2;

    private char character;

    private String password;

    public Integer getLimit1(){
        return this.limit1;
    }

    public Integer getLimit2(){
        return this.limit2;
    }
    
    public char getCharacter(){
        return this.character;
    }

    public String getPassword(){
        return this.password;
    }

    public PasswordCondition(Integer limit1, Integer limit2, char character, String password){
        this.limit1 = limit1;
        this.limit2 = limit2;
        this.character = character;
        this.password = password;
    } 
}
