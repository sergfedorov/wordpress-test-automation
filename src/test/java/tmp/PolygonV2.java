package luxtest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PolygonV2 {

    public static void main(String args[]) throws IOException {
        String inputFilePath = "D:\\point.txt";
        String outputFilePath = "D:\\output.txt";
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        readFile(inputFile, outputFile);
    }

    private static void readFile(File inFile, File outFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(outFile);
        FileInputStream fis = new FileInputStream(inFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        ArrayList<String> coordinatePairArrayFromLine;
        ArrayList<ArrayList<String>> coordinatePairArrayFromFile = new ArrayList<ArrayList<String>>();
        ArrayList<Integer> singleCoordinateArrayFromLine;
        ArrayList<ArrayList<Integer>> singleCoordinateArrayFromFile = new ArrayList<ArrayList<Integer>>();

        String line;
        int lineNumber = 1;

        while ((line = br.readLine()) != null) {

            System.out.println("Line #" + lineNumber + " is " + line);
            //Validation
            String regex = "([0-9]+:[0-9]+,){1,}[0-9]+:[0-9]+|[0-9]+:[0-9]+";
            if (line.matches(regex)) {
                System.out.println("--- Validation is PASSED");
            } else {
                System.out.println("--- Validation is FAILED. Moving to the next line.");
                lineNumber++;
                continue;
            }

            //System.out.

            String[] coordinatePair = line.split(",");

            int n = 0;
            coordinatePairArrayFromLine = new ArrayList<String>();
            singleCoordinateArrayFromLine = new ArrayList<Integer>();

            // Loop for each coordinate pair. coordinatePairArray contains all coordinate pairs from current file line
            for (int cp = 0; cp < coordinatePair.length; cp++) {
                coordinatePairArrayFromLine.add(coordinatePair[cp]);

                String[] singleCoordinate = coordinatePair[cp].split(":");

                // Loop for single coordinate. singleCoordinateArray contains all coordinates from current file line
                for (int sc = 0; sc < singleCoordinate.length; sc++) {
                    singleCoordinateArrayFromLine.add(n, Integer.parseInt(singleCoordinate[sc]));
                    n++;
                }

            }

            bw.write(String.valueOf(singleCoordinateArrayFromLine));

            singleCoordinateArrayFromFile.add(singleCoordinateArrayFromLine);
            coordinatePairArrayFromFile.add(coordinatePairArrayFromLine);

            //Checks
            whatTheFigureIs(singleCoordinateArrayFromLine);

            lineNumber++;

        }

        //System.out.println(singleCoordinateArrayFromFile);
        System.out.println(coordinatePairArrayFromFile);
        //sortAndPrintMultidimArrayList(coordinatePairArrayFromFile);

        br.close();
        bw.close();

    }

    public static void lineValidator(String line) {

    }

    public static void sortAndPrintMultidimArrayList(ArrayList list) {
        //sort by vertex number
        Collections.sort(list, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o1.size() - o2.size();
            }
        });
        //print the coordinates
        for (Object singleLine : list) {
            System.out.println(singleLine);
        }
    }

    public static void whatTheFigureIs(ArrayList list) {
        if (list.size() == 2) {
            System.out.println("--- RESULT: This is a POINT");
        } else if (list.size() == 4) {
            //line
            System.out.println(isLineCorrect(list));
        } else if (list.size() == 6) {
            //triangle
            System.out.println(isTriangleCorrectByCoordinates(list));
        } else if (list.size() == 8) {
            System.out.println("It is square - " + isItSquare(list));
            System.out.println("4angle");
        } else if (list.size() > 8) {
            //manyangle
        }
    }

    public static String isLineCorrect(ArrayList list) {
        if (list.get(0).equals(list.get(2)) && list.get(1).equals(list.get(3)))
            return "--- RESULT: This is an INVALID LINE. Both coordinate pairs are identical";
        else
            return "--- RESULT: This is a LINE";
    }

    public static String isTriangleCorrectByCoordinates(ArrayList<Integer> list) {
        double sideA = sqrt(pow((list.get(1) - list.get(0)), 2) + pow((list.get(3) - list.get(2)), 2));
        double sideB = sqrt(pow((list.get(3) - list.get(2)), 2) + pow((list.get(5) - list.get(4)), 2));
        double sideC = sqrt(pow((list.get(5) - list.get(4)), 2) + pow((list.get(1) - list.get(0)), 2));
        boolean res = ((sideA < sideB + sideC) && (sideA > sideB - sideC) && (sideB < sideA + sideC) && (sideB > sideA - sideC) && (sideC < sideA + sideB) && (sideC > sideA - sideB));
        if (res)
            return "--- RESULT: This is a TRIANGLE";
        else
            return "--- RESULT: This is an INVALID TRIANGLE. Please check coordinates";
    }

    public static boolean isItSquare(ArrayList<Integer> list) {
        double sideA = sqrt(pow((list.get(1) - list.get(0)), 2) + pow((list.get(3) - list.get(2)), 2));
        double sideB = sqrt(pow((list.get(3) - list.get(2)), 2) + pow((list.get(5) - list.get(4)), 2));
        double sideC = sqrt(pow((list.get(5) - list.get(4)), 2) + pow((list.get(7) - list.get(6)), 2));
        double sideD = sqrt(pow((list.get(7) - list.get(6)), 2) + pow((list.get(1) - list.get(0)), 2));
        return (sideA == sideB && sideB == sideC && sideC == sideD && sideD == sideA);
    }
}
