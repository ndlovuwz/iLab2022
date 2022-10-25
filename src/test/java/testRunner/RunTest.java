package testRunner;

import java.util.Properties;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.codoid.products.exception.FilloException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import iLabApplication.ApplicationForm;
import initializing.Initializing;
import reporting.Report;
public class RunTest {

    private WebDriver driver;
    private  Properties prt = new Properties();
    ExtentReports extent= null;
    //initiating objects
    Initializing myInitialize = new  Initializing();
    //GetData getDat = new GetData();
    ApplicationForm myForm = new ApplicationForm();
    Report myReport =new Report();

    @BeforeTest
    //@Parameters("browserName")
    @Parameters({"browserName","url"})
    public void seBaseURL(String browser, String URL) throws IOException {

        driver = myInitialize.initiate(browser,URL);
        prt=myInitialize.loadData();
    }
    @Test
    public void testApp() throws InterruptedException, IOException, FilloException {

        myForm.goToApply(driver);
        XSSFWorkbook workbook = new XSSFWorkbook("TestData//iLabData.xlsx");
        XSSFSheet sheetName = workbook.getSheet("Sheet1");

        int countRow= sheetName.getPhysicalNumberOfRows();
        for (int i = 1; i < countRow; i++) {
            myForm.clear(driver);
            myForm.Apply(sheetName, i, driver);
            extent=myReport.reportScreenshot(driver);
        }
    }
    @AfterTest
    public void sessionEnds() {
        //quit Driver;
        myInitialize.quitDriver(extent, driver);
    }

}
