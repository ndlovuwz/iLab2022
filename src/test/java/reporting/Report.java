package reporting;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import initializing.Initializing;
public class Report {
    private  Properties prt = new Properties();
    Initializing obj = new  Initializing();

    public ExtentReports reportScreenshot(WebDriver driver) throws IOException {
        prt=obj.loadData();
        String actualError =driver.findElement(By.xpath(prt.getProperty("lblErrorMessage"))).getText();
        ExtentReports extent=ExtentReport("./Reports/Report.html");
        ExtentTest test = ExtentTestMethod(extent);
        String screenshot= takeScreenshot(".//Screenshot//", driver);
        if(actualError.equals("Please complete this required field.")) {
            test.log(Status.PASS, actualError);
            test.pass(actualError);
            System.out.println("The actual error message: " + actualError);
            test.info("ScreenShort for Pass results",
                    MediaEntityBuilder.createScreenCaptureFromPath("."+screenshot).build());
        }
        else {
            test.log(Status.FAIL, actualError); test.fail(actualError);
        }
        return extent;
    }
    public  ExtentReports ExtentReport(String Report) {
        ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(Report);
        ExtentReports extent = new  ExtentReports();
        String css= ".r-img {width: 100%;}";
        htmlReport.config().setCSS(css);
        htmlReport.config().setTheme(Theme.STANDARD);
        extent.attachReporter(htmlReport);
        return extent;
    }
    public com.aventstack.extentreports.ExtentTest ExtentTestMethod(ExtentReports extent) {
        com.aventstack.extentreports.ExtentTest  test= extent.createTest("iLabTest");
        test.assignAuthor("Walter Ndlovu");
        return test;
    }
    public String takeScreenshot(String filename, WebDriver driver) throws IOException {
        File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHss").format(Calendar.getInstance().getTime());
        filename =filename+timeStamp+".png";
        FileUtils.copyFile(screenshot, new File(filename));
        return filename;
    }

}
