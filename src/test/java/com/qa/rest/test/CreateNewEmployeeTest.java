package com.qa.rest.test;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class CreateNewEmployeeTest {
	private static Logger logger = LoggerFactory.getLogger();

	@Test(description = "The method creates a new record of employee, accessing data from  external excel sheet", priority = 1)
	public void createNewEmpRecordPositiveFlow() throws Exception {

		// Feeding the input data
		APIURLDetails URI = new APIURLDetails();
		EmployeeRecordDetails cdata = new EmployeeRecordDetails();

		// passing the employee details as a List
		List<EmployeeDataObject> empdata = cdata.getEmployeeData();
		for (EmployeeDataObject emp : empdata) {

			Response positiveResponse = given().contentType(ContentType.JSON).body(emp).when()
					.post(URI.getEMPCreateURI()).then().contentType(ContentType.HTML).extract().response();
			Assert.assertEquals(positiveResponse.statusCode(), 200);

			// Retrieving the above result and storing in an object to reuse
			// Since API is accessible across different users , best practice is to re-use
			// the data for further manipulations across project

			ObjectMapper objectMapper = new ObjectMapper();
			EmployeeDataObject empData = objectMapper.readValue(positiveResponse.asString(), EmployeeDataObject.class);
			EmployeeRecordDetails.employeeList.add(empData);
			logger.info("Get All Employees Positive Response - " + positiveResponse.asString());

		}

	}

	@Test(description = "Negative method of employee record not created when proper parameters are not passed", priority = 2)
	public void createNewEmpRecordNegativeFlow() throws Exception {

		APIURLDetails URI = new APIURLDetails();
		EmployeeRecordDetails cdata = new EmployeeRecordDetails();
		List<EmployeeDataObject> empdata = cdata.getEmployeeData();
		for (EmployeeDataObject emp : empdata) {

			Response negativeRespone = given().contentType(ContentType.JSON).body(emp).when()
					.post(URI.getEMPCreateURI() + "dummy/").then().contentType(ContentType.HTML).extract().response();
			Assert.assertEquals(negativeRespone.statusCode(), 404);
			logger.info("Get All Employees Negative Response - " + negativeRespone.getStatusCode());

		}

	}
}