package aoc2024.day22;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Day22Solution {

    public static void day22Solution(){
        Day22 input = Day22.getDay22Data();
        System.out.println("--------------------------PART1--------------------------");
        Map<Long, List<Integer>> priceSucesionMap = new HashMap<>();
        part1Solution(input, priceSucesionMap);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(priceSucesionMap);
    }

    private static void part1Solution(Day22 input, Map<Long,List<Integer>> priceSucesionMap) {
        System.out.println("SOLUCION: " + getSumOfSecretCodes(input, priceSucesionMap));
    }

    private static Long getSumOfSecretCodes(Day22 input, Map<Long,List<Integer>> priceSucesionMap) {
        return input.getSecretCodes().stream()
            .mapToLong(secretCode->iterateSecretCode(secretCode, priceSucesionMap))
            .sum();
    }

    private static Long iterateSecretCode(Long secretCode, Map<Long,List<Integer>> priceSucesionMap) {
        List<Integer> priceSucesion = new ArrayList<>();
        priceSucesionMap.put(secretCode, priceSucesion);
        for(int i = 0;i<2000;i++){  
            Long tempSecret = secretCode*64;
            secretCode = secretCode ^ tempSecret;
            secretCode = secretCode % 16777216;
            tempSecret = Math.floorDiv(secretCode, 32);
            secretCode = secretCode ^ tempSecret;
            secretCode = secretCode % 16777216;
            tempSecret = secretCode * 2048;
            secretCode = secretCode ^ tempSecret;
            secretCode = secretCode % 16777216;
            priceSucesion.add((int) (secretCode%10));
        }
        return secretCode;
    }

    private static void part2Solution(Map<Long, List<Integer>> priceSucesionMap) {
        System.out.println("SOLUCION: " + getMaxOfBananas( priceSucesionMap));
    }

    private static Integer getMaxOfBananas(Map<Long, List<Integer>> priceSucesionMap) {
        Map<String, Integer> priceBySucesion = getPriceBySucesion(priceSucesionMap);
        return priceBySucesion.values().stream().max(Integer::compareTo).orElse(null);
    }

    private static Map<String,Integer> getPriceBySucesion(Map<Long, List<Integer>> priceSucesionMap){
        Map<String, Integer> priceForSucesion = new HashMap<>();
        for(Entry<Long, List<Integer>> entry: priceSucesionMap.entrySet()){
            List<Integer> priceVariation = entry.getValue();
            Set<String> visitedSequences = new HashSet<>();
            for(int i = 0; i<priceVariation.size()-5; i++){
                Integer p1 = priceVariation.get(i);
                Integer p2 = priceVariation.get(i+1);
                Integer p3 = priceVariation.get(i+2);
                Integer p4 = priceVariation.get(i+3);
                Integer p5 = priceVariation.get(i+4);
                String sucesion = (p2-p1) + "," + (p3-p2) + "," + (p4-p3) + "," + (p5-p4);
                if(visitedSequences.contains(sucesion)){
                    continue;
                }
                visitedSequences.add(sucesion);
                if(priceForSucesion.containsKey(sucesion)){
                    priceForSucesion.put(sucesion, priceForSucesion.get(sucesion) + p5);
                }else{
                    priceForSucesion.put(sucesion, p5);
                }
            }
        }
        return priceForSucesion;
    }


    
}
