package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.HelperMethods;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestListener implements ITestListener {

    HelperMethods helperMethods=new HelperMethods();
    public static String imagePath;

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        try {
            String filename=result.getName()+"_"+result.getTestClass()+"_" +LocalDate.now()+"_" +LocalTime.now().toString().replace(":","-");
            imagePath= helperMethods.takeScreenshot(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getImagePath()
    {
        return imagePath;
    }


}
