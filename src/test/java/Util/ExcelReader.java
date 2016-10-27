package Util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] excelConverter() {

        String[][] sheetArray = null;

        try {

            FileInputStream fis = new FileInputStream("D:\\testdata.xls");
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheet("testdata");

            int numberOfColumns = sheet.getRow(0).getLastCellNum();
            int numberOfRows = sheet.getLastRowNum();

            sheetArray = new String[numberOfRows + 1][numberOfColumns];

            for (int rowCounter = 0; rowCounter <= numberOfRows; rowCounter++) {
                for (int columnCounter = 0; columnCounter <= numberOfColumns - 1; columnCounter++) {

                    HSSFRow row = sheet.getRow(rowCounter);

                    sheetArray[rowCounter][columnCounter] = row.getCell(columnCounter).toString();
                }
            }

            fis.close();

        } catch (IOException e) {
            System.out.println("Test data file not found");
        }

        return sheetArray;
    }
}
