

public class Card {
    private String region;
    private UnitType unitType;  
    public Card(String region, UnitType unitType ){
        this.region = region;
        this.unitType = unitType;
    }
    public String getRegion(){
        return region;
    }
    public UnitType getUnitType(){
        return unitType;
    }
    @Override
    public String toString(){
        return("Card for Region: " + getRegion() + " with unit type " + getUnitType());
    }
}

