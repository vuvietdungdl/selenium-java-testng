package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic02_Xpath_Css {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

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

   //@Test
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

   // @Test
    public void TC_01_RegisterEmtyData() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String firtNameErro=driver.findElement(By.id("txtFirstname-erro")).getText();
        String emailErro=driver.findElement(By.id("txtEmail-error")).getText();
        String emailConfirmErro=driver.findElement(By.id("txtCEmail-error")).getText();
        String passErro=driver.findElement(By.id("txtPassword-error")).getText();
        String passConfirmErro=driver.findElement(By.id("txtCPassword-error")).getText();
        String phoneErro=driver.findElement(By.id("txtPhone-error")).getText();

        Assert.assertEquals(firtNameErro,"Vui lòng nhập họ tên");
        Assert.assertEquals(emailErro,"Vui lòng nhập email");
        Assert.assertEquals(emailConfirmErro,"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(passErro,"Vui lòng nhập mật khẩu");
        Assert.assertEquals(passConfirmErro,"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(phoneErro,"Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_RegisterEmtyData() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Dung CRM");
        driver.findElement(By.id("txtEmail")).sendKeys("dung@123@123");
        driver.findElement(By.id("txtCEmail")).sendKeys("dung@123@123");
        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtPhone")).sendKeys("849658745236");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
