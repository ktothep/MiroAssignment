package pagemodels;

import initilaiser.Base;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.HelperMethods;

/*This is Page Object Model for Miro Successful Registration Page*/


public class SuccessfulRegistration extends Base {

    HelperMethods helperMethods=new HelperMethods();
    Logger logger=Logger.getLogger(SuccessfulRegistration.class);

    @FindBy(xpath=".//h1[text()='Check your email']")
    WebElement checkEmailLabel;

    @FindBy(xpath=".//div[contains(@class,'signup__subtitle')]/child::strong")
    WebElement emailLabel;

    @FindBy(xpath=".//input[@name='code']")
    WebElement codeTextBox;


    /*
   Constructor for initialising the WebElements through PageFactory
   */

    public SuccessfulRegistration()
    {
        PageFactory.initElements(webDriver,this);
    }

    /*
    Method for checking if the user has landed on Successful Registration Page
    */
    public boolean validateRegistration()
    {
       helperMethods.waitForWebElement(checkEmailLabel,30);
       boolean emailCheck= checkEmailLabel.isDisplayed();
       boolean codeCheck=  codeTextBox.isDisplayed();
       return emailCheck && codeCheck;


    }

    /*Fetches the email of the user from Successful RRegistration Page*/
    public String getEmail()
    {
       logger.info("Email present on Successful Registration Page: "+emailLabel.getText());
      return emailLabel.getText();
    }

}
