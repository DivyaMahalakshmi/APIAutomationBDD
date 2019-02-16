package com.qa.rest.test;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.rest.api.EmployeeDataObject;
import com.qa.rest.excel.APIURLDetails;
import com.qa.rest.excel.EmployeeRecordDetails;
import com.qa.rest.logger.LoggerFactory;
import io.restassured.response.Response;

/**
 * @author Divya Mahalakshmi S
 *
 */
public class GetEmployeeRecordByIDTest {
	private static APIURLDetails data;
	private static Logger logger = LoggerFactory.getLogger();

	@BeforeTest(description = "This is to establish connections with the data source")
	public static void setURIwithEmpID()

	{
		data = new APIURLDetails();

	}

	@Test(description = "The Test method is to reteive employee record when the unique ID is presented", priority = 1)
	public static void getEmployeeDataWithIDPositiveFlow() throws Exception {
		// data used from the previously created Employee Record
		List<EmployeeDataObject> employeeList = EmployeeRecordDetails.employeeList;
		for (EmployeeDataObject employee : employeeList) {

			Response positiveResponse = given().when().get(data.getEMPURLID() + employee.getId()).then().extract()
					.response();
			Assert.assertEquals(positiveResponse.statusCode(), 200);
			logger.info("Get All Employees Positive Response - " + positiveResponse.asString());
		}
	}

	@Test(description = "This is negative case where Employee details are not retrived when invalid paramaters are passed", priority = 2)
	public static void getEmployeeDataWithIDNegativeFLow() throws Exception {

		List<EmployeeDataObject> employeeList = EmployeeRecordDetails.employeeList;
		for (EmployeeDataObject employee : employeeList) {

			Response negativeResponse = given().when().get(data.getEMPURLID() + "dummy/" + employee.getId()).then()
					.extract().response();

			Assert.assertEquals(negativeResponse.statusCode(), 404);
			logger.info("Get All Employees Negative Response - " + negativeResponse.getStatusCode());

		}

	}

}