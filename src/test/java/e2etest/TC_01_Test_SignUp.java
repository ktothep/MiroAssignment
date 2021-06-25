package e2etest;


import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import initilaiser.Base;
import listeners.TestListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pagemodels.SignUp;
import pagemodels.SuccessfulRegistration;
import utils.DataMethods;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/*This test is for checking if user is able to signup.*/

@Listeners(TestListener.class)
public class TC_01_Test_SignUp {

    public static SignUp signUp;
    public static SuccessfulRegistration successfulRegistration;
    static ExtentReports extentReports;
    static ExtentTest extentLogger;
    TestListener testListener;
    Logger log = Logger.getLogger(TC_01_Test_SignUp.class.getName());


    /*Perform initialisation Activity of Webdriver,Logger,Reporter*/
    @BeforeClass()
    public void classSetup() throws IOException {
        testListener=new TestListener();
        log.info("Initilaising Chrome Driver");
        Base.initialise(Base.getBrowser());
        signUp = new SignUp();
        successfulRegistration = new SuccessfulRegistration();
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/result/" + TC_01_Test_SignUp.class.getName()+"_"+ LocalDate.now() +"_"+ LocalTime.now().toString().replace(":","-") +  ".html", true);
        extentLogger = extentReports.startTest("Verify if user is able to Sign Up");

    }

    /*Initialise DataProvider and get the Data*/
    @BeforeSuite
    public void initialiseDataprovider() throws FilloException {
        DataMethods dataMethods = new DataMethods();
        dataMethods.fetchData("Sheet1", "TC_1", new String[]{"Testcase", "Username", "Password", "Email"});
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataMethods.class)
    public void signUp(String userName, String password, String eMail) throws FilloException {

        try {
            Base.getURL(Base.getURL());
            log.info("Opening URL");
            extentLogger.log(LogStatus.PASS, "URL opened successfully");
            Base.maximise();
            log.info("Validating all the elements on SignUp Page are present");
            Assert.assertTrue(signUp.validatePageElements());
            extentLogger.log(LogStatus.PASS, "Sign Up Page is validated");
            log.info("Entering Credentials for Sign Up");
            log.info("Entering Username: " + userName);
            log.info("Entering Email: " + eMail);
            log.info("Entering Password: " + password);
            signUp.enterCredentials(userName, password, eMail);
            extentLogger.log(LogStatus.PASS, "Credentials entered successfully username: " + userName + " ,Password: " + password + " ,eMail: " + eMail);
            log.info("Validating if Sign in Successful Page is loaded");
            Assert.assertTrue(successfulRegistration.validateRegistration());
            extentLogger.log(LogStatus.PASS, "Registration page is loaded succesfully");
            log.info("Validating the Email used for Sign-up is properly captured and correct");
            Assert.assertEquals(successfulRegistration.getEmail(), eMail);
            extentLogger.log(LogStatus.PASS, "Validating the email is captured correctly or not");

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

    @AfterMethod
    public void addScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentLogger.log(LogStatus.FAIL, "Screenshot of failed Step", extentLogger.addScreenCapture(testListener.getImagePath()));
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
