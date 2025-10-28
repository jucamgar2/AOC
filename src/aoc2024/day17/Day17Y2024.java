package aoc2024.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day17Y2024 {

    private Long registerA;

    private Long registerB;

    private Long registerC;

    private String[] programInstructions;

    private Integer programSize;

    private Integer pointer;

    public Long getRegisterA() {
        return this.registerA;
    }

    public Long getRegisterB() {
        return this.registerB;
    }

    public Long getRegisterC() {
        return this.registerC;
    }

    public String[] getProgramInstructions() {
        return this.programInstructions;
    }

    public Integer getProgramSize() {
        return this.programSize;
    }

    public Integer getPointer(){
        return this.pointer;
    }

    public void setRegisterA(Long registerA) {
        this.registerA = registerA;
    }

    public void setRegisterB(Long registerB) {
        this.registerB = registerB;
    }

    public void setRegisterC(Long registerC) {
        this.registerC = registerC;
    }

    public void setPointer(Integer pointer){
        this.pointer = pointer;
    }

    public Day17Y2024(Long registerA, Long registerB, Long registerC, String[] programInstructions){
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        this.programInstructions = programInstructions;
        this.programSize = programInstructions.length;
        this.pointer = 0;
    }

    public static Day17Y2024 getDay17Data(){
        Long registerA = 0l;
        Long registerB = 0l;
        Long registerC = 0l;
        String[] programInstructions = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day17Data.txt"))) {
            registerA = Long.valueOf(br.lines().filter(line->line.startsWith("Register A:")).findFirst().orElse(null).replace("Register A: ", ""));
            registerB = Long.valueOf(br.lines().filter(line->line.startsWith("Register B:")).findFirst().orElse(null).replace("Register B: ", ""));
            registerC = Long.valueOf(br.lines().filter(line->line.startsWith("Register C:")).findFirst().orElse(null).replace("Register C: ", ""));
            programInstructions = br.lines().filter(line->line.startsWith("Program: ")).findFirst().orElse(null).replace("Program: ", "").split(",");
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day17Y2024(registerA, registerB, registerC, programInstructions);
    }
    
}
