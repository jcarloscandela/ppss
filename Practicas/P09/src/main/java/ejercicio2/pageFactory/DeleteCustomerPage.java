package ejercicio2.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteCustomerPage {
    WebDriver driver;
    @FindBy(name="cusid") WebElement customerid;
    @FindBy(name="AccSubmit") WebElement submit;

    public DeleteCustomerPage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://demo.guru99.com/V4/manager/DeleteCustomerInput.php");
    }

    public ManagerPage DeleteCustomer(String customer){
        customerid.sendKeys(customer);
        submit.click();
        return PageFactory.initElements(driver, ManagerPage.class);
    }
}
