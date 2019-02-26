import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.HashMap;

public class ContinentTest {
    @Test
    void updateTest1(){ //test case where regions are not owned by same user and mocks an update call from a subject
        HashMap<String, Region> testRegions = new HashMap<>();
        Region t1 = new Region("T1");
        Region t2 = new Region("T2");
        t1.setOwner(PlayerEnum.USER);
        t2.setOwner(PlayerEnum.NEUTRAL);
        testRegions.put("T1",t1);
        testRegions.put("T2",t2);
        Continent testCont = new Continent(testRegions, 0);
        testCont.update(testRegions.get("T1"));
        assertEquals(null, testCont.getOwner());
    }
    @Test
    void updateTest2(){ //test case where regions are owned by the same user and mocks an update call from a subjec
        HashMap<String, Region> testRegions = new HashMap<>();
        Region t1 = new Region("T1");
        Region t2 = new Region("T2");
        t1.setOwner(PlayerEnum.USER);
        t2.setOwner(PlayerEnum.USER);
        testRegions.put("T1",t1);
        testRegions.put("T2",t2);
        Continent testCont = new Continent(testRegions, 0);
        testCont.update(testRegions.get("T1"));
        assertEquals(PlayerEnum.USER, testCont.getOwner());
    }
}
