package negativescenario;

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
import java.util.List;

/*This test is for checking different messages when Password of different strength are used*/

public class TC_07_Test_PasswordLengthMessages {

    public static SignUp signUp;
    static ExtentReports extentReports;
    static ExtentTest extentLogger;
    Logger log = Logger.getLogger(TC_07_Test_PasswordLengthMessages.class.getName());


    /*Perform initialisation Activity of Webdriver,Logger,Reporter*/

    @BeforeClass()
    public void classSetup() throws IOException {
        log.info("Initializing Chrome Driver");
        Base.initialise(Base.getBrowser());
        signUp = new SignUp();
        log.info("Fetching URL");
        Base.getURL(Base.getURL());
        Base.maximise();
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/result/" + TC_07_Test_PasswordLengthMessages.class.getName() + "_" + LocalDateTime.now(ZoneId.systemDefault()) + "_" + ".html", true);
        extentLogger = extentReports.startTest("Validate different Password Length Related Messages");
        extentLogger.log(LogStatus.PASS, "URL opened successfully");


    }

    @Test
    public void passwordTest() {
        try {
            log.info("Validating different Messages are shown for Passwords of Different Legth");
            List<String> messages = signUp.passwordMessageValidations();
            Assert.assertEquals(messages.size(), 2);
            Assert.assertTrue(messages.stream().anyMatch((str) -> str.equals("So-so password")) && messages.stream().anyMatch((str) -> str.equals("Good password")));
            messages.stream().forEach((str) -> log.info("Message Displayed: " + str));
            messages.stream().forEach((str) -> extentLogger.log(LogStatus.PASS, "Message validation successful.Messages Shown : " + str));


        } catch (TimeoutException timeoutException) {
            log.error("Element is not present.Check for change in xpath or if Page is loaded: " + timeoutException.getLocalizedMessage());
            extentLogger.log(LogStatus.FAIL, "Element is missing " + timeoutException.getLocalizedMessage());
            Assert.fail("Test has failed");
        } catch (Exception exception) {
            log.error("Something went wrong.Please check code.:" + exception.getLocalizedMessage());
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
