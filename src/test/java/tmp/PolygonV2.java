package tmp;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PolygonV2 {

    public static void main(String args[]) throws IOException {
        File file = new File("D:\\point.txt");
        readFile(file);
    }

    private static void readFile(File fin) throws IOException {

        String FILENAME = "D:\\output.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));

        FileInputStream fis1 = new FileInputStream(fin);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));

        int lineNumber = 1;
        String line;

        ArrayList<ArrayList<Integer>> myList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row;

        ArrayList<ArrayList<String>> lineList = new ArrayList<ArrayList<String>>();
        ArrayList<String> lineRow;

        while ((line = br1.readLine()) != null) {

            System.out.println("Coordinate number: " + lineNumber);
            //validation
            String regex = "([0-9]+:[0-9]+,){1,}[0-9]+:[0-9]+|[0-9]+:[0-9]+";
            if (line.matches(regex)) {
                System.out.println("valid coordinates");
            } else {
                System.out.println("invalid coordinates. Please use the pattern: for one coordinate: X1:Y1 or for two or more coordinates: X1:Y1,X2:Y2,X3:Y3");
                System.out.println(line);
                lineNumber++;
                continue;
            }

            String[] ln = line.split(",");

            int t = 0;
            lineRow = new ArrayList<String>();
            row = new ArrayList<Integer>();

            for (int stolb = 0; stolb < ln.length; stolb++) {
                lineRow.add(ln[stolb]);
                String[] ln1 = ln[stolb].split(":");

                for (int ss = 0; ss < ln1.length; ss++) {

                    row.add(t, Integer.parseInt(ln1[ss]));
                    t++;
                }

            }

            bw.write(String.valueOf(row));

            myList.add(row);
            lineList.add(lineRow);

            //check each line from the file
            whatTheFigureIs(row);

            lineNumber++;

        }

        System.out.println(myList);
        System.out.println(lineList);
        sortAndPrintMultidimArrayList(lineList);

        br1.close();
        bw.close();
    }

    public static void lineValidator(String line){

    }

    public static void sortAndPrintMultidimArrayList(ArrayList list){
        //sort by vertex number
        Collections.sort(list, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o1.size() - o2.size();
            }
        });
        //print the coordinates
        for(Object singleLine : list){
            System.out.println(singleLine);
        }
    }

    public static void whatTheFigureIs(ArrayList list){
        if(list.size() == 2){
            System.out.println("Point");
        } else if(list.size() == 4){
            //line
            isLineCorrect(list);
        } else if(list.size() == 6){
            //triangle
            System.out.println("Triangle is good - " + isTriangleCorrectByCoordinates(list));
        } else if(list.size() == 8){
            System.out.println("It is square - " + isItSquare(list));
            System.out.println("4angle");
        } else if(list.size() > 8){
            //manyangle
        }
    }

    public static void isLineCorrect(ArrayList list){
        if (list.get(0).equals(list.get(2)) && list.get(1).equals(list.get(3)))
            System.out.println("Invalid line");
        else
            System.out.println("Valid line");
    }

    public static boolean isTriangleCorrectByCoordinates(ArrayList<Integer> list) {
        double sideA = sqrt(pow((list.get(1) - list.get(0)), 2) + pow((list.get(3) - list.get(2)), 2));
        double sideB = sqrt(pow((list.get(3) - list.get(2)), 2) + pow((list.get(5) - list.get(4)), 2));
        double sideC = sqrt(pow((list.get(5) - list.get(4)), 2) + pow((list.get(1) - list.get(0)), 2));
        return ((sideA < sideB + sideC) && (sideA > sideB - sideC) && (sideB < sideA + sideC) && (sideB > sideA - sideC) && (sideC < sideA + sideB) && (sideC > sideA - sideB));

    }

    public static boolean isItSquare(ArrayList<Integer> list) {
        double sideA = sqrt(pow((list.get(1) - list.get(0)), 2) + pow((list.get(3) - list.get(2)), 2));
        double sideB = sqrt(pow((list.get(3) - list.get(2)), 2) + pow((list.get(5) - list.get(4)), 2));
        double sideC = sqrt(pow((list.get(5) - list.get(4)), 2) + pow((list.get(7) - list.get(6)), 2));
        double sideD = sqrt(pow((list.get(7) - list.get(6)), 2) + pow((list.get(1) - list.get(0)), 2));
        return (sideA == sideB && sideB == sideC && sideC == sideD && sideD == sideA);
    }
}
