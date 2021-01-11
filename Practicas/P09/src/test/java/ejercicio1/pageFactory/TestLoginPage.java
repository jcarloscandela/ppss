package ejercicio1.pageFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TestLoginPage{
    WebDriver driver;
    LoginPage poLogin;
    ManagerPage poManagerPage;
    @BeforeEach
    public void setup(){
        driver = new FirefoxDriver();
        poLogin = PageFactory.initElements(driver, LoginPage.class);
    }
    @Test
    public void test_Login_Correct(){
        String loginPageTitle = poLogin.getLoginTitle();
        Assertions.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
        poLogin.login("mngr192901", "yjenaty");
        poManagerPage = PageFactory.initElements(driver, ManagerPage.class);
        Assertions.assertTrue(poManagerPage.getHomeDashboardUserName()
                .toLowerCase().contains("manger id : mngr192901"));
        driver.close();
    }

    @Test
    public void test_Login_Incorrect(){
        String loginPageTitle = poLogin.getLoginTitle();
        Assertions.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
        poLogin.login("usuarioIncorrecto", "passIncorrecta");
        poManagerPage = PageFactory.initElements(driver, ManagerPage.class);
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals(alert.getText(), "User or Password is not valid");
        alert.accept();
        driver.close();
    }
}
