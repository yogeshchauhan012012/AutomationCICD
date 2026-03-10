package Datadetails;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Studetdetails {
	
    @DataProvider(name="step1")
    public Object[][] datadetails() throws IOException
    {
     // String sheetname= "Login";
    	DataFormatter formatter = new DataFormatter();
        FileInputStream fils= new FileInputStream(System.getProperty("user.dir")+"\\Exceldata\\VG_UP student details.xlsx");
         XSSFWorkbook workbook = new XSSFWorkbook(fils);
         XSSFSheet sheet = workbook.getSheet("Sheet2");
         int rowcount =sheet.getPhysicalNumberOfRows();
         XSSFRow row=sheet.getRow(0);
         int clcount= row.getLastCellNum();
         Object data [][]= new Object[rowcount-1][clcount];
         for(int i=0;i<rowcount-1;i++)
         {
             row=sheet.getRow(i+1);
             for(int j=0;j<clcount;j++)
             {
                 XSSFCell cell =row.getCell(j);
                 data[i][j]= formatter.formatCellValue(cell);
             }
         }
        return data;
    }
}
