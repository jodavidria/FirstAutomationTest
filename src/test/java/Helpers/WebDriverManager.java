package Helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    private WebDriver driver;

    public WebDriverManager(WebDriver driver){

        this.driver = driver;
    }

    public static void setWindowsSize(WebDriver driver, String size){

        if (size == "maximazed"){
            driver.manage().window().maximize();
        }
        if (size == "fullscreen"){
            driver.manage().window().fullscreen();
        }
    }

    public static void setWindowsSize(WebDriver driver, int x, int y){

        driver.manage().window().setSize(new Dimension(x,y));
        //driver.manage().window().setSize(new Dimension(800, 600));
    }
}
