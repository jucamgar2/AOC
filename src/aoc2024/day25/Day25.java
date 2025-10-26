package aoc2024.day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day25 {

    private List<List<Integer>> locks;

    private List<List<Integer>> keys;

    public List<List<Integer>> getLocks(){
        return this.locks;
    }

    public List<List<Integer>> getKeys(){
        return this.keys;
    }

    public Day25(List<List<Integer>> keys, List<List<Integer>> locks){
        this.keys = keys;
        this.locks = locks;
    }

    public static Day25 getDay25Data(){
        List<List<Integer>> locks = new ArrayList<>();
        List<List<Integer>> keys = new ArrayList<>();
        List<String> blocks = new ArrayList<>();
        StringBuilder actualBlock = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/Day25Data.txt"))) {
            for(String line: br.lines().toList()){
                if(line.trim().isEmpty()){
                    blocks.add(actualBlock.toString());
                    actualBlock = new StringBuilder();
                }else{
                    actualBlock.append(line);
                }
            }
            blocks.add(actualBlock.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
        for(String block: blocks){
            if(block.charAt(0)=='#'){
                locks.add(parseBlockLength(block, "lock"));
            }else{
                keys.add(parseBlockLength(block, "key"));
            }
        }
        return new Day25(keys, locks);
    }

    private static List<Integer> parseBlockLength(String block, String type){
        List<Integer> parsedBlock = new ArrayList<>();
        if(type.equals("lock")){
            parsedBlock.add(0);
            parsedBlock.add(0);
            parsedBlock.add(0);
            parsedBlock.add(0);
            parsedBlock.add(0);
        }else{
            parsedBlock.add(-1);
            parsedBlock.add(-1);
            parsedBlock.add(-1);
            parsedBlock.add(-1);
            parsedBlock.add(-1);
        }
        for(int i = 5; i<block.length();i++){
            int column = (i - 5) % 5;
            if(block.charAt(i) == '#'){
                parsedBlock.set(column, parsedBlock.get(column) + 1);
            }
        }
        return parsedBlock;
    }
    
}
