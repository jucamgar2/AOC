package aoc2024.day2;

import java.util.List;
import java.util.stream.IntStream;

public class Day2Solution {
    
    public static void day2Solution(){
        Day2 input = Day2.getDay2Data();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    private static void part1Solution(Day2 input){
        System.out.println("SOLUCION:" + getSafeReports(input));
    }

    private static Long getSafeReports(Day2 input){
        return input.getReports().stream().filter(x->isReportSafe(x)).count();
    }   

    private static boolean isReportSafe(List<Integer> report){
        return isReportSort(report)&&checkDistances(report);
    }

    private static boolean isReportSort(List<Integer> report){
        boolean isSort = true;
        if(report.get(0)==report.get(1)){
            return false;
        }
        boolean isAscending = report.get(0)<report.get(1);
        for(int i = 2;i<report.size()&&isSort;i++){
            if(isAscending){
                isSort = report.get(i-1)<report.get(i);
            }else{
                isSort = report.get(i-1)>report.get(i);
            }
        }
        return isSort;
    }

    private static boolean checkDistances(List<Integer> report){
        return IntStream.range(0, report.size()-1)
        .map(index->Math.abs(report.get(index)-report.get(index+1)))
        .allMatch(difference-> difference<=3);
    }

    private static void part2Solution(Day2 input){
        Long res = getSafeReports(input) + getNewSafeReports(input);
        System.out.println("SOLUCION:" + res);
    }

    private static Long getNewSafeReports(Day2 input){
        return input.getReports().stream().filter(x->!isReportSafe(x)).filter(report->checkConditionsWithTolerance(report)).count();
    }

    private static boolean checkConditionsWithTolerance(List<Integer> report){
        List<Integer> reportWithOutStart = report.subList(1, report.size());
        if(isReportSafe(reportWithOutStart)){
            return true;
        }
        boolean isSort = true;
        if((report.get(0)==report.get(1) || !checkDiference(report.get(0),report.get(1)))){
            report.remove(1);
            return isReportSafe(report);
        }
        boolean isAscending = report.get(0)<report.get(1);
        for(int i = 2;i<report.size()&&isSort;i++){
            if(isAscending){
                isSort = report.get(i-1)<report.get(i);
            }else{
                isSort = report.get(i-1)>report.get(i);
            }
            if((!isSort||!checkDiference(report.get(i-1), report.get(i)))){
                report.remove(i);
                return isReportSafe(report);
            }
        }
        return isSort;
    }

    private static boolean checkDiference(Integer n1, Integer n2){
        return Math.abs(n1-n2)<=3;
    }



}
