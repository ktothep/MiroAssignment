package pagemodels;

import initilaiser.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.HelperMethods;

/*This is Page Object Model for Miro HomePage*/

public class HomePage extends Base {

    HelperMethods helperMethods = new HelperMethods();

    @FindBy(xpath = ".//h5[text()='Product']")
    WebElement product;

    @FindBy(xpath = ".//h5[text()='By Use Cases']")
    WebElement useCases;

    @FindBy(xpath = ".//h5[text()='Plans & Pricing']")
    WebElement planPricing;

    @FindBy(xpath = ".//h5[text()='Resources']")
    WebElement resources;

    @FindBy(xpath = ".//h5[text()='Company']")
    WebElement company;

    /*
    Constructor for initialising the WebElements through PageFactory
    */

    public HomePage() {
        PageFactory.initElements(webDriver, this);
    }

    /*
    Method for checking if the user has landed on Home Page
    */
    public boolean validateHomePage() {
        this.scrollBy(product);
        boolean productCheck = product.isDisplayed();
        boolean useCasesCheck = useCases.isDisplayed();
        boolean planPricingCheck = planPricing.isDisplayed();
        boolean resourcesCheck = resources.isDisplayed();
        boolean companyCheck = company.isDisplayed();

        return productCheck && useCasesCheck && planPricingCheck && resourcesCheck && companyCheck;
    }

    /*
    This method is for scrolling till a particular element comes in view
    */
    public void scrollBy(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView()", element);

    }
}
