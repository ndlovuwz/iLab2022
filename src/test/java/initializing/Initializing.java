package initializing;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Initializing {
    private WebDriver driver;
    private  Properties prt = new Properties();
    public WebDriver initiate(String browserName,String url) throws IOException {
        System.out.println("Browser is running on :" + browserName);

        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("Firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver =new FirefoxDriver();
        }
        else {
            System.out.println("invalid browser");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        prt = loadData();
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
    }
    public Properties loadData() throws IOException {
        File path = new File("iLabApplication.properties");
        FileInputStream fis = new FileInputStream(path);
        prt.load(fis);
        return prt;
    }
    public void quitDriver(ExtentReports extent, WebDriver driver) {
        extent.flush();
        driver.close();
    }

}
