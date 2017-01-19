package tmp;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.util.Scanner;

public class PolygonTest {

    ByteArrayOutputStream baos;
    PrintStream old;

    @BeforeMethod
    public void myStream() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        old = System.out;
    }

    @AfterMethod
    public void closeMyStream() throws IOException {
        System.out.flush();
        System.setOut(old);
        //baos = null;
    }

    @Test
    public void test1() throws IOException {

        PolygonV3.readFile("D:\\point1.txt", "D:\\output.txt");
        Assert.assertEquals(baos.toString(), "Line #1 is qwe\r\n--- Validation is FAILED. Moving to the next line.\r\nIt was the last line\r\n");

    }

    @Test
    public void test2() throws IOException {

        PolygonV3.readFile("D:\\emptyfile.txt", "D:\\output.txt");
        Assert.assertEquals(baos.toString(), "File is empty\r\n");

    }


}
