import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
public class MathTest {
    HelloWorld math;
    @Before
    public void setUp() throws Exception {
        math = new HelloWorld();
    }
    @Test
    public void testAdd() {
        Assert.assertEquals(17, math.add(7 , 10));
    }
    
//     @Test
//     public void passTest() {
//         assertTrue( true );
//     }
}

