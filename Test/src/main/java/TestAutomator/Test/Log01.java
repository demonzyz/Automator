package TestAutomator.Test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class Log01 {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Log01.class.getName());
		BasicConfigurator.configure();
		logger.setLevel(Level.WARN);
		logger.debug("is debug");
		logger.info("is info");
		logger.warn("is warn");
		logger.error("is error");
		logger.fatal("is fatal");
	}
}
