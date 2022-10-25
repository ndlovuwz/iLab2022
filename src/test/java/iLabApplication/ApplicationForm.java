package iLabApplication;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import populatePhoneNumber.PopulatePhoneNumber;
import getDataApp.GetData;
import initializing.Initializing;

public class ApplicationForm {
    Initializing init = new Initializing();
    GetData getData = new GetData();
    Properties prt = new Properties();
    PopulatePhoneNumber number = new PopulatePhoneNumber();

    public void  goToApply(WebDriver driver) throws IOException, InterruptedException {
        prt =init.loadData();
        JavascriptExecutor js = (JavascriptExecutor)  driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        driver.findElement(By.xpath(prt.getProperty("lblCareer"))).click();
        driver.findElement(By.xpath(prt.getProperty("btnCountry"))).click();
        driver.findElement(By.xpath(prt.getProperty("lnkPost"))).click();
    }
    public void Apply(XSSFSheet s1,int i,WebDriver driver) throws IOException {
        String firstname = getData.getTestData("firstname", i, s1);
        String lastname = getData.getTestData("lastname", i, s1);
        String email = getData.getTestData("email", i, s1);
        String phoneNumber =number.generatePhoneNumber();
        prt =init.loadData();
        driver.findElement(By.xpath(prt.getProperty("txtFirstname"))).sendKeys(firstname);
        System.out.println("Data set: " + firstname);
        driver.findElement(By.xpath(prt.getProperty("txtLastname"))).sendKeys(lastname);
        System.out.println("Data set: " + lastname);
        driver.findElement(By.xpath(prt.getProperty("txtEmail"))).sendKeys(email);
        System.out.println("Data set: " + email);
        driver.findElement(By.xpath( prt.getProperty("txtPhone"))).sendKeys(phoneNumber);
        System.out.println("Data set: " + phoneNumber);
        driver.findElement(By.xpath(prt.getProperty("btnSubmit"))).click();
    }
    public void clear(WebDriver driver) throws IOException {

        prt =init.loadData();
        driver.switchTo().frame(0);
        driver.findElement(By.xpath(prt.getProperty("txtFirstname"))).clear();
        driver.findElement(By.xpath(prt.getProperty("txtEmail"))).clear();
        driver.findElement(By.xpath(prt.getProperty("txtPhone"))).clear();
    }

}
