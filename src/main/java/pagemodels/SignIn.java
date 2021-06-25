package pagemodels;

import initilaiser.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*This is Page Object Model for Miro Sign In Page*/

public class SignIn extends Base {

    @FindBy(xpath = ".//h1[contains(@class,'signup__title')]")
    WebElement signInHeading;


    /*
    Constructor for initialising the WebElements through PageFactory
    */
    public SignIn() {
        PageFactory.initElements(webDriver, this);
    }

    /*
    Method for checking if the user has landed on Home Page
    */
    public boolean validatePage() {
        return signInHeading.isDisplayed();
    }
}
