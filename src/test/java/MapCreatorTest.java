import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

public class MapCreatorTest {
    private MapCreator creator;

    @Before
    public void setup() {
        creator = new MapCreator();
    }

    @After
    public void teardown() {
        creator = null;
    }

    //Testing createMap

    @Test
    public void TestCreateMap_testingSafeMap_shouldReturnSafeMap(){
        Assert.assertTrue(creator.createMap("safe",5) instanceof SafeMap);
    }

    @Test
    public void TestCreateMap_testingHazardousMap_shouldReturnHazardousMap(){
        Assert.assertTrue(creator.createMap("hazardous",5) instanceof HazardousMap);
    }

    @Test
    public void TestCreateMap_testingSafeMap_shouldReturnNull(){
        Assert.assertNull(creator.createMap("invalidtype",5));
    }
}
