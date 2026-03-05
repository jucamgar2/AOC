package aoc2023.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.MappingRange;

public class Day5Y2023 {
    
    private List<Long> seeds;

    private List<MappingRange> seedToSoilMappings;

    private List<MappingRange> soilToFertilizerMappings;
    
    private List<MappingRange> fertilizerToWaterMappings;

    private List<MappingRange> waterToLightMappings;

    private List<MappingRange> lightToTemperatureMappings;

    private List<MappingRange> temperatureToHumidityMappings;

    private List<MappingRange> humidityToLocationMappings;

    
    /**
     * Constructs a Day5Y2023 instance with all mapping lists and seeds.
     */
    public Day5Y2023(
            List<Long> seeds,
            List<MappingRange> seedToSoilMappings,
            List<MappingRange> soilToFertilizerMappings,
            List<MappingRange> fertilizerToWaterMappings,
            List<MappingRange> waterToLightMappings,
            List<MappingRange> lightToTemperatureMappings,
            List<MappingRange> temperatureToHumidityMappings,
            List<MappingRange> humidityToLocationMappings) {
        this.seeds = seeds;
        this.seedToSoilMappings = seedToSoilMappings;
        this.soilToFertilizerMappings = soilToFertilizerMappings;
        this.fertilizerToWaterMappings = fertilizerToWaterMappings;
        this.waterToLightMappings = waterToLightMappings;
        this.lightToTemperatureMappings = lightToTemperatureMappings;
        this.temperatureToHumidityMappings = temperatureToHumidityMappings;
        this.humidityToLocationMappings = humidityToLocationMappings;
    }

    public List<Long> getSeeds() {
        return seeds;
    }

    public List<MappingRange> getSeedToSoilMappings() {
        return seedToSoilMappings;
    }

    public List<MappingRange> getSoilToFertilizerMappings() {
        return soilToFertilizerMappings;
    }

    public List<MappingRange> getFertilizerToWaterMappings() {
        return fertilizerToWaterMappings;
    }

    public List<MappingRange> getWaterToLightMappings() {
        return waterToLightMappings;
    }

    public List<MappingRange> getLightToTemperatureMappings() {
        return lightToTemperatureMappings;
    }

    public List<MappingRange> getTemperatureToHumidityMappings() {
        return temperatureToHumidityMappings;
    }

    public List<MappingRange> getHumidityToLocationMappings() {
        return humidityToLocationMappings;
    }

    public static Day5Y2023 readDay5Data(){
        List<Long> seeds = new ArrayList<>();
        List<MappingRange> seedToSoilMappings = new ArrayList<>();
        List<MappingRange> soilToFertilizerMappings = new ArrayList<>();
        List<MappingRange> fertilizerToWaterMappings = new ArrayList<>();
        List<MappingRange> waterToLightMappings = new ArrayList<>();
        List<MappingRange> lightToTemperatureMappings = new ArrayList<>();
        List<MappingRange> temperatureToHumidityMappings = new ArrayList<>();
        List<MappingRange> humidityToLocationMappings = new ArrayList<>();
        final String[] mapper = {""};
        try (BufferedReader br = new BufferedReader(new FileReader("inputs/2023/TestDay5.txt"))) {
            br.lines().forEach(line->{
                if(!"".equals(line)){
                    if(line.contains("seeds:")){
                        line = line.replace("seeds: ", "");
                        seeds.addAll(Arrays.asList(line.split(" ")).stream().map(Long::parseLong).toList());
                    }
                    mapper[0] = calculateMapper(line, mapper[0]);
                    switch (mapper[0]) {
                        case "soil":
                            fillMappings(line, seedToSoilMappings);
                            break;
                        case "fertilizer":
                            fillMappings(line, soilToFertilizerMappings);
                            break;
                        case "water":
                            fillMappings(line, fertilizerToWaterMappings);
                            break;
                        case "light":
                            fillMappings(line, waterToLightMappings);
                            break;
                        case "temperature":
                            fillMappings(line, lightToTemperatureMappings);
                            break;
                        case "humidity":
                            fillMappings(line, temperatureToHumidityMappings);
                            break;
                        case "location":
                            fillMappings(line, humidityToLocationMappings);
                            break;
                        default:
                            break;
                    }

                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Day5Y2023(seeds, seedToSoilMappings, soilToFertilizerMappings, 
            fertilizerToWaterMappings, waterToLightMappings, lightToTemperatureMappings, 
            temperatureToHumidityMappings, humidityToLocationMappings);
    }

    private static String calculateMapper(String line, String mapper) {
        if(line.contains("seed-to-soil map:")){
            return "soil";
        }else if(line.contains("soil-to-fertilizer map:")){
            return "fertilizer";
        }else if(line.contains("fertilizer-to-water map:")){
            return "water";
        }else if(line.contains("water-to-light map:")){
            return "light";
        }else if(line.contains("light-to-temperature map:")){
            return "temperature";
        }else if(line.contains("temperature-to-humidity map:")){
            return "humidity";
        }else if(line.contains("humidity-to-location map:")){
            return "location";
        }else{
            return mapper;
        }
    }

    private static void fillMappings(String line, List<MappingRange> mappingRanges){
        if(!line.contains("map")){
            String[] values = line.split(" ");
            Long destination = Long.valueOf(values[0]);
            Long source = Long.valueOf(values[1]);
            Long range = Long.valueOf(values[2]);
            MappingRange mappingRange = new MappingRange(destination, destination+range, source, source+range);
            mappingRanges.add(mappingRange);
        }
    }

}
