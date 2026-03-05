package utils;

public class MappingRange {

    private Long destinationMin;

    private Long destinationMax;

    private Long sourceMin;

    private Long sourceMax;

    public Long getDestinationMin(){
        return this.destinationMin;
    }

    public Long getDestinationMax(){
        return this.destinationMax;
    }

    public Long getSourceMin(){
        return this.sourceMin;
    }

    public Long getSourceMax(){
        return this.sourceMax;
    }

    public MappingRange(Long destinationMin, Long destinationMax, Long sourceMin, Long sourceMax){
        this.destinationMin = destinationMin;
        this.destinationMax = destinationMax; 
        this.sourceMin = sourceMin;
        this.sourceMax = sourceMax;
    }
    
}
