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
public class DeleteEmployeeTest {
	private static APIURLDetails data;
	private static Logger logger = LoggerFactory.getLogger();

	@BeforeTest(description = "This is to establish connections with the data source")
	public static void setURIwithEmpID()

	{
		data = new APIURLDetails();

	}

	@Test(description = "The method is used to delete an employee Record", priority = 1)
	public void deleteDataPositiveFLow() throws Exception {
		List<EmployeeDataObject> employeeList = EmployeeRecordDetails.employeeList;

		Response positiveResponse = given().contentType(ContentType.JSON).when()
				.delete(data.getEMPDeleteURI() + employeeList.get(0).getId()).then().extract().response();
		Assert.assertEquals(positiveResponse.getStatusCode(), 200);
		logger.info("Get All Employees Positive Response - " + positiveResponse.asString());

	}

	@Test(description = "This is negative flow where employee Record is not deleted when invalid parameters are passed", priority = 2)
	public void deleteData() throws Exception {
		List<EmployeeDataObject> employeeList = EmployeeRecordDetails.employeeList;
		for (EmployeeDataObject employee : employeeList) {

			Response negativeResponse = given().contentType(ContentType.JSON).when()
					.delete(data.getEMPDeleteURI() + "dummy/" + employee.getId()).then().extract().response();
			Assert.assertEquals(negativeResponse.getStatusCode(), 404);
			logger.info("Get All Employees Negative Response - " + negativeResponse.getStatusCode());

		}

	}
}
