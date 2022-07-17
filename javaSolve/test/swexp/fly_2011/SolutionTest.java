package swexp.fly_2011;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class SolutionTest {
    private static Solution solution;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Before
    public void initAll(){
        System.setOut(new PrintStream(outContent));
    }
    @After
    public void restoreStream(){
        System.setOut(originalOut);
    }
//    @Test
//    public void solutionTest() throws IOException {
//        String input = "1\n" +
//                "5 2";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        solution.main();
//        assertEquals(outContent.toString(),"52");
//
//    }
    @Test
    public void printTest() throws IOException {
        String input = "test";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Solution.main(new String[0]);
        assertEquals(outContent.toString(),"test");


    }

}