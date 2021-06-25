package utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.DataProvider;

import java.util.*;
import java.util.stream.Collectors;

/*
This class is for accessing Data from Excel.
I have made use of Fillo Library instead of Apache POI
 */

public class DataMethods {

    public final String DATA_SOURCE = System.getProperty("user.dir") + "/src/main/resources/testdata/Testdata.xlsx";
    public static Object[][] objectArray;
    Connection connection;
    Fillo fillo = new Fillo();

    /*
         Default Constructor to fetch Data from default file
     */
    public DataMethods() {
        try {
            connection = fillo.getConnection(DATA_SOURCE);
        } catch (FilloException e) {
            e.printStackTrace();
        }
    }

    /*
        Parameterised Constructor so user can specify the Path of Test Data Sheet
     */
    public DataMethods(String testData) {
        try {
            connection = fillo.getConnection(System.getProperty("user.dir") + "/src/main/resources/testdata/" + testData + ".xlsx");
        } catch (FilloException e) {
            e.printStackTrace();
        }

    }

    /*
    Return MultiDimension Object Array.This method acts as a dataProvider
    */
    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return objectArray;
    }

    /*
   Fetches data  from the excel sheet and adds it to multi dimension Object Array for Data
   Provider.It makes use of Arraylist and constructs a Dynamic Hashmap based on the number
   of columns that are fetched.User can pass sheet name ,testcase number and array of columns
   which are required to be fetched.
   */
    public void fetchData(String sheetName, String testCase, String[] strings) throws FilloException {
        String query = "Select * from " + sheetName + " where TestCase = " + "'" + testCase + "'";
        List<String> expectedColumns = Arrays.asList(strings);
        Recordset recordset = connection.executeQuery(query);
        List<HashMap<String, String>> dataList = new ArrayList<>();
        int count = recordset.getCount();
        Map<String, String> dataMap = new HashMap<>();
        Map<String, String> valueMap = new HashMap<>();
        List<String> columns = recordset.getFieldNames().stream().filter((colNames) -> (expectedColumns.contains(colNames))).collect(Collectors.toList());
        for (int rowcount = 0; rowcount < columns.size(); rowcount++) {
            dataMap.put(columns.get(rowcount), "");
        }
        while (recordset.next()) {
            for (Map.Entry<String, String> mapEntry : dataMap.entrySet()) {

                valueMap.put(mapEntry.getKey(), recordset.getField(mapEntry.getKey()));
            }
            dataList.add(new HashMap<>(valueMap));
        }


        objectArray = new Object[recordset.getCount()][columns.size()];


        for (int row = 0; row < count; row++) {
            int col = 0;
            for (String columnName : columns) {
                objectArray[row][col] = dataList.get(row).get(columnName);
                col++;
            }
        }


    }


}
