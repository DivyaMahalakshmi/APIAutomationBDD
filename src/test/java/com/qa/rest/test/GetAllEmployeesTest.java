package com.qa.rest.test;

import static io.restassured.RestAssured.given;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.rest.excel.APIURLDetails;
import com.qa.rest.logger.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author Divya Mahalakshmi S
 *
 */
public class GetAllEmployeesTest {

	private static Logger logger = LoggerFactory.getLogger();

	@BeforeTest(description = "Setting the base URI to retrieve all the employee records")
	public static void setBaseURI()

	{
		APIURLDetails data = new APIURLDetails();
		RestAssured.baseURI = data.getEmpDataURL();

	}

	@Test(description = "The test method retrives all the employee Records from the Base URI", priority = 1)
	public static void getEmployeeDataPositiveFlow() {

		Response positiveResponse = given().when().get(RestAssured.baseURI).then().extract().response();
		Assert.assertEquals(positiveResponse.getStatusCode(), 200);
		logger.info("Get All Employees Positive Response - " + positiveResponse.asString());

	}

	@Test(description = "The test method is a negative case, given a wrong URI", priority = 2)
	public static void getEmployeeDataNegativeFlow() {

		Response negativeResponse = given().when().get(RestAssured.baseURI + "dummy/").then().extract().response();
		Assert.assertEquals(negativeResponse.getStatusCode(), 404);
		logger.info("Get All Employees Negative Response - " + negativeResponse.getStatusCode());

	}
}