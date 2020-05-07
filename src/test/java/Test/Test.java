package Test;

import Helpers.Helpers;
import Helpers.ScreenShoter;
import Helpers.WebDriverManager;
import Pages.PageLogin;
import Pages.PageReservation;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Test {

    String baseUrl = "http://newtours.demoaut.com/";
    ArrayList<String> tabs;
    WebDriver driver;
    String chromPath = System.getProperty("user.dir") + "/drivers/chromedriver";
    //String path = "/Users/jonriano/Downloads/chromedriver";



    @BeforeMethod
    public void loadBrowser() {

        DesiredCapabilities caps = new DesiredCapabilities();
        System.setProperty("webdriver.chrome.driver", chromPath);
        driver = new ChromeDriver();
        //WebDriverManager.setWindowsSize(driver, "maximazed");
        driver.navigate().to(baseUrl);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        String googleWindow = "window.open('http://www.google.com')";
        javascriptExecutor.executeScript(googleWindow);
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



    }


    @org.testng.annotations.Test(priority = 1)
    public void failLogin(){

        WebDriverManager.setWindowsSize(driver, "maximazed");
        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.Login("ghj", "ghg");

    }

    @org.testng.annotations.Test(priority = 2)
    public void Login(){

        //WebDriverManager.setWindowsSize(driver, "fullscreen");
        driver.switchTo().window(tabs.get(1)).navigate().to("http://www.facebook.com");
        driver.switchTo().window(tabs.get(0));
        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.Login("mercury", "mercury");
        pageLogin.successLogin();

    }


    @org.testng.annotations.Test(priority = 3)
    public void bookReservation(){

        WebDriverManager.setWindowsSize(driver, 800, 600);
        PageLogin pageLogin = new PageLogin(driver);
        PageReservation pageReservation = new PageReservation(driver);
        pageLogin.Login("mercury", "mercury");
        pageReservation.selectPassenger("3");
        pageReservation.selectDeparting("London");
        pageReservation.selectArriving("Frankfurt");

    }

    @org.testng.annotations.Test(priority = 4)
    public void findFields(){

        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.verifyFields();

    }

    /*@AfterMethod
    public void goBackToHomePage() {
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
    }*/



    @AfterMethod
    public void closeBrowser(ITestResult result) {

        if (!result.isSuccess()){

            ScreenShoter.takeSceenshot("Error", driver);
        }
        driver.switchTo().window(tabs.get(1)).close();
        driver.switchTo().window(tabs.get(0)).close();
        //Helpers helper = new Helpers();
        //helper.sleepSeconds(4);
        //driver.quit();
        //driver.close();
    }
}