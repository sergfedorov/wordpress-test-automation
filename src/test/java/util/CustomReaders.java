package util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CustomReaders {

    public static Object[][] excelConverter(String filePath, String sheetName) {

        String[][] sheetArray = null;

        try {

            FileInputStream fis = new FileInputStream(filePath);
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheet(sheetName);

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

    public enum BrowserType {
        FIREFOX("firefox"),
        CHROME("chrome");

        private String value;

        BrowserType(String value) {
            this.value = value;
        }

        public String getBrowserName() {
            return this.value;
        }
    }


    // Read the properties file and return enum BrowserType (firefox or chrome) from it
    public static BrowserType getBrowserTypeFromProperty() {
        BrowserType type = null;
        try {
            FileInputStream file = new FileInputStream("src\\test\\resources\\properties\\wordpress.properties");
            Properties properties = new Properties();
            properties.load(file);
            String browserName = properties.getProperty("browser");
            for (BrowserType bType : BrowserType.values()) {
                if (bType.getBrowserName().equalsIgnoreCase(browserName)) {
                    type = bType;
                    System.out.println("Browser is " + bType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }


}
