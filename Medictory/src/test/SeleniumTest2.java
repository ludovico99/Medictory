package test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.ingegnerizzazione.ConnectionClose;
import logic.model.Connector;



public class SeleniumTest2 {
	
	private static final String ACTION = "arguments[0].click()";
	 private JavascriptExecutor jse;
	 private WebElement ele;

@Test
public void medictoryTest2() {
	System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
	WebDriver driver =new ChromeDriver();
	driver.get("http://localhost:8080/Medictory/");
	driver.manage().window().setSize(new Dimension(697, 728));
	
	 jse = (JavascriptExecutor)driver;
	
	 driver.findElement(By.id("signIn")).click();
	 click(driver, "username", "myfarma");
	
	 click(driver, "password", "myfarma");
	 
	 driver.findElement(By.name("login")).click();
	 driver.findElement(By.cssSelector(".hexagon-item:nth-child(3) .hex-content-inner")).click();
	
	 ele = driver.findElement(By.name("nome"));
	 jse.executeScript(ACTION, ele);
	 driver.findElement(By.name("nome")).sendKeys("Nuovo Evento");
	 
	 
	 ele = driver.findElement(By.name("dettagli"));
	 jse.executeScript(ACTION, ele);
	 driver.findElement(By.name("dettagli")).sendKeys("ok");

	 ele = driver.findElement(By.name("livello"));
	 jse.executeScript(ACTION, ele);
	 driver.findElement(By.name("livello")).sendKeys("1");
	 

	 ele = driver.findElement(By.id("Premio"));
	 jse.executeScript(ACTION, ele);
	 
	  WebElement dropdown = driver.findElement(By.id("Premio"));
	  dropdown.findElement(By.xpath("//option[. = 'Sconto 50%']")).click();
	 

	 ele = driver.findElement(By.name("data inizio"));
	 jse.executeScript(ACTION, ele);
	 driver.findElement(By.name("data inizio")).sendKeys("05/07/2021");
	 
	 ele = driver.findElement(By.name("data fine"));
	 jse.executeScript(ACTION, ele);
	 driver.findElement(By.name("data fine")).sendKeys("06/07/2021");
	 
	 ele = driver.findElement(By.name("crea"));
	 jse.executeScript(ACTION, ele);
	 
	 ConnectionClose.closeConn( Connector.getConnectorInstance().getConnection()); // chiudo la connessione 
	 
	 assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Non puoi inserire un evento iniziato");
	}

	private void click(WebDriver driver, String name, String str) {
	    ele = driver.findElement(By.name(name));
	    jse.executeScript(ACTION, ele);
	    driver.findElement(By.name(name)).sendKeys(str);
}
}

