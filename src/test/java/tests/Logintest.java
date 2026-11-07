package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class Logintest extends BaseTest {

    @Test
    public void validLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials("student","Password123");
        Assert.assertEquals(loginPage.loginTitle(),"Logged In Successfully | Practice Test Automation");
    }
}
