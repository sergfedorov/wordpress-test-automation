package tmp;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PolygonV3 {

    public static void main(String args[]) throws IOException {

        //D:\emptyfile.txt
        //D:\point1.txt

        PolygonV3.readFile("D:\\emptyfile.txt", "D:\\output.txt");
    }

    public static void readFile(String in, String out) throws IOException {
        File inputFile = new File(in);
        File outputFile = new File(out);

        if (inputFile.isDirectory()) {
            System.out.println("not a file");
            return;
        } else if (!inputFile.exists()) {
            System.out.println("no such file");
            return;
        } else if (!Files.probeContentType(inputFile.toPath()).equals("text/plain")) {
            System.out.println("it is not a txt file");
            return;
        } else if (inputFile.length() == 0) {
            System.out.println("File is empty");
            return;
        }

        FileInputStream fis = new FileInputStream(inputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        FileOutputStream fos = new FileOutputStream(outputFile);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        ArrayList<String> coordinatePairArrayFromLine;
        ArrayList<ArrayList<String>> coordinatePairArrayFromFile = new ArrayList<ArrayList<String>>();
        ArrayList<Integer> singleCoordinateArrayFromLine;
        ArrayList<ArrayList<Integer>> singleCoordinateArrayFromFile = new ArrayList<ArrayList<Integer>>();

        String line;
        int lineNumber = 1;

        while ((line = br.readLine()) != null) {

            writeOutputToConsoleAndFile("Line #" + lineNumber + " is " + line, bw);

            //Validation
            String regex = "([0-9]+:[0-9]+,){1,}[0-9]+:[0-9]+|[0-9]+:[0-9]+";
            if (line.matches(regex)) {
                writeOutputToConsoleAndFile("--- Validation is PASSED", bw);
            } else {
                writeOutputToConsoleAndFile("--- Validation is FAILED. Moving to the next line.", bw);
                lineNumber++;
                continue;
            }

            String[] coordinatePair = line.split(",");

            int n = 0;
            coordinatePairArrayFromLine = new ArrayList<String>();
            singleCoordinateArrayFromLine = new ArrayList<Integer>();

            // Loop for each coordinate pair. coordinatePairArray contains all coordinate pairs from current file line
            for (String aCoordinatePair : coordinatePair) {
                coordinatePairArrayFromLine.add(aCoordinatePair);

                String[] singleCoordinate = aCoordinatePair.split(":");

                // Loop for single coordinate. singleCoordinateArray contains all coordinates from current file line
                for (String aSingleCoordinate : singleCoordinate) {
                    singleCoordinateArrayFromLine.add(n, Integer.parseInt(aSingleCoordinate));
                    n++;
                }

            }

            singleCoordinateArrayFromFile.add(singleCoordinateArrayFromLine);
            coordinatePairArrayFromFile.add(coordinatePairArrayFromLine);

            //Checks
            writeOutputToConsoleAndFile(whatTheFigureIs(singleCoordinateArrayFromLine), bw);

            lineNumber++;
        }


        //System.out.println(singleCoordinateArrayFromFile);
        //System.out.println(coordinatePairArrayFromFile);
        //sortAndPrintMultidimArrayList(coordinatePairArrayFromFile);

        br.close();
        bw.close();

        System.out.println("It was the last line");
    }

    /*public static File fileValidator(File fileToValidate) throws IOException {
        File file = null;
        while (fileToValidate.isDirectory() || !fileToValidate.exists() ||
                !Files.probeContentType(fileToValidate.toPath()).equals("text/plain")) {
            if (fileToValidate.isDirectory()) {
                System.out.println("not a file");
            } else if (!fileToValidate.exists()) {
                System.out.println("no such file");
            } else if (!Files.probeContentType(fileToValidate.toPath()).equals("text/plain")) {
                System.out.println("it is not a txt file");
            }
            Scanner scan = new Scanner(System.in);
            String filePathByUser = scan.nextLine();
            file = new File(filePathByUser);
        }
        return file;
    }*/

    public static void writeOutputToConsoleAndFile(String outLine, BufferedWriter bufferedWriter) throws IOException {
        System.out.println(outLine);
        bufferedWriter.write(outLine);
        bufferedWriter.newLine();
    }

    /*public static void sortAndPrintMultidimArrayList(ArrayList list) {
        //sort by vertex number
        Collections.sort(list, new Comparator<ArrayList<String>>() {
            //@Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o1.size() - o2.size();
            }
        });
        //print the coordinates
        for (Object singleLine : list) {
            System.out.println(singleLine);
        }
    }*/

    public static String whatTheFigureIs(ArrayList<Integer> list) {
        if (list.size() == 2)
            return "--- RESULT: This is a POINT";
        else if (list.size() == 4)
            //line
            return isLineCorrect(list);
        else if (list.size() == 6)
            //triangle
            return isTriangleCorrectByCoordinates(list);
        else if (list.size() == 8)
            return isItSquare(list);
        else if (list.size() > 8)
            return "Figure has mote than 4 angles";
        return "Hm...";
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

    public static String isItSquare(ArrayList<Integer> list) {
        double sideA = sqrt(pow((list.get(1) - list.get(0)), 2) + pow((list.get(3) - list.get(2)), 2));
        double sideB = sqrt(pow((list.get(3) - list.get(2)), 2) + pow((list.get(5) - list.get(4)), 2));
        double sideC = sqrt(pow((list.get(5) - list.get(4)), 2) + pow((list.get(7) - list.get(6)), 2));
        double sideD = sqrt(pow((list.get(7) - list.get(6)), 2) + pow((list.get(1) - list.get(0)), 2));
        boolean res = (sideA == sideB && sideB == sideC && sideC == sideD && sideD == sideA);
        if (res)
            return "--- RESULT: This is a SQUARE";
        else
            return "--- RESULT: This is NOT SQUARE. Please check coordinates";
    }
}
