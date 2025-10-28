package aoc2024.day9;

import java.util.Map;

import structure.BaseDay;

public class Day9Solution2024 extends BaseDay{

    @Override
    protected Day9Y2024 getInputData(){
        return Day9Y2024.getDay9Data();
    }

    @Override
    public void runDaySolution(){
        Day9Y2024 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        input = Day9Y2024.getDay9Data();
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input){
        System.out.println("SOLUCION: " + getChecksum((Day9Y2024)input));
    }

    private static Long getChecksum(Day9Y2024 input){
        Map<Integer,Long> sortedDisk = getSortedDisk(input);
        return sortedDisk.entrySet().stream().filter(entrySet->!entrySet.getValue().equals(Long.MAX_VALUE)).mapToLong(entrySet->entrySet.getKey()*entrySet.getValue()).sum();
    }

    private static Map<Integer,Long> getSortedDisk(Day9Y2024 input){
        Map<Integer,Long> sortedDisk = input.getSortedMap();
        int firstEmptyBlock = findFirstEmptyBlock(sortedDisk);
        int lastNumber = findLastNumber(sortedDisk);
        while(firstEmptyBlock<lastNumber){
            sortedDisk.put(firstEmptyBlock, sortedDisk.get(lastNumber));
            sortedDisk.put(lastNumber,Long.MAX_VALUE);
            firstEmptyBlock = findFirstEmptyBlock(sortedDisk);
            lastNumber = findLastNumber(sortedDisk);
        }
        return sortedDisk;
    }

    private static int findFirstEmptyBlock(Map<Integer,Long> disk){
        return disk.entrySet().stream().filter(entrySet->entrySet.getValue().equals(Long.MAX_VALUE)).mapToInt(entrySet->entrySet.getKey()).min().orElse(Integer.MAX_VALUE);
    }

    private static int findLastNumber(Map<Integer,Long> disk){
        return disk.entrySet().stream().filter(entrySet->!entrySet.getValue().equals(Long.MAX_VALUE)).mapToInt(entrySet->entrySet.getKey()).max().orElse(0);
    }

    @Override
    protected void part2Solution(Object input){
        System.out.println("SOLUCION: " + getChechsumWithNoFragmentation((Day9Y2024)input));
    }

    private static Long getChechsumWithNoFragmentation(Day9Y2024 input){
        Map<Integer,Long> sortedDisk = getSortedDiskWithNoFragmentation(input);
        return sortedDisk.entrySet().stream().filter(entrySet->!entrySet.getValue().equals(Long.MAX_VALUE)).mapToLong(entrySet->entrySet.getKey()*entrySet.getValue()).sum();
    }

    private static Map<Integer,Long> getSortedDiskWithNoFragmentation(Day9Y2024 input){
        Map<Integer,Long> sortedMap = input.getSortedMap();
        for (Long id = input.getMaxId();id>0;id--){
            Integer blockSize = getBlockSize(id, sortedMap);
            Integer blockNewPlace = searchEmptyBlock(input.getEmptyBlocks(), blockSize);
            Integer blockPlace = input.getBlockPosition(id);
            if(!blockNewPlace.equals(Integer.MAX_VALUE) && !blockPlace.equals(Integer.MAX_VALUE)){
                for(int i = 0; i<blockSize;i++){
                    sortedMap.put(blockPlace+i, Long.MAX_VALUE);
                    sortedMap.put(blockNewPlace+i, id);
                }
            }
        }
        return sortedMap;
    }

    private static Integer getBlockSize(Long id, Map<Integer,Long> sortedMap){
        long appearances = sortedMap.values().stream().filter(x->x.equals(id)).count();
        return (int) (appearances);
    }

    private static Integer searchEmptyBlock(Map<Integer,Integer> emptyBlocks, Integer blockSize){
        Integer emptyBlock = emptyBlocks.entrySet().stream().filter(x->x.getValue()>=blockSize).mapToInt(x->x.getKey()).min().orElse(Integer.MAX_VALUE);
        if(emptyBlock!=Integer.MAX_VALUE){
            Integer newEmtpyBlockSize = emptyBlocks.get(emptyBlock);
            emptyBlocks.remove(emptyBlock);
            emptyBlocks.put(emptyBlock+blockSize, newEmtpyBlockSize-blockSize);
        }
        return emptyBlock;
    }
    
}