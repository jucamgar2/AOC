package aoc2025.day4;

import aoc2025.day6.Position;

public class Day4Solution {
    public static void day4Solution(){
        Day4 input = Day4.getDay4Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    public static void part1Solution(Day4 input){
        Integer res = input.getPositions().stream().filter(position->position.getValue().equals("X")).mapToInt(position->getNumOfXmasByX(position, input)).sum();
        System.out.println("SOLUCION: " + res);
    }

    public static int getNumOfXmasByX(Position position,Day4 input){
        int countOfXMAS = 0;
        int i = position.getI();
        int j = position.getJ();
        countOfXMAS = calculatePrincipalDirections(i, j, input);
        countOfXMAS += calculateSecondaryDirections(i, j, input);
        return countOfXMAS;
    }

    public static boolean checkIfXMAS(String c1, String c2, String c3){
        return c1.equals("M") && c2.equals("A") && c3.equals("S");
    }

    public static int calculatePrincipalDirections(int i, int j, Day4 input){
        int principalXMAS = 0;
        if(checkIfXMAS(input.getValueP(i-1, j), input.getValueP(i-2, j),input.getValueP(i-3, j))){
            principalXMAS++;
        }
        if(checkIfXMAS(input.getValueP(i, j+1), input.getValueP(i, j+2),input.getValueP(i, j+3))){
            principalXMAS++;
        }
        if(checkIfXMAS(input.getValueP(i+1, j), input.getValueP(i+2, j),input.getValueP(i+3, j))){
            principalXMAS++;
        }
        if(checkIfXMAS(input.getValueP(i, j-1), input.getValueP(i, j-2),input.getValueP(i, j-3))){
            principalXMAS++;
        }
        return principalXMAS;
    }

    private static int calculateSecondaryDirections(int i, int j, Day4 input) {
        int secondXMAS = 0;
        if(checkIfXMAS(input.getValueP(i-1, j+1), input.getValueP(i-2, j+2),input.getValueP(i-3, j+3))){
            secondXMAS++;
        }
        if(checkIfXMAS(input.getValueP(i+1, j+1), input.getValueP(i+2, j+2),input.getValueP(i+3, j+3))){
            secondXMAS++;
        }
        if(checkIfXMAS(input.getValueP(i+1, j-1), input.getValueP(i+2, j-2),input.getValueP(i+3, j-3))){
            secondXMAS++;
        }
        if(checkIfXMAS(input.getValueP(i-1, j-1), input.getValueP(i-2, j-2),input.getValueP(i-3, j-3))){
            secondXMAS++;
        }
        return secondXMAS;
    }

    public static void part2Solution(Day4 input){
        Long res = input.getPositions().stream().filter(x->x.getValue().equals("A")).filter(p->isAinMasX(p,input)).count();
        System.out.println("RESULTADO: " + res);
    }

    public static boolean isAinMasX(Position p, Day4 input){
        int i = p.getI();
        int j = p.getJ();
        String upLeftChar = input.getValueP(i-1,j-1);
        String upRightChar = input.getValueP(i-1, j+1);
        String downLeftChar = input.getValueP(i+1, j-1);
        String downRightChar = input.getValueP(i+1, j+1);
        return checkFirstDiag(upLeftChar, downRightChar) && checkSecondDiag(upRightChar, downLeftChar);

    }

    public static boolean checkFirstDiag(String ulc, String drc){
        return (ulc.equals("M")&&drc.equals("S")) 
                || (ulc.equals("S")&&drc.equals("M"));
    }

    public static boolean checkSecondDiag(String urc, String dlc){
        return (urc.equals("M")&&dlc.equals("S"))
                || (urc.equals("S")&&dlc.equals("M"));
    }
}
