package ejercicio2.pageFactory;

import ejercicio2.pageFactory.LoginPage;
import ejercicio2.pageFactory.ManagerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.nio.charset.Charset;
import java.util.Random;

public class TestNewCustomer {
    WebDriver driver;
    LoginPage poLogin;
    ManagerPage poManagerPage;
    NewCustomerPage poNewCustomerPage;
    DeleteCustomerPage poDeleteCustomerPage;
    String name,  gender,  dateofbirth,  addres,  city,  state, pin,  mobilenumber,  email,  password;

    @BeforeEach
    public void setup(){
        driver = new FirefoxDriver();
        poLogin = PageFactory.initElements(driver, LoginPage.class);

    }
    @Test
    public void test_New_Customer(){
        name = "jccb";
        gender = "male";
        dateofbirth = "01/01/1990";
        addres = "Castalla";
        city = "Alicante";
        pin = "123456";
        mobilenumber = "999999999";
        state = "Alicante";

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        email = generatedString + "jccb3@alu.ua.es";
        password = "123456";

        String loginPageTitle = poLogin.getLoginTitle();
        Assertions.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

        poManagerPage = poLogin.login("mngr192901", "yjenaty");

        poNewCustomerPage = poManagerPage.newCustomer();

        String userId = poNewCustomerPage.NewCustomer(name,  gender,  dateofbirth,  addres,  city,  state, pin,  mobilenumber,  email,  password);

        Assertions.assertEquals("Customer Registered Successfully!!!", poNewCustomerPage.getResponse());

        poDeleteCustomerPage = PageFactory.initElements(driver, DeleteCustomerPage.class);
        poDeleteCustomerPage.DeleteCustomer(userId);

        driver.close();
    }
}
