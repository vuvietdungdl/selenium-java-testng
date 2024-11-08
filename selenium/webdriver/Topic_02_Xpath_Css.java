package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    //@Test
    public void TC_01_CheckUserNamePassEmpty(){
        driver.get("https://dev.cloudpro.vn/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

        String messgaeContent=driver.findElement(By.xpath("//div[@id='validationMessage']//span[@class='message-content']")).getText();
        Assert.assertEquals(messgaeContent,"Xin vui lòng nhập tên người dùng hợp lệ");

        driver.quit();
    }

    //@Test
    public void TC_02_CheckPassEmpty(){
        driver.get("https://dev.cloudpro.vn/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("dung");
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

        String messgaeContent=driver.findElement(By.xpath("//div[@id='validationMessage']//span[@class='message-content']")).getText();
        Assert.assertEquals(messgaeContent,"Vui lòng nhập mật khẩu");

        driver.quit();
    }

    //@Test
    public void TC_03_CheckWrongPass(){
        driver.get("https://dev.cloudpro.vn/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("dung");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

        String messgaeContent=driver.findElement(By.xpath("//div[@id='validationMessage']//span[@class='message-content']")).getText();
        Assert.assertEquals(messgaeContent,"Sai thông tin đăng nhập");

        driver.quit();
    }

   // @Test
    public void TC_04_LoginSuccess(){
        driver.get("https://dev.cloudpro.vn/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("dung.vu");
        driver.findElement(By.id("password")).sendKeys("crm12345");
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

        String HomePage=driver.findElement(By.xpath("//h4[@class='module-title pull-left text-uppercase']")).getText();
        Assert.assertEquals(HomePage,"TRANG CHỦ");

        driver.quit();
    }

    //@Test
    public void TC_01_RegisterEmtyData() {
        driver.get("https://dev.cloudpro.vn/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("dung.vu");
        driver.findElement(By.id("password")).sendKeys("crm12345");
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

        String HomePage = driver.findElement(By.xpath("//h4[@class='module-title pull-left text-uppercase']")).getText();
        Assert.assertEquals(HomePage, "TRANG CHỦ");

        driver.quit();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
