package test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.ingegnerizzazione.ConnectionClose;
import logic.model.Connector;


public class SeleniumTest1 {

  @Test
  public void medictoryTest1() {
	System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
	WebDriver driver =new ChromeDriver();
	driver.get("http://localhost:8080/Medictory/");
	driver.manage().window().setSize(new Dimension(697, 728));
    driver.findElement(By.id("signIn")).click();
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).sendKeys("francesca");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("francesca");
    driver.findElement(By.name("login")).click();
    driver.findElement(By.cssSelector(".hexagon-item:nth-child(3) .title")).click();
    
    WebElement ele = driver.findElement(By.xpath("/html/body/main/div/form/span/button"));
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("arguments[0].click()", ele);
	
	 ConnectionClose.closeConn( Connector.getConnectorInstance().getConnection()); // chiudo la connessione 
	
    assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Non hai inserito tutti i parametri");
  }
}
