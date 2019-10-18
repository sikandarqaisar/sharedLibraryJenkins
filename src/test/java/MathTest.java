package com.coveros.demo.helloworld;
// import org.junit.Assert;
// import org.junit.Before;
// import static org.junit.Assert.*;
// import org.junit.Test;
// public class MathTest {
//     HelloWorld math;
//     @Before
//     public void setUp() throws Exception {
//          math = new HelloWorld();

//     }
//     @Test
//     public void testAdd() {
//         Assert.assertEquals(17, math.add(7 , 10));
//     }
//         public void testMulti() {
//         Assert.assertEquals(15, math.multiple(5 , 3));
//     } 
    
    
// }
    

import org.junit.Test;
import static org.junit.Assert.*;

public class MyUnitTest {
    MyUnit myUnit;
    @Test
    public void testConcatenate() {
        myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }
}








    
    
//     @Test
//     public void passTest() {
//         assertTrue( true );
//     }


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
