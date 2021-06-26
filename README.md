# MiroAssignment

This is a Test Automation Framework making use of TestNG and Selenium along with Maven for dependency management and execution.Reporting is handled by Extent Reports and logging by log4j

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

#NOTE               
I am making use of Mac so the webdriver executeable i have does not has any .exe extension.
If you are a windows users please place the chromedriver.exe in resources->driver folder and change the 
filename in Base.class.

If some Test Cases fail please re run the individual test case sperately

Please enter fresh data in the Test-Data excel.In some of the test cases i have hard-coded the testdata
But they can be insterted through excel.I just wanted to shown different ways of inserting data.



