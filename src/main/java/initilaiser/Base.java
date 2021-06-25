package initilaiser;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*This is the test base class.Used for initialisation of the Webdriver
   and it contains commonly called method related to
  webdriver.
 */
public class Base {

    public static WebDriver webDriver;

    private static Logger logger = Logger.getLogger(Base.class);

    static Properties  properties;


    public static void initialise(String driverName) {
        switch (driverName) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
                webDriver = new ChromeDriver();
                logger.info("Chrome Driver initialised");
                break;

            case "Firefox":
                webDriver = new FirefoxDriver();
                logger.info("Firefox Driver initialised");
                break;

            case "Safari":
                webDriver = new SafariDriver();
                logger.info("Safari Driver initialised");
                break;

            default:

        }
    }

    public static void maximise() {

        webDriver.manage().window().maximize();
        logger.info("Browser Window maximised");
    }

    public static String getTitle() {
        return webDriver.getTitle();
    }

    public static void getURL(String URL) {

        logger.info("Opening URL: " + URL);
        webDriver.get(URL);
        logger.info("URL opened: " + URL);
    }

    public static void quit() {
        logger.info("Quitting  driver");
        webDriver.quit();
        logger.info("Driver closed");
    }

    public static void initaliseProperties() throws IOException {
        properties =new Properties();

        properties.load(new FileReader(System.getProperty("user.dir")+"/src/main/resources/properties"));
    }

    public static String getURL() throws IOException {
    initaliseProperties();
       return properties.getProperty("URL");
    }

    public static String getBrowser() throws IOException {
        initaliseProperties();
        return properties.getProperty("Browser");
    }

}
