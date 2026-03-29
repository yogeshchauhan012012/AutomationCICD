package Datadetails;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;

import org.testng.annotations.DataProvider;

public class Studetdetails2 {
	
    @DataProvider(name="step2",parallel = true )
    public Object[][] datadetails(ITestContext context) throws IOException
    {
    	String browser = context.getCurrentXmlTest().getParameter("browser");


        int startRow = 0;
        int endRow = 18;

        if ("chrome".equalsIgnoreCase(browser)) {
            startRow = 1;
            endRow = 9;
        } else if ("firefox".equalsIgnoreCase(browser)) {
            startRow = 10;
            endRow = 14;
        } else if ("edge".equalsIgnoreCase(browser)) {
            startRow = 15;
            endRow = 18;
        }
    	
        FileInputStream fils= new FileInputStream(System.getProperty("user.dir")+"\\Exceldata\\demo.xlsx");
         XSSFWorkbook workbook = new XSSFWorkbook(fils);
         XSSFSheet sheet = workbook.getSheet("Sheet1");
         DataFormatter formatter = new DataFormatter();
        // int rowcount =sheet.getPhysicalNumberOfRows();
         XSSFRow row=sheet.getRow(0);
         int clcount= row.getLastCellNum();
         int dataSize = endRow - startRow + 1;
         Object data[][]= new Object[dataSize][clcount];
         for(int i=0 ;i<=(endRow - startRow);i++)
         {
             row=sheet.getRow(startRow+i);
             for(int j=0;j<clcount;j++)
             {
                 XSSFCell cell =row.getCell(j);
                 data[i][j]= formatter.formatCellValue(cell);
             }
         }
         workbook.close();
         fils.close();
        return data;
    }
}
