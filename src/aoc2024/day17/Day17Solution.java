package aoc2024.day17;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day17Solution {

    public static void day17Solution(){
        Day17 input = Day17.getDay17Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        input = Day17.getDay17Data();
        System.out.println("--------------------------PART1--------------------------");
        part2Solution(input);
    }

    public static void part1Solution(Day17 input){
        System.out.println("SOLUCION: " + getProgramOutput(input));
    }

    public static String getProgramOutput(Day17 input){
        String output = "";
        while(input.getPointer()<input.getProgramSize()){
            Integer instruction = Integer.parseInt(input.getProgramInstructions()[input.getPointer()]);
            Long operand = Long.parseLong(input.getProgramInstructions()[input.getPointer()+1]);
            output = runInstruction(input, input.getPointer(), instruction, operand, output);
        }
        return output;
    }

    public static String runInstruction(Day17 input, Integer pointer, Integer instruction, Long operand, String output){
        switch (instruction) {
            case 0:
                adv( operand, input, pointer);
                pointer +=2;
                break;
            case 1:
                bxl(input, operand, pointer);
                pointer +=2;
                break;
            case 2:
                bst(input, operand, pointer);
                pointer +=2;
                break;
            case 3:
                if(input.getRegisterA()!=0){
                    pointer = operand.intValue();
                }else{
                    pointer +=2;
                }
                break;
            case 4:
                bxc(input);
                pointer +=2;
                break;
            case 5:
                output = out(input, operand, output);
                pointer +=2;
                break;
            case 6:
                bdv(operand, input, pointer);
                pointer +=2;
                break;
            case 7:
                cdv(operand, input, pointer);
                pointer +=2;
                break;
            default:
                break;
        }
        input.setPointer(pointer);
        return output;
    }

    public static Long getComboOperand(Long operand, Day17 input){
        if(operand<4){
            return operand;
        }else if(operand ==4){
            return input.getRegisterA();
        }else if(operand == 5){
            return input.getRegisterB();
        }else{
            return input.getRegisterC();
        }
    }

    public static void adv(Long operand, Day17 input, Integer poninter){
        input.setRegisterA(input.getRegisterA() / (long)Math.pow(2, getComboOperand(operand, input)));
    }

    public static void bdv(Long operand, Day17 input, Integer poninter){
        input.setRegisterB(input.getRegisterA() / (long)Math.pow(2, getComboOperand(operand, input)));
    }

    public static void cdv(Long operand, Day17 input, Integer poninter){
        input.setRegisterC(input.getRegisterA() / (long)Math.pow(2, getComboOperand(operand, input)));
    }

    public static void bxl(Day17 input, Long operand, Integer poninter){
        input.setRegisterB(input.getRegisterB()^operand);
    }

    public static void bst(Day17 input, Long operand, Integer poninter){
        input.setRegisterB(getComboOperand(operand, input)%8);
    }

    public static void bxc(Day17 input){
        input.setRegisterB(input.getRegisterB()^input.getRegisterC());
    }

    public static String out(Day17 input, Long operand, String output){
        output += getComboOperand(operand, input)%8 + ",";
        return output;
    }
    
    private static void part2Solution(Day17 input) {
        System.out.println("SOLUCIÓN: " + findSolution(input.getProgramInstructions(), 0, input.getProgramInstructions()));
    }

    private static Long findSolution(String[] target, long answer, String[] program){
        if(target.length == 0){
            return answer;
        }
        for(int t = 0;t<8;t++){
            Map<String, Long> registers = new HashMap<>();
            registers.put("A", (answer << 3) | t);
            registers.put("B", 0L);
            registers.put("C", 0L);
            Long output = null;
            boolean adv3Encountered = false;

            for(int pointer = 0; pointer<program.length -2; pointer+=2){
                Integer opcode = Integer.parseInt(program[pointer]);
                Long operand = Long.parseLong(program[pointer+1]);

                switch (opcode) {
                    case 0: // ADV (A / 8)
                        if (adv3Encountered) {
                            throw new IllegalStateException("Error: múltiples ADV en el programa");
                        }
                        if (operand != 3) {
                            throw new IllegalStateException("ADV con operando inválido");
                        }
                        adv3Encountered = true;
                        break;
                    case 1: 
                        registers.put("B", registers.get("B") ^ operand);
                        break;
                    case 2: 
                        registers.put("B", getComboValue(operand, registers) % 8);
                        break;
                    case 3: 
                        throw new IllegalStateException("JNZ encontrado fuera del bucle esperado");
                    case 4:
                        registers.put("B", registers.get("B") ^ registers.get("C"));
                        break;
                    case 5: 
                        if (output != null) {
                            throw new IllegalStateException("Múltiples OUT en el programa");
                        }
                        output = getComboValue(operand, registers) % 8;
                        break;
                    case 6: 
                        registers.put("B", registers.get("A") >> getComboValue(operand, registers));
                        break;
                    case 7:
                        registers.put("C", registers.get("A") >> getComboValue(operand, registers));
                        break;
                    default:
                        throw new IllegalStateException("Opcode desconocido: " + opcode);
                }
            }
            if (output != null && output.equals(Long.parseLong(target[target.length - 1]))) {
                Long subResult = findSolution(
                    Arrays.copyOf(target, target.length - 1),
                    registers.get("A"),
                    program
                );
                if (subResult != null) {
                    return subResult;
                }
            }
        }

        return null;
    }

    private static long getComboValue(long operand, Map<String, Long> registers) {
        if(operand<4){
            return operand;
        }else if(operand==4){
            return registers.get("A");
        }else if(operand==5){
            return registers.get("B");
        }else{
            return registers.get("C");
        }
    }
}
