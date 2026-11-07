package tests;

import base.BaseTest;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

public class Logintest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\testData.xlsx";
        Log.info(filePath);
        ExcelUtils.loadExcel(filePath,"sheet1");
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount-1][2];
        for(int i=1;i<rowCount;i++){
            data[i-1][0] = ExcelUtils.getCellData(i,0);
            data[i-1][1] = ExcelUtils.getCellData(i,1);
        }
        ExcelUtils.closeExcel();
        return data;
    }



    @Test(dataProvider = "loginData")
    public void validLogin(String name,String pswd){
        test = ExtentReportManager.createTest("LoginTest ");
        test.info("navigating to url");
        LoginPage loginPage = new LoginPage(driver);
        Log.info("entering valid username and password");
        loginPage.enterCredentials(name,pswd);
        Assert.assertEquals(loginPage.loginTitle(),"Logged In Successfully | Practice Test Automation");
        test.pass("login successful");
    }
//
//    @Test
//    public void invalidLogin(){
//        test = ExtentReportManager.createTest("failure test ");
//        test.info("navigating to url");
//        LoginPage loginPage = new LoginPage(driver);
//        Log.info("entering invalid username and password");
//        loginPage.enterCredentials("student","Password");
//        Assert.assertEquals(loginPage.loginTitle(),"Logged In Successfully | Practice Test Automation");
//        Log.error("failed");
//        test.fail("login fail");
//    }


//    @DataProvider(name = "loginData")
//    public Object[][] getloginData() {
//        return new Object[][]{
//                {"student", "password123"},
//                {"student2", "password"}
//        };
//    }
//    @Test(dataProvider = "loginData")
//        public void dataProviderTest(String name, String value) {
//        test = ExtentReportManager.createTest("parameter Test");
//        test.info("navigating to url");
//        LoginPage loginPage = new LoginPage(driver);
//        Log.info("entering valid username and password");
//        loginPage.enterCredentials(name, value);
//        Assert.assertEquals(loginPage.loginTitle(), "Logged In Successfully | Practice Test Automation");
//        test.pass("login successful");

//    }
//    @Test
//    @Parameters({"username","password"})
//    public void parameterTest(String name, String value){
//        test = ExtentReportManager.createTest("parameter Test");
//        test.info("navigating to url");
//        LoginPage loginPage = new LoginPage(driver);
//        Log.info("entering valid username and password");
//        loginPage.enterCredentials(name,value);
//        Assert.assertEquals(loginPage.loginTitle(),"Logged In Successfully | Practice Test Automation");
//        test.pass("login successful");
//
//    }


}
