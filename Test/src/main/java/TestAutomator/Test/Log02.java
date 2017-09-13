package TestAutomator.Test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class Log02 {

	static final Logger logger = Logger.getLogger(Log02.class.getName());
	
	static{
		BasicConfigurator.configure();
	}
	
	public synchronized void debug(String msg) {
		logger.debug(msg);
	}
	
	public synchronized void info(String msg) {
		logger.info(msg);
	}
	
	public synchronized void warn(String msg) {
		logger.warn(msg);
	}
	
	public synchronized void error(String msg) {
		logger.error(msg);
	}
	
	public synchronized void fatatl(String msg) {
		logger.fatal(msg);
	}


}
