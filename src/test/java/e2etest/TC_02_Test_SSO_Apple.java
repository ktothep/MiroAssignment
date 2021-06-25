package e2etest;

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
import pagemodels.SuccessfulRegistration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/*This test is for checking if user is able to SSO using Apple.*/

public class TC_02_Test_SSO_Apple {

    public static SignUp signUp;
    public static SuccessfulRegistration successfulRegistration;
    static ExtentReports extentReports;
    static ExtentTest extentLogger;
    Logger log = Logger.getLogger(TC_02_Test_SSO_Apple.class.getName());



    /*Perform initialisation Activity of Webdriver,Logger,Reporter*/

    @BeforeClass()
    public void classSetup() throws IOException {
        log.info("Initializing Chrome Driver");
        Base.initialise(Base.getBrowser());
        signUp = new SignUp();
        successfulRegistration = new SuccessfulRegistration();
        log.info("Fetching URL");
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/result/" + TC_02_Test_SSO_Apple.class.getName() +"_"+ LocalDate.now() +"_"+ LocalTime.now().toString().replace(":","-") + ".html", true);
        extentLogger = extentReports.startTest("Verify if user is able to SSO using Apple");
        Base.getURL(Base.getURL());
        extentLogger.log(LogStatus.PASS, "URL opened successfully");
        Base.maximise();

    }



    @Test
    public void verifyTitle() {
        try {
            log.info("Clicking on Apple SSO button and validating title of the newly open Page ");
            Assert.assertEquals(signUp.ssoApple().replace(String.valueOf((char) 160), " "), "Sign in with Apple ID");
            log.info("Title of the Page: " + Base.getTitle());
            extentLogger.log(LogStatus.PASS, "Title of the Page is validated : " + Base.getTitle());


        } catch (TimeoutException timeoutException) {
            log.error("Element is not present.Check for change in xpath or if Page is loaded:" + timeoutException.getLocalizedMessage());
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
