package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportManager;
import utils.Log;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport(){
        extent = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void teardownreport(){
        extent.flush();
        String reportPath = ExtentReportManager.reportPath;
        test.info(reportPath);
    }

    @BeforeMethod
    public void launchbrowser() {
        Log.info("navigating to url");
        driver = new ChromeDriver();
        Log.info("maximizing driver");
        driver.manage().window().maximize();
        Log.info("navigating to url");
        driver.get("https://practicetestautomation.com/practice-test-login/");
        Log.info("waiting if url does not open");
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
        Log.info("url is working");
    }
    @AfterMethod
    public void teardown(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure");
            test.fail("test failed .. check Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
        Log.info(("closing chrome"));
        if(driver!=null){
            driver.quit();
        }
    }
}
