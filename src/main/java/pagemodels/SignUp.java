package pagemodels;

import initilaiser.Base;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.HelperMethods;

import java.util.ArrayList;
import java.util.List;

/* This is Page Object Model for Miro Sign Up Page */
public class SignUp extends Base {

    HelperMethods helperMethods = new HelperMethods();

    @FindBy(xpath = ".//input[@id='name']")
    WebElement name;

    @FindBy(xpath = ".//input[@id='email']")
    WebElement email;

    @FindBy(xpath = ".//input[@id='password']")
    WebElement password;

    @FindBy(xpath = ".//button[@type='submit']")
    WebElement submit;

    @FindBy(xpath = ".//label[@for='signup-terms']")
    WebElement termsCheck;

    @FindBy(xpath = ".//label[@for='signup-subscribe']")
    WebElement subscribeCheck;

    @FindBy(xpath = ".//label[@for='tos-signup-terms']")
    WebElement ssoTermsCheck;

    @FindBy(xpath = ".//label[@for='tos-signup-subscribe']")
    WebElement ssoSubscribeCheck;

    @FindBy(css = "div#a11y-signup-with-google")
    WebElement signupGoogle;

    @FindBy(xpath = ".//img[contains(@alt,'Slack')]")
    WebElement signupSlack;

    @FindBy(xpath = ".//img[contains(@alt,'Office')]")
    WebElement signupOutlook;

    @FindBy(xpath = ".//img[contains(@alt,'Apple')]")
    WebElement signupApple;

    @FindBy(xpath = ".//img[contains(@alt,'Facebook')]")
    WebElement signupFacebook;

    @FindBy(xpath = ".//div[contains(@class,'socialtos__btn js')]")
    WebElement submitSSO;

    @FindBy(xpath = ".//a[@title='Miro Logo']")
    WebElement miroLogo;

    @FindBy(xpath = ".//a[contains(@class,'signup__btn')]")
    WebElement signIn;

    @FindBy(xpath = ".//div[contains(@class,'signup__error')]")
    WebElement emptyPassword;

    @FindBy(xpath = ".//div[contains(@class,'signup__input-hint-text')]")
    WebElement smallPassword;

    @FindBy(xpath = ".//*[contains(@data-autotest-id,'mr-error-signup')]")
    WebElement termsError;

    /*
    Constructor for initialising the WebElements through PageFactory
    */
    public SignUp() {
        PageFactory.initElements(webDriver, this);
    }

    /*
       Method for checking if the user has landed on Home Page
    */
    public boolean validatePageElements() {
        boolean nameCheck = name.isDisplayed() && name.isEnabled();
        boolean emailCheck = email.isDisplayed() && email.isEnabled();
        boolean passwordCheck = password.isDisplayed() && password.isEnabled();
        boolean signupGoogleCheck = signupGoogle.isDisplayed() && signupGoogle.isEnabled();
        boolean signupSlackCheck = signupSlack.isDisplayed() && signupSlack.isEnabled();
        boolean signupOutlookCheck = signupOutlook.isDisplayed() && signupOutlook.isEnabled();
        boolean signupAppleCheck = signupApple.isDisplayed() && signupApple.isEnabled();
        boolean signupFacebookCheck = signupFacebook.isDisplayed() && signupFacebook.isEnabled();
        boolean logoCheck = miroLogo.isDisplayed();

        return nameCheck && emailCheck && passwordCheck && signupGoogleCheck && signupSlackCheck && signupOutlookCheck && signupAppleCheck && signupFacebookCheck && logoCheck;

    }

    /*
    Method for entering User Credentials on Sign Up Page
     */
    public void enterCredentials(String usernameValue, String passwordValue, String emailValue) {

        name.sendKeys(usernameValue);
        email.sendKeys(emailValue);
        password.sendKeys(passwordValue);
        termsCheck.click();
        this.scrollBy(submit);
        subscribeCheck.click();
        submit.click();
    }

    /*
    Method for checking SSO functionality using Gmail
     */
    public String ssoGoogle() {
        this.scrollBy(signupGoogle);
        signupGoogle.click();
        ssoSubscribeCheck.click();
        ssoTermsCheck.click();
        submitSSO.click();
        while (!helperMethods.waitForPageToLoad()) {
            helperMethods.waitForPageToLoad();
        }
        return webDriver.getTitle();
    }

    /*
    Method for checking SSO functionality using Slack
     */
    public String ssoSlack() {
        this.scrollBy(signupSlack);
        signupSlack.click();
        ssoSubscribeCheck.click();
        ssoTermsCheck.click();
        submitSSO.click();
        while (!helperMethods.waitForPageToLoad()) {
            helperMethods.waitForPageToLoad();
        }
        return webDriver.getTitle();
    }

    /*
    Method for checking SSO functionality using Office
     */
    public String ssoOffice() {
        this.scrollBy(signupOutlook);
        signupOutlook.click();
        ssoSubscribeCheck.click();
        ssoTermsCheck.click();
        submitSSO.click();
        while (!helperMethods.waitForPageToLoad()) {
            helperMethods.waitForPageToLoad();
        }
        return webDriver.getTitle();
    }

    /*
    Method for checking SSO functionality using Apple
     */
    public String ssoApple() {
        this.scrollBy(signupApple);
        signupApple.click();
        ssoSubscribeCheck.click();
        ssoTermsCheck.click();
        submitSSO.click();
        while (!helperMethods.waitForPageToLoad()) {
            helperMethods.waitForPageToLoad();
        }
        return webDriver.getTitle();
    }

    /*
    Method for checking SSO functionality using Facebook
     */
    public String ssoFacebook() {
        this.scrollBy(signupFacebook);
        signupFacebook.click();
        ssoSubscribeCheck.click();
        ssoTermsCheck.click();
        submitSSO.click();
        while (!helperMethods.waitForPageToLoad()) {
            helperMethods.waitForPageToLoad();
        }
        return webDriver.getTitle();
    }

    /*
    Method for checking Home Page Redirect by clicking on Miro Logo
    */
    public String logoRedirect() {
        miroLogo.click();
        return webDriver.getTitle();
    }

    /*
    This method is for scrolling till a particular element comes in view
    */
    public void scrollBy(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView()", element);
    }

    /*
       Method for checking Sign In Page Redirect by clicking on Sign In Button
     */
    public String signInRedirect() {
        signIn.click();
        return webDriver.getTitle();
    }

    /*
    Method for checking validation when password field is empty
     */
    public String passwordEmptyValidation() {
        this.scrollBy(email);
        submit.click();
        helperMethods.waitForWebElement(emptyPassword, 30);
        return emptyPassword.getText();
    }

    /*
   Method for validating if error is shown when password of less than 8 characters is used
     */
    public String passwordLengthValidation(String username, String passwordValue, String emailValue) {
        name.clear();
        email.clear();
        name.sendKeys(username);
        email.sendKeys(emailValue);
        this.scrollBy(submit);
        password.sendKeys(passwordValue);
        submit.click();
        helperMethods.waitForWebElement(smallPassword, 30);
        return smallPassword.getText();
    }

    /*
    Method for checking Terms and conditions validation when not selected
     */
    public String termsValidation() {
        name.sendKeys("Karan Prinja");
        email.sendKeys("k1234@hotmail.com");
        password.sendKeys("termsnotmet");
        this.scrollBy(submit);
        submit.click();
        helperMethods.waitForWebElement(termsError, 30);
        this.scrollBy(termsError);
        return termsError.getText();
    }

    /*
     Method for collecting different messages that are shown when password of different  strength are used
   */
    public List<String> passwordMessageValidations() {
        name.sendKeys("Karan Prinja");
        email.sendKeys("k1234@hotmail.com");
        password.sendKeys("12345678");
        helperMethods.waitForWebElement(smallPassword, 30);
        String sosoPassword = smallPassword.getText();
        password.clear();
        password.sendKeys("Randomer12345678");
        helperMethods.waitForWebElement(smallPassword, 30);
        String goodPassword = smallPassword.getText();
        List<String> passwordValidationText = new ArrayList<>();
        passwordValidationText.add(sosoPassword);
        passwordValidationText.add(goodPassword);
        return passwordValidationText;
    }


}
