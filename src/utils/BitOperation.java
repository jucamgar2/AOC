package utils;

public class BitOperation {

    private String wire1;

    private String wire2;

    private String operation;

    public String getWire1(){
        return this.wire1;
    }

    public String getWire2(){
        return this.wire2;
    }

    public String getOperation(){
        return this.operation;
    }

    public BitOperation(String wire1, String wire2, String operation){
        this.wire1 = wire1;
        this.wire2 = wire2;
        this.operation = operation;
    }

    public BitOperation (String operation){
        String[] operators = operation.split(" ");
        this.wire1 = operators[0].trim();
        this.wire2 = operators[2].trim();
        this.operation = operators[1].trim();
    }

    
}
