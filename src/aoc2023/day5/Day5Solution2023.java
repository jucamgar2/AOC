package aoc2023.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import structure.BaseDay;
import utils.MappingRange;
import utils.Range;

public class Day5Solution2023 extends BaseDay{

    @Override
    protected Day5Y2023 getInputData() {
        return Day5Y2023.readDay5Data();
    }

    @Override
    public void runDaySolution() {
        Day5Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getMinLocation((Day5Y2023) input));
    }

    private Long getMinLocation(Day5Y2023 input) {
        return makeMappingsAndGetMin(input.getSeeds(), input);
    }

    private Long makeMappingsAndGetMin(List<Long> seeds, Day5Y2023 input){
        return seeds.stream()
                    .mapToLong(seed->mapValue(seed, input.getSeedToSoilMappings()))
                    .map(soil->mapValue(soil, input.getSoilToFertilizerMappings()))
                    .map(fertilizer->mapValue(fertilizer, input.getFertilizerToWaterMappings()))
                    .map(water->mapValue(water, input.getWaterToLightMappings()))
                    .map(light->mapValue(light, input.getLightToTemperatureMappings()))
                    .map(temperature-> mapValue(temperature, input.getTemperatureToHumidityMappings()))
                    .map(humidity-> mapValue(humidity, input.getHumidityToLocationMappings()))
                    .min().orElse(0);
    }

    private Long mapValue(Long seed, List<MappingRange> mapper) {
        MappingRange rangeMapper = mapper.stream()
                                    .filter(range->range.getSourceMin()<=seed && range.getSourceMax()>=seed)
                                    .findFirst()
                                    .orElse(null);
        if(rangeMapper==null){
            return seed;
        }else{
            Long diff = seed - rangeMapper.getSourceMin();
            return rangeMapper.getDestinationMin()+diff;
        }
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: "+ getMinLocationWithSeedsInrange((Day5Y2023)input));
    }

    private Long getMinLocationWithSeedsInrange(Day5Y2023 input) {
        return IntStream.range(0, input.getSeeds().size() / 2)
            .mapToObj(index-> {
                Long start = input.getSeeds().get(index * 2);
                Long length = input.getSeeds().get(index * 2 + 1);
                return new Range(start, start+length);
            })
            .flatMap(range->mapRange(range, input.getSeedToSoilMappings()).stream())
            .flatMap(range->mapRange(range, input.getSoilToFertilizerMappings()).stream())
            .flatMap(range->mapRange(range, input.getFertilizerToWaterMappings()).stream())
            .flatMap(range->mapRange(range, input.getWaterToLightMappings()).stream())
            .flatMap(range->mapRange(range, input.getLightToTemperatureMappings()).stream())
            .flatMap(range->mapRange(range, input.getTemperatureToHumidityMappings()).stream())
            .flatMap(range->mapRange(range, input.getHumidityToLocationMappings()).stream())
            .mapToLong(Range::start)
            .min().orElse(0);
    }

    private List<Range> mapRange(Range range, List<MappingRange> mapper){
        List<Range> pending = new ArrayList<>();
        List<Range> result = new ArrayList<>();
        pending.add(range);
        for(MappingRange map : mapper){
            List<Range> nextPending = new ArrayList<>();
            for(Range current : pending){
                long start = current.start();
                long end = current.end();
                long sourceMin = map.getSourceMin();
                long sourceMax = map.getSourceMax();
                if(end < sourceMin || start > sourceMax){
                    nextPending.add(current);
                    continue;
                }
                if(start < sourceMin){
                    nextPending.add(new Range(start, sourceMin - 1));
                }
                long interStart = Math.max(start, sourceMin);
                long interEnd = Math.min(end, sourceMax);
                long offset = map.getDestinationMin() - map.getSourceMin();
                result.add(new Range(
                    interStart + offset,
                    interEnd + offset
                ));
                if(end > sourceMax){
                    nextPending.add(new Range(sourceMax + 1, end));
                }
            }
            pending = nextPending;
        }
        result.addAll(pending);
        return result;
    }    
    
}
