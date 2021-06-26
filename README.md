# MiroAssignment

This is a Test Automation Framework making use of TestNG and Selenium along with Maven for dependency management and execution.Reporting is handled by Extent Reports and logging by log4j

**Pre-Requisite**
  1.JAVA 8 or higher
  2.Maven

**Directory Structure:**

**Results Folder **--> This folder has all the test results that are generated after execution of the Test Cases.
 
**Logs Folder** --> This folder is used to store all the logs that are generated for test execution

**src** --> This folder is divided in two parts : main and test

 1. **main** --> This folder further contains more folders:
   
    i.  **pagemodels** --> This folder has all the POM's of the page we are interatcing .
    ii. **initilaise** --> This folder has the base class that is used for initialising the webdriver.
    iii.**utils**      --> This folder has the helper classes for Data fetching and other commonly used method.For example Explicit wait 

 2.  **test**  --> This folder has all the test cases.The test cases are further divided in sanity,end to end and negative scenarios.
     **Listener** --> This has the listener method for the test cases 
**Resources** --> This folder is present inside the main folder.Below are the files it has

   1. **driver**          -->  This folder has drivers for all the browsers we are making use of.If you want to make use of new browser for testing.Place the                                      browser driver in this folder and make changes to the Base.Class switch case statement.
   2. **testdata**        -->  This folder has the test datat which has to be used for testing.We will take a look into how to give test data later
   3. **properties**      -->  This file has 2 properties Browser,which is used to specify the browser to be open and URL,which gives the URL that has to be       
   4. **log4j.properties**-->  This file is for configuring the log4j properties


**Test Data**

To enter Test Data for particular test case you have to open the Testdata.xlsx file.Please make sure that we are making use of .xlsx file only and not xls.
In the Testcase column enter the number of the testcase i.e TC_1.Create columns as per your requirement.If you want to execute a single test case multiple times.Then enter the testdata equal to the number of times you want the test to be executed.
For Example currently you can see that TC_1 data has been added 3 times so test case 1 will be executed 3 times with the test data provided.
In your **@BeforeSuite** annotation method then call the DataMethods as below:
  DataMethods dataMethods = new DataMethods();
  dataMethods.fetchData("Sheet1", "TC_1", new String[]{"Testcase", "Username", "Password", "Email"});
  where
  Sheet1.      : This is the sheet from which data has to be accessed.
  TC_1         : This is the testcase for which test data is required.
  String Array : This is the array of column for which we require the data.
 
 Then make use of Dataprovider in your test method to access the data.
 
 
 **TestNG-XML's**
 
 We have three testng xml's:
 
 1. test_end2end.xml : This XML is for executing the end to end scenario test suite.
 2. test_negative.xml: This XML is for executing negative scenario test suite.  
 3. test_sanity.xml  : This XML is for executing sanity scenario test suite.


  **Execution**
  
  Make sure JAVA is installed and maven is configured.
  Execute below command:
  mvn clean test
         
            **OR**
            
 Open the poject in your IDE and execute each test case individually or through the XML.           

  **Continuous Integration/Deployment**

  Instead of creating a pipeline for this project.We can create a freestyle project.Pass the Git repository
  path in it.Set build trigger for periodic or when changes are push by configuring a webhook in git.Then we can
  set the build step as 'mvn clean test'.


  **Test Cases**
    
  1.*TC_01_Test_SignUp*: This test case is for Sign-in Up User.To check successful signup.

  2.*TC_02_Test_SSO_Apple*: This test case is for checking if SSO to Apple opens Apple sign in page.

  3.*TC_03_Test_SSO_Facebook*: This test case is for checking if SSO to Facebook opens Facebook sign in page. 

  4.*TC_04_Test_SSO_Google*: This test case is for checking if SSO to GMAIL opens Gmail login page.

  5.*TC_05_Test_SSO_Office*: This test case is for checking if SSO to Office365 opens Outlook sign in page.

  6.*TC_06_Test_SSO_Slack*: This test case is for checking if SSO to Slack opens Slack SSO page.

  7.*TC_07_Test_PasswordLengthMessages*: This test case validates different messages shown for passwords of varied length.

  8.*TC_08_Test_PasswordValidation*: This test case is for validation of message when password length ios less than 8 characters.

  9.*TC_09_Test_TermsCheck*: This test case validates error message when Terms Check box is left unchecked.

  10.*TC_10_Test_ValidateHomeLink*: This test case validates if clicking on Miro logo redirects user to HomePage.

  11.*TC_11_Test_ValidateSignIn*: This test case validates if user is redirected to Sign in Page on clicking sign in Page.

  12.*TC_12_Test_ValidateSignUpPage*: This test case validates if Sign Up Page is coming correctly.

  13.*TC_13_Test_UseralreadyRegistered*: This test case validates the scenario if the user is already registerd and tries to register again.




#NOTE               
I am using Mac so the chrome driver does not has any .exe extension.
If you are a windows users please place the chromedriver.exe in resources->driver folder and change the filename in Base.class.

If some Test Cases fail please re-run the individual test case separately.I have not implemented IRetry Analyser for simplicity.

Please enter fresh data in the Test-Data excel for TC_1.For TC_13 enter credentials of user already registered or keep it as it is as i have added my credentials
In some test cases I have hard-coded the test-data,but they can be inserted through excel.I just wanted to shown different ways of inserting data.

In SSO Test cases I have used Thread.sleep.I know this is not a valid method.But i did not
wanted to access elements of third party pages and increase POM count.

