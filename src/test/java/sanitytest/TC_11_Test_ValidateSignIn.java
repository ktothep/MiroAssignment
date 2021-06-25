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
import pagemodels.SignIn;
import pagemodels.SignUp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/*This test is for checking if User is redirected to Sign In Page on clicking Sign IN button*/


public class TC_11_Test_ValidateSignIn extends Base {
    public static SignUp signUp;
    public static SignIn signIn;
    static ExtentReports extentReports;
    static ExtentTest extentLogger;
    Logger log = Logger.getLogger(TC_11_Test_ValidateSignIn.class.getName());



    /*Perform initialisation Activity of Webdriver,Logger,Reporter*/

    @BeforeClass()
    public void setUp() throws IOException {
        log.info("Initializing Chrome Driver");
        Base.initialise(Base.getBrowser());
        signUp = new SignUp();
        signIn = new SignIn();
        log.info("Fetching URL");
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/result/" + TC_11_Test_ValidateSignIn.class.getName() + "_" + LocalDateTime.now(ZoneId.systemDefault()) + "_" + ".html", true);
        extentLogger = extentReports.startTest("Validate Sign in Page Link");
        Base.getURL(Base.getURL());
        extentLogger.log(LogStatus.PASS, "URL opened successfully");
        Base.maximise();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void validateSignInRedirect() {
        try {
            log.info("Validating title of Sign In Page");
            String signInTitle = signUp.signInRedirect();
            Assert.assertEquals(signInTitle, "Sign in | Miro | Online Whiteboard for Visual Collaboration");
            extentLogger.log(LogStatus.PASS, "Redirected to Sign In Page successfully");

            log.info("Validating elements of Sign In Page");
            Assert.assertTrue(signIn.validatePage());
            extentLogger.log(LogStatus.PASS, "Sign In Page is validated successfully");

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
