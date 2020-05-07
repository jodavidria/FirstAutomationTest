package Pages;

import Helpers.Helpers;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class PageLogin {

    private WebDriver driver;
    private By userField;
    private By passField;
    private By loginButton;
    private By titleText_1;
    private By titleText_2;
    private By flieds;

    public PageLogin(WebDriver driver) {

        this.driver = driver;
        userField = By.xpath("//input[@name='userName']");
        passField = By.name("password");
        loginButton = By.xpath("//input[@name='login']");
        titleText_1 = (By.xpath("//font[contains(text(),'Use our Flight Finder')]"));
        titleText_2 = (By.xpath("//font[contains(text(),'Enter your user information to access the member-o')]"));
        flieds = By.tagName("input");
    }

    public void Login(String user, String pass) {

        driver.findElement(userField).sendKeys(user);
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(loginButton).click();
        /*File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(myScreenshot, new File("LOGIN" + System.currentTimeMillis()+ ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }*/
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    public void verifyFields(){

        List<WebElement> loginFields = driver.findElements(flieds);
        System.out.println(loginFields.size());
        Assert.assertTrue(loginFields.size()==5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void successLogin(){
        Assert.assertTrue(driver.findElement(titleText_1).getText().contains("Use our Flight Finder to search"));
    }
    public void failedLogin(){
        Assert.assertTrue(driver.findElement(titleText_2).getText().contains("Welcome back to Mercury Tours!"));
    }

    /*public void failedLogin(String user, String pass){


        driver.findElement(userField).sendKeys(user);
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(loginButton).click();
        String expectedResult = "Sign-on: Mercury Tours";
        String currentResult = driver.getTitle();
        Assert.assertEquals(expectedResult,currentResult);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        /*Helpers helper = new Helpers();
        helper.sleepSeconds(3);*/




}
