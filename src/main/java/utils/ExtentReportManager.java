package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import freemarker.template.SimpleDate;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    public static String reportPath;

    public  static ExtentReports getReportInstance(){
        if(extent == null){
            String timestamp = new SimpleDateFormat("YYYY-MM-DD_HH-mm-ss").format(new Date());
            reportPath = "reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("test report");
            reporter.config().setReportName(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testname){
        test = getReportInstance().createTest(testname);
        return test;
    }

    public static String captureScreenshot(WebDriver driver,String screenshotName){
        try{
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
            System.out.println("path of ss : " + path);
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}

