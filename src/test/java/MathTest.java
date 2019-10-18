import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import com.coveros.demo.helloworld;
public class MathTest {
    MyMath math;
    @Before
    public void setUp() throws Exception {
        math = new MyMath(7, 10);
    }
    @Test
    public void testAdd() {
        Assert.assertEquals(17, math.add());
    }
    
//     @Test
//     public void passTest() {
//         assertTrue( true );
//     }
}

