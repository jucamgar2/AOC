package aoc2024.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day9Y2024 {

    Map<Integer, Long> sortedMap;

    Map<Integer,Integer> emptyBlocks;

    public Day9Y2024(Map<Integer,Long> sortedMap,Map<Integer, Integer> emptyBlocks){
        this.sortedMap = sortedMap;
        this.emptyBlocks = emptyBlocks;
    }

    public Map<Integer,Long> getSortedMap(){
        return this.sortedMap;
    }

    public Map<Integer, Integer> getEmptyBlocks(){
        return this.emptyBlocks;
    }

    public Long getMaxId(){
        return this.sortedMap.values().stream().filter(x->!x.equals(Long.MAX_VALUE)).mapToLong(x->x).max().orElse(0);
    }

    public Integer getBlockPosition(Long id){
        return this.sortedMap.entrySet().stream().filter(x->x.getValue().equals(id)).mapToInt(x->x.getKey()).findFirst().orElse(Integer.MAX_VALUE);
    }

    public static Day9Y2024 getDay9Data(){
        Map<Integer,Integer> emptyBlocks = new HashMap<>();
        String disk = "";
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2024/TestDay9.txt"))) {
            String line;
            while((line = br.readLine())!=null){
                disk += line;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return transformDisk(disk, emptyBlocks);
    }

    public static Day9Y2024 transformDisk(String disk, Map<Integer,Integer> emptyBlocks){
        Map<Integer,Long> sortedMap = new HashMap<>();
        int counter = 0;
        for(int i = 0; i<disk.length();i++){
            int repetitions = Integer.valueOf(String.valueOf(disk.charAt(i)));
            if(i%2==0){
                for(int j=0;j<repetitions;j++){
                    sortedMap.put(counter, Long.valueOf(i/2));
                    counter++;
                }
            }else{
                emptyBlocks.put(counter, repetitions);
                for(int j=0;j<repetitions;j++){
                    sortedMap.put(counter, Long.MAX_VALUE);
                    counter++;
                }
            }
        }
        return new Day9Y2024(sortedMap, emptyBlocks);
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        this.sortedMap.entrySet().stream().mapToLong(x->x.getValue()).forEach(num -> {
            if (num != Long.MAX_VALUE) {
                res.append(num);
            } else {
                res.append(".");
            }
        });
        return res.toString();
    }
}
