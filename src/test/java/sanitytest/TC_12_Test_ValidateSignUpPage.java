package sanitytest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import initilaiser.Base;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagemodels.SignUp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/*This test is for validating Sign Up Page*/

public class TC_12_Test_ValidateSignUpPage {

    public static SignUp signUp;
    static ExtentReports extentReports;
    static ExtentTest extentLogger;
    Logger log = Logger.getLogger(TC_12_Test_ValidateSignUpPage.class.getName());


    @BeforeClass()
    public void setUp() throws IOException {
        log.info("Initializing Chrome Driver");
        Base.initialise(Base.getBrowser());
        signUp = new SignUp();
        log.info("Fetching URL");
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/result/" + TC_12_Test_ValidateSignUpPage.class.getName() + "_" + LocalDateTime.now(ZoneId.systemDefault()) + "_" + ".html", true);
        extentLogger = extentReports.startTest("Validate Sign Up Page ");
        Base.getURL(Base.getURL());
        extentLogger.log(LogStatus.PASS, "URL opened successfully");
        Base.maximise();

    }

    @Test
    public void signInTest() throws InterruptedException {
        try {
            log.info("Validating all the elements on SignUp Page are present");
            Assert.assertTrue(signUp.validatePageElements());
            extentLogger.log(LogStatus.PASS, "Sign Up Page is validated");

        } catch (TimeoutException timeoutException) {
            log.error("Element is not present.Check for change in xpath or if Page is loaded: " + timeoutException.getLocalizedMessage());
            extentLogger.log(LogStatus.FAIL, "Element is missing " + timeoutException.getLocalizedMessage());
            Assert.fail("Test has failed");
        } catch (Exception exception) {
            log.error("Something went wrong.Please check code.: " + exception.getLocalizedMessage());
            extentLogger.log(LogStatus.FAIL, "Error Occurred" + exception.getLocalizedMessage());
            Assert.fail("Test has failed");
        }

    }

    /*Perform Clean Up Activity after Test*/

    @AfterClass
    public void cleanUp() {
        log.info("Closing Web Browser.Performing Cleanup");
        extentLogger.log(LogStatus.PASS, "Cleanup Successful. Browser is closed");
        extentReports.flush();
        Base.quit();
    }
}
