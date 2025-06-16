package aoc2024.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Solution {
    
    public static void day3Solution(){
        Day3 input = Day3.getDay3Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day3 input){
        System.out.println("SOLUCION: " + getProgramResult(input));
    }

    private static Long getProgramResult(Day3 input){
        String regex = "mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input.getProgram());
        return matcher.results().mapToLong(result->Integer.parseInt(result.group(1))*Integer.parseInt(result.group(2))).sum();
    }

    private static void part2Solution(Day3 input){
        System.out.println("SOLUCION: " + getProgramResultWithConditions(input));
    }

    private static Long getProgramResultWithConditions(Day3 input){
        Map<Integer, MatchResult> mulByPosition = new HashMap<>();
        List<Integer> dos = new ArrayList<>();
        List<Integer> donts = new ArrayList<>(); 
        String program = input.getProgram();
        String regex = "mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(program);
        matcher.results().forEach(mul->mulByPosition.put(mul.start(),mul));
        String regexDo = "do\\(\\)";
        Pattern patternDo = Pattern.compile(regexDo);
        Matcher matcherDo = patternDo.matcher(program);
        matcherDo.results().forEach(make->dos.add(make.start()));
        String regexDont = "don't\\(\\)";
        Pattern patternDont = Pattern.compile(regexDont);
        Matcher matcherDont = patternDont.matcher(program);
        matcherDont.results().forEach(dont->donts.add(dont.start()));
        return mulByPosition.entrySet().stream().mapToLong(entrySet->{
            if(isBeforeInstructionDo(entrySet.getKey(), dos, donts)){
                return Integer.parseInt(entrySet.getValue().group(1)) * Integer.parseInt(entrySet.getValue().group(2));
            }else{
                return 0l;
            }
        }).sum();
    }

    private static boolean isBeforeInstructionDo(Integer position, List<Integer> dos, List<Integer> donts){
        Integer lastDo = dos.stream().filter(index-> index<position).mapToInt(Integer::intValue).max().orElse(0);
        Integer lastDont = donts.stream().filter(index-> index<position).mapToInt(Integer::intValue).max().orElse(0);
        if(lastDont==0||lastDo>lastDont){
            return true;
        }else{
            return false;
        }
    }
}