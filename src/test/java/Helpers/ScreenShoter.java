package Helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.security.PrivateKey;

public class ScreenShoter {

    private  WebDriver  driver;

    public ScreenShoter(WebDriver driver){
        this.driver = driver;
    }

    public static void takeSceenshot(String screenName, WebDriver driver){

        File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(myScreenshot, new File(screenName + "_" + System.currentTimeMillis()+ ".png"));
        }catch (IOException E){
            E.printStackTrace();
        }

    }
}
