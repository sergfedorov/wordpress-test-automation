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
    public void wrongInputFilePath1() throws IOException {
        PolygonV3.readFile("D:\\testdata", "D:\\output.txt");
        Assert.assertEquals(baos.toString(), "ERROR: Wrong input fie path\r\n");
    }

    @Test
    public void wrongInputFilePath2() throws IOException {
        PolygonV3.readFile("D:\\testdata\\testdata.xls", "D:\\output.txt");
        Assert.assertEquals(baos.toString(), "ERROR: Input file must be .txt\r\n");
    }

    @Test
    public void wrongInputFilePath3() throws IOException {
        PolygonV3.readFile("", "D:\\output.txt");
        Assert.assertTrue(baos.toString().contains("ERROR: Input file not found:"));
    }

    @Test
    public void emptyInputFile() throws IOException {
        PolygonV3.readFile("D:\\testdata\\input-empty.txt", "D:\\output.txt");
        Assert.assertEquals(baos.toString(), "ERROR. Input file is empty\r\n");
    }

    @Test
    public void invalidInputFile() throws IOException {
        PolygonV3.readFile("D:\\testdata\\input-invalid-wrongcoordinate.txt", "D:\\output.txt");
        Assert.assertFalse(baos.toString().contains("Validation is PASSED"), "\r\nActual result: \r\n"+ baos.toString());
    }

    @Test(description = "X1:Y1")
    public void positiveOneCoordinatePair() throws IOException {
        PolygonV3.readFile("D:\\testdata\\input-valid-oneline-onecoordinatepair.txt", "D:\\output.txt");
        Assert.assertTrue(baos.toString().contains("Validation is PASSED"), "\r\nActual result: \r\n"+ baos.toString());
        Assert.assertTrue(baos.toString().contains("RESULT: This is a POINT"), "\r\nActual result: \r\n"+ baos.toString());
    }

    @Test(description = "X1:Y1,X2:Y2")
    public void positiveTwoCoordinatePairs() throws IOException {
        PolygonV3.readFile("D:\\testdata\\input-valid-oneline-twocoordinatepairs.txt", "D:\\output.txt");
        Assert.assertTrue(baos.toString().contains("Validation is PASSED"), "\r\nActual result: \r\n"+ baos.toString());
        Assert.assertTrue(baos.toString().contains("RESULT: This is a LINE"), "\r\nActual result: \r\n"+ baos.toString());
    }

    @Test(description = "X1:Y1,X1:Y1")
    public void negativeTwoCoordinatePairs() throws IOException {
        PolygonV3.readFile("D:\\testdata\\input-invalid-oneline-twocoordinatepairs.txt", "D:\\output.txt");
        Assert.assertTrue(baos.toString().contains("Validation is PASSED"), "\r\nActual result: \r\n"+ baos.toString());
        Assert.assertTrue(baos.toString().contains("RESULT: This is an INVALID LINE"), "\r\nActual result: \r\n"+ baos.toString());
    }

    @Test(description = "X1:Y1,X2:Y2,X3:Y3")
    public void positiveThreeCoordinatePairs() throws IOException {
        PolygonV3.readFile("D:\\testdata\\input-valid-oneline-threecoordinatepairs.txt", "D:\\output.txt");
        Assert.assertTrue(baos.toString().contains("Validation is PASSED"), "\r\nActual result: \r\n"+ baos.toString());
        Assert.assertTrue(baos.toString().contains("RESULT: This is a TRIANGLE"), "\r\nActual result: \r\n"+ baos.toString());
    }


    @Test(description = "X1:Y1,X2:Y2,X3:Y3 X1:Y1,X2:Y2,X3:Y3 X1:Y1,X2:Y2,X3:Y3 X1:Y1,X2:Y2,X3:Y3")
    public void negativeThreeCoordinatePairs() throws IOException {
        PolygonV3.readFile("D:\\testdata\\input-invalid-oneline-threecoordinatepairs.txt", "D:\\output.txt");
        Assert.assertFalse(baos.toString().contains("Validation is FAILED"), "\r\nActual result: \r\n"+ baos.toString());
        Assert.assertFalse(baos.toString().contains("RESULT: This is a TRIANGLE"), "\r\nActual result: \r\n"+ baos.toString());
    }

}
