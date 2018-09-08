import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameStateMediatorTest {
    @Test
    void moveTest1(){ //mocks a player's move action with non-neighbouring regions
        ArrayList<UnitType> unitsInRegion = new ArrayList<>();
        ArrayList<UnitType> expectedUnits = new ArrayList<>();
        expectedUnits.add(UnitType.SOLDIER);
        unitsInRegion.add(UnitType.HORSE);
        Region t1 = new Region("T1");
        t1.setOwner(PlayerEnum.USER);
        Region t2 = new Region("T2");
        t2.setOwner(PlayerEnum.USER);
        t1.addUnits(unitsInRegion);
        GameStateMediator testMediator = new GameStateMediator();
        testMediator.move(t1,t2,5);
        assertEquals(expectedUnits, t2.getUnits());
    }
    @Test
    void moveTest2(){//mocks a player's move action with neighbouring regions
        ArrayList<UnitType> unitsInRegion = new ArrayList<>();
        ArrayList<UnitType> expectedUnits = new ArrayList<>();
        expectedUnits.add(UnitType.SOLDIER);
        expectedUnits.add(UnitType.HORSE);
        unitsInRegion.add(UnitType.HORSE);
        Region t1 = new Region("T1");
        t1.setOwner(PlayerEnum.USER);
        Region t2 = new Region("T2");
        t2.setOwner(PlayerEnum.USER);
        t1.addNeighbourRegions(new ArrayList<>(Arrays.asList(t2)));
        t1.addUnits(unitsInRegion);
        GameStateMediator testMediator = new GameStateMediator();
        testMediator.move(t1,t2,5);
        assertEquals(expectedUnits, t2.getUnits());
    }
    @Test
    void moveTest3(){//mocks a player's move action with neighbouring regions but there are not enough units in the origin region
        ArrayList<UnitType> unitsInRegion = new ArrayList<>();
        ArrayList<UnitType> expectedUnits = new ArrayList<>();
        expectedUnits.add(UnitType.SOLDIER);
        unitsInRegion.add(UnitType.HORSE);
        Region t1 = new Region("T1");
        t1.setOwner(PlayerEnum.USER);
        Region t2 = new Region("T2");
        t2.setOwner(PlayerEnum.USER);
        t1.addNeighbourRegions(new ArrayList<>(Arrays.asList(t2)));
        t1.addUnits(unitsInRegion);
        GameStateMediator testMediator = new GameStateMediator();
        testMediator.move(t1,t2,6);
        assertEquals(expectedUnits, t2.getUnits());
    }
    @Test
    void moveTest4(){//mocks a player's move action where both regions are not owned by the player
        ArrayList<UnitType> unitsInRegion = new ArrayList<>();
        ArrayList<UnitType> expectedUnits = new ArrayList<>();
        expectedUnits.add(UnitType.SOLDIER);
        unitsInRegion.add(UnitType.HORSE);
        Region t1 = new Region("T1");
        t1.setOwner(PlayerEnum.USER);
        Region t2 = new Region("T2");
        t2.setOwner(PlayerEnum.NEUTRAL);
        t1.addNeighbourRegions(new ArrayList<>(Arrays.asList(t2)));
        t1.addUnits(unitsInRegion);
        GameStateMediator testMediator = new GameStateMediator();
        testMediator.move(t1,t2,6);
        assertEquals(expectedUnits, t2.getUnits());
    }
    @Test
    void attackTest1(){//mocks a player's attack action with neighbouring regions
        Region t1 = new Region("T1");
        Region t2 = new Region("T2");
        t1.setOwner(PlayerEnum.USER);
        t2.setOwner(PlayerEnum.NEUTRAL);
        t1.addNeighbourRegions(new ArrayList<>(Arrays.asList(t2)));
        t2.addNeighbourRegions(new ArrayList<>(Arrays.asList(t1)));
        t1.addUnits(5);
        t2.addUnits(2);
        GameStateMediator mediator = new GameStateMediator();
        mediator.attack(t1, t2, 3);
        assertEquals(true, mediator.isSuccessful());
    }
    @Test
    void attackTest2(){ //mocks a player's attack with non neighbouring regions
        Region t1 = new Region("T1");
        Region t2 = new Region("T2");
        t1.setOwner(PlayerEnum.USER);
        t2.setOwner(PlayerEnum.NEUTRAL);
        t1.addUnits(5);
        t2.addUnits(2);
        GameStateMediator mediator = new GameStateMediator();
        mediator.attack(t1, t2, 3);
        assertEquals(false, mediator.isSuccessful()); }
}