package com.coveros.demo.helloworld;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
public class MathTest {
    HelloWorld math;
    @Before
    public void setUp() throws Exception {
//         math = new HelloWorld();
         math = new HelloWorld(7, 10);
    }
    @Test
    public void testAdd() {
//         Assert.assertEquals(17, math.add(7 , 10));
        Assert.assertEquals(17, math.add());
    }
    
//     @Test
//     public void passTest() {
//         assertTrue( true );
//     }
}

// public class MathTest {
//     Math math;
//     @Before
//     public void setUp() throws Exception {
//         math = new Math(7, 10);
//     }
//     @Test
//     public void testAdd() {
//         Assert.assertEquals(17, math.add());
//     }
// }
