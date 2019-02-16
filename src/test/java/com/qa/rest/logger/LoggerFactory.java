package com.qa.rest.logger;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Divya Mahalakshmi S
 *
 */

//This class is to log the output

public class LoggerFactory {

	//This provides instance to the logger that is used across all test methods
	public static Logger getLogger() {
		Logger logger = Logger.getLogger("RestAssured BDD");

		FileHandler fh;
		try {
			fh = new FileHandler("src/test/resources/TestResults.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logger;
	}

}
