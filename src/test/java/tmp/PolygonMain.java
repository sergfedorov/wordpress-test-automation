package tmp;

import java.io.IOException;

public class PolygonMain {

    public static void main(String args[]) throws IOException {
        String inputFilePath = "D:\\point.txt";
        String outputFilePath = "D:\\output.txt";

        PolygonV2.readFile(inputFilePath, outputFilePath);
    }

}
