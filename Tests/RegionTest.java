import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RegionTest {
    @Test
    void addNeighbourTest(){
        Region regionTest = new Region("test");
        ArrayList<Region> neighboursToAdd = new ArrayList<>();
        neighboursToAdd.add(new Region("neighbour1"));
        neighboursToAdd.add(new Region("neighbour2"));
        regionTest.addNeighbourRegions(neighboursToAdd);
        assertEquals(regionTest.getNeighbours().get(0).toString(),"neighbour1");
        assertEquals(2, regionTest.getNeighbours().size());
    }
    @Test
    void observerRegisterTest(){
        MockObserver observerTest = new MockObserver();
        Region regionTest = new Region("Test");
        regionTest.register(observerTest);
        assertEquals(observerTest.getUpdated(), false);
        regionTest.setOwner(PlayerEnum.USER);
        assertEquals(true, observerTest.getUpdated());
    }
    @Test
    void setOwnerTest(){
        Region regionTest = new Region("Test");
        regionTest.setOwner(PlayerEnum.USER);
        assertEquals(PlayerEnum.USER, regionTest.getOwner());
    }
    @Test
    void addUnitsTest(){
        Region regionTest = new Region("Test");
        ArrayList<UnitType> unitsToAdd = new ArrayList<>();
        unitsToAdd.add(UnitType.SOLDIER);
        unitsToAdd.add(UnitType.HORSE);
        unitsToAdd.add(UnitType.CANON);
        ArrayList<UnitType> expectedUnitsInRegion = new ArrayList<>(unitsToAdd);
        expectedUnitsInRegion.add(0,UnitType.SOLDIER);//regions are constructed with a soldier unit in them
        regionTest.addUnits(unitsToAdd);
        assertEquals(expectedUnitsInRegion, regionTest.getUnits());
    }
    @Test
    void removeUnitsTest1(){ //tests adding a unit then removing the same unit e.g adding Horse then removing Horse
        Region regionTest = new Region ("Test");
        ArrayList<UnitType> unitsToAdd = new ArrayList<>();
        ArrayList<UnitType> unitsToRemove = new ArrayList<>();
        ArrayList<UnitType> expectedUnits = new ArrayList<>();
        expectedUnits.add(UnitType.SOLDIER);
        unitsToAdd.add(UnitType.HORSE);
        unitsToRemove.add(UnitType.HORSE);
        regionTest.addUnits(unitsToAdd);
        regionTest.removeUnits(unitsToRemove);
        assertEquals(expectedUnits, regionTest.getUnits());
    }
    @Test
    void removeUnitsTest2(){ //tests adding a unit then removing a lower value unit e.g adding a horse then removing 2 soldiers
        Region regionTest = new Region ("Test");
        ArrayList<UnitType> unitsToAdd = new ArrayList<>();
        ArrayList<UnitType> unitsToRemove = new ArrayList<>();
        ArrayList<UnitType> expectedUnits = new ArrayList<>();
        expectedUnits.add(UnitType.SOLDIER);
        expectedUnits.add(UnitType.SOLDIER);
        unitsToAdd.add(UnitType.HORSE);
        unitsToAdd.add(UnitType.SOLDIER);
        unitsToAdd.add(UnitType.CANON);
        unitsToRemove.add(UnitType.HORSE);
        unitsToRemove.add(UnitType.HORSE);
        unitsToRemove.add(UnitType.HORSE);
        regionTest.addUnits(unitsToAdd);
        regionTest.removeUnits(unitsToRemove);

        assertEquals(expectedUnits, regionTest.getUnits());
    }



}