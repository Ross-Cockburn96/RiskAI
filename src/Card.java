

public class Card {
    private String region;
    private UnitType unitType;
    private String continent;
    public Card(String region, UnitType unitType, String continent ){
        this.region = region;
        this.unitType = unitType;
        this.continent = continent;
    }
    public String getContinent(){return getContinent();}
    public String getRegionName(){
        return region;
    }
    public UnitType getUnitType(){
        return unitType;
    }
    @Override
    public String toString(){
        return("Card for Region: " + getRegionName() + " with unit type " + getUnitType());
    }
}

