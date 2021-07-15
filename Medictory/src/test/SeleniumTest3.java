package test;

 

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.ingegnerizzazione.ConnectionClose;
import logic.model.Connector;
 

 

public class SeleniumTest3 {
    private static final String ACTION = "arguments[0].click()";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private JavascriptExecutor jse;
    private WebElement ele;
 

 

    @Test
    public void medictoryTest3() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        WebDriver driver =new ChromeDriver();
        driver.get("http://localhost:8080/Medictory/");
        driver.manage().window().setSize(new Dimension(697, 728));
        jse = (JavascriptExecutor) driver;
        
        
        //login
        driver.findElement(By.id("signInCustomer")).click();
        driver.findElement(By.name(USERNAME)).click();
        driver.findElement(By.name(USERNAME)).sendKeys("elisa");
        driver.findElement(By.name(PASSWORD)).click();
        driver.findElement(By.name(PASSWORD)).sendKeys("elisa");
        driver.findElement(By.name("login")).click();
        
        //click su risorse
        driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/div[2]/div[1]/a")).click();
        
        //compila la form
        click(driver, "//*[@id=\"contact-form\"]/input[1]", "aaa");
        click(driver, "//*[@id=\"contact-form\"]/input[2]", "aaa");
        click(driver, "//*[@id=\"contact-form\"]/input[3]", "1");
        click(driver, "//*[@id=\"contact-form\"]/input[4]", "01/08/2025");
    
        
        ele = driver.findElement(By.xpath("//*[@id=\"contact-form\"]/input[5]"));
        jse.executeScript(ACTION, ele);
        
        //torno indetro
        ele = driver.findElement(By.xpath("/html/body/main/div/div/a"));
        jse.executeScript(ACTION, ele);
        
        //vado su account e faccio il logout
        ele = driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/div[2]/div[4]/a"));
        jse.executeScript(ACTION, ele);
        ele = driver.findElement(By.xpath("//*[@id=\"contact-form\"]/input"));
        jse.executeScript(ACTION, ele);
        
        //login di nuovo
        ele = driver.findElement(By.id("signInCustomer"));
        jse.executeScript(ACTION, ele);
        ele = driver.findElement(By.name(USERNAME));
        jse.executeScript(ACTION, ele);
        driver.findElement(By.name(USERNAME)).sendKeys("elisa");
        ele = driver.findElement(By.name(PASSWORD));
        jse.executeScript(ACTION, ele);
        driver.findElement(By.name(PASSWORD)).sendKeys("elisa");
        ele = driver.findElement(By.name("login"));
        jse.executeScript(ACTION, ele);
        
        //risorse di nuovo
        driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/div[2]/div[1]/a")).click();
        
        ele = driver.findElement(By.xpath("/html/body/main/div/div/div/table/tbody/tr[2]/td[1]"));
        String name = ele.getText();
        ele = driver.findElement(By.xpath("/html/body/main/div/div/div/table/tbody/tr[2]/td[2]"));
        String des = ele.getText();
        ele = driver.findElement(By.xpath("/html/body/main/div/div/div/table/tbody/tr[2]/td[3]"));
        String qta = ele.getText();
        ele = driver.findElement(By.xpath("/html/body/main/div/div/div/table/tbody/tr[2]/td[4]"));
        String scad = ele.getText();
        
        ArrayList<String> actual = new ArrayList<>();
        actual.add(name);
        actual.add(des);
        actual.add(qta);
        actual.add(scad);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("aaa");
        expected.add("aaa");
        expected.add("1");
        expected.add("2025-08-01");
        
      
       ConnectionClose.closeConn( Connector.getConnectorInstance().getConnection());
        
        assertEquals(expected, actual);
    }
    
    private void click(WebDriver driver, String xpath, String str) {
        ele = driver.findElement(By.xpath(xpath));
        jse.executeScript(ACTION, ele);
        driver.findElement(By.xpath(xpath)).sendKeys(str);
    }
}