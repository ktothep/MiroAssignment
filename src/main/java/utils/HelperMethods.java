package utils;

import initilaiser.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/*This class contains helper method which are used across frameworks but are not related
 *to webdriver.
 */

public class HelperMethods extends Base {

    /*Method for taking screenshots*/
    public void takeScreenshot(String fileName) throws IOException {
        File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "/screeshots/" + fileName + ".png");
        FileUtils.copyFile(src, dest);
    }

    /*
     Method for waiting for a particular element to appear.Using Explicit Wait with
     Expected Condition
    */
    public void waitForWebElement(WebElement webElement, long seconds) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, seconds);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public void waitForWebElementToBeInteractable(WebElement webElement, long seconds) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, seconds);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /*
    Method to wait for page to load completely
    */
    public boolean waitForPageToLoad() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        return javascriptExecutor.executeScript("return document.readyState").equals("complete");

    }
}
