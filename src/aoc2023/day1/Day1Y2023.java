package aoc2023.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Y2023 {

    private List<String> calibrationDocument;

    public List<String> getCalibrationDocument(){
        return this.calibrationDocument;
    }

    public Day1Y2023(List<String> calibrationDocument){
        this.calibrationDocument = calibrationDocument;
    }

    public static Day1Y2023 getDay1Data(){
        List<String> calibrationDocument = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/Day1Data.txt"))) {
            br.lines().forEach(line->{
                calibrationDocument.add(line);
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return new Day1Y2023(calibrationDocument);
    }
    
}
