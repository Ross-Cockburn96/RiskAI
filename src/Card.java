public class Card {
    private String region;
    private String unitType;
    public Card(String region, String unitType ){
        this.region = region;
        this.unitType = unitType;
    }
    public String getRegion(){
        return region;
    }
    public String getUnitType(){
        return unitType;
    }
}

