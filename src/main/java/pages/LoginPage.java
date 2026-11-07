package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
private WebDriver driver;

    @FindBy(name = "username")
    private WebElement emailInput;

    @FindBy(css = "input#password")
    private WebElement passwordInput;

    @FindBy(css = "button#submit")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterCredentials(String email, String password){
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public String loginTitle(){
        return driver.getTitle();
    }

}
