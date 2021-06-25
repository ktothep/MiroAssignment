package negativescenario;

import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import initilaiser.Base;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pagemodels.SignUp;
import utils.DataMethods;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/*This test is for checking if password validation is present or not.*/

public class TC_08_Test_PasswordValidation extends Base {
    public static SignUp signUp;
    static ExtentReports extentReports;
    static ExtentTest extentLogger;
    Logger log = Logger.getLogger(TC_08_Test_PasswordValidation.class.getName());

    /*Initialise DataProvider and get the Data*/
    @BeforeSuite
    public void initialiseDataprovider() throws FilloException {
        DataMethods dataMethods = new DataMethods();
        dataMethods.fetchData("Sheet1", "TC_7", new String[]{"Testcase", "Username", "Password", "Email"});
    }

    /*Perform initialisation Activity of Webdriver,Logger,Reporter*/

    @BeforeClass()
    public void classSetup() throws IOException {
        log.info("Initializing Chrome Driver");
        Base.initialise(Base.getBrowser());
        signUp = new SignUp();
        log.info("Fetching URL");
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/result/" + TC_08_Test_PasswordValidation.class.getName() +"_"+ LocalDate.now() +"_"+ LocalTime.now().toString().replace(":","-") +  ".html", true);
        extentLogger = extentReports.startTest("Verify Password Validation Check");
        Base.getURL(Base.getURL());
        extentLogger.log(LogStatus.PASS, "URL opened successfully");
        Base.maximise();

        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test()
    public void emptyPasswordTest() {
        try {
            log.info("Validating if message is shown if Password field is empty");
            String emptyPasswordMessage = signUp.passwordEmptyValidation();
            Assert.assertEquals(emptyPasswordMessage, "Please enter your password.");
            extentLogger.log(LogStatus.PASS, "Message for Empty password validated successfully: " + emptyPasswordMessage);

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

    @Test(dataProvider = "dataProvider", dataProviderClass = DataMethods.class)
    public void passwordTestLessThan8(String userName, String password, String eMail) {
        try {
            log.info("Validating if message is shown if Password field lenght is less than 8 characters");
            String passwordLengthMessage = signUp.passwordLengthValidation(userName, password, eMail);
            Assert.assertEquals(passwordLengthMessage, "Please use 8+ characters for secure password");
            extentLogger.log(LogStatus.PASS, "Message for  password length validated successfully: " + passwordLengthMessage);

        } catch (TimeoutException timeoutException) {
            log.error("Element is not present.Check for change in xpath or if Page is loaded " + timeoutException.getLocalizedMessage());
            extentLogger.log(LogStatus.FAIL, "Element is missing " + timeoutException.getLocalizedMessage());
            Assert.fail("Test has failed");
        } catch (Exception exception) {
            log.error("Something went wrong.Please check code. " + exception.getLocalizedMessage());
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
