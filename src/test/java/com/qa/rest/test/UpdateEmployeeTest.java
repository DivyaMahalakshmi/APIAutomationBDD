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
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Divya Mahalakshmi S
 *
 */
public class UpdateEmployeeTest {
	private static APIURLDetails data;
	private static Logger logger = LoggerFactory.getLogger();

	@BeforeTest(description = "This is to establish connections with the data source")
	public static void setURIwithEmpID()

	{
		data = new APIURLDetails();

	}

	@Test(description = "The test method is to update Employee Record", priority = 1)
	public void updateEmployeeDataPositiveFlow() throws Exception {
		int salaryToBeUpdated = 10000;

		List<EmployeeDataObject> employeeList = EmployeeRecordDetails.employeeList;
		for (EmployeeDataObject employee : employeeList) {

			employee.setSalary(salaryToBeUpdated++);

			Response positiveResponse = given().body(employee).when().contentType(ContentType.JSON)
					.put(data.getEMPUpdateURI() + employee.getId()).then().extract().response();
			Assert.assertEquals(positiveResponse.getStatusCode(), 200);
			logger.info("Get All Employees Positive Response - " + positiveResponse.asString());

		}

	}

	@Test(description = "Negative flow where Employee Record is not updated when invalid parameters are passed", priority = 2)
	public void updateEmployeeDataNegativeFlow() throws Exception {
		int salaryToBeUpdated = 10000;

		List<EmployeeDataObject> employeeList = EmployeeRecordDetails.employeeList;
		for (EmployeeDataObject employee : employeeList) {

			employee.setSalary(salaryToBeUpdated++);

			Response negativeResponse = given().body(employee).when().contentType(ContentType.JSON)
					.put(data.getEMPUpdateURI() + "dummy/" + employee.getId()).then().extract().response();
			Assert.assertEquals(negativeResponse.getStatusCode(), 404);
			logger.info("Get All Employees Negative Response - " + negativeResponse.getStatusCode());

		}

	}
}
