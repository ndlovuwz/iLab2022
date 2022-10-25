package getDataApp;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
public class GetData {
    public String getTestData (String strColumn,int iRow,XSSFSheet sheet) {

        String sValue = null;

        XSSFRow row =sheet.getRow(0);
        for (int i = 0; i < columnCount(sheet); i++) {
            if(row.getCell(i).getStringCellValue().trim().equals(strColumn)){
                XSSFRow raw =sheet.getRow(iRow);
                XSSFCell cell = raw.getCell(i);
                DataFormatter formater = new DataFormatter();
                sValue=formater.formatCellValue(cell);
            }
        }
        return sValue;
    }
    public int columnCount(XSSFSheet sheet){
        return sheet.getRow(0).getLastCellNum();
    }
}
