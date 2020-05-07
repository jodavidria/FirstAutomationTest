package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageReservation {

    private WebDriver driver;
    private By passengerDrop;
    private By departingDrop;
    private By arrivingDrop;

    public PageReservation(WebDriver driver){

        this.driver = driver;
        passengerDrop = By.xpath("//select[@name='passCount']");
        departingDrop = By.name("fromPort");
        //departingDrop = By.xpath("/select[@name='fromPort']");
        arrivingDrop = By.xpath("//select[@name='toPort']");

    }

    public void selectPassenger(String cantidad){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengerDrop));
        Select selectPasajeros = new Select(cantidadPasajeros);
        selectPasajeros.selectByVisibleText(cantidad);

    }

    public void selectDeparting(String city){

        Select selectDeparting = new Select(driver.findElement(departingDrop));
        selectDeparting.selectByValue(city);
    }

    public void selectArriving(String city){

        Select selectCity = new Select(driver.findElement(arrivingDrop));
        selectCity.selectByValue(city);

    }

}
