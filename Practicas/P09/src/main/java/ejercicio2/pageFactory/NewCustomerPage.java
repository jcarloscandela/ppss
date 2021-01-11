package ejercicio2.pageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCustomerPage {
    WebDriver driver;
    @FindBy(name="name") WebElement name;
    @FindBy(name="rad1") WebElement male;
    @FindBy(name="rad2") WebElement female;
    @FindBy(name="dob") WebElement dateofbirth;
    @FindBy(name="addr") WebElement address;
    @FindBy(name="city") WebElement city;
    @FindBy(name="state") WebElement state;
    @FindBy(name="pinno") WebElement pin;
    @FindBy(name="telephoneno") WebElement mobilenumber;
    @FindBy(name="emailid") WebElement email;
    @FindBy(name="password") WebElement password;
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]") WebElement submit;

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]") WebElement customerID;
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p") WebElement response;
    public NewCustomerPage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://demo.guru99.com/V4/manager/addcustomerpage.php");
    }

    public String NewCustomer(String name, String gender, String dateofbirth, String addres, String city, String state,
                            String pin, String mobilenumber, String email, String password){
        this.name.sendKeys(name);

        this.dateofbirth.click();
        this.dateofbirth.sendKeys(dateofbirth);

        this.address.sendKeys(addres);
        this.city.sendKeys(city);
        this.state.sendKeys(state);
        this.pin.sendKeys(pin);
        this.mobilenumber.sendKeys(mobilenumber);
        this.email.sendKeys(email);
        this.password.sendKeys(password);

        if(gender == "male"){
            this.male.click();
        } else{
            this.female.click();
        }

        submit.click();

        return getCustomerID();
    }
    public String getCustomerID(){
        return customerID.getText();
    }

    public String getResponse(){
        return response.getText();
    }
}
