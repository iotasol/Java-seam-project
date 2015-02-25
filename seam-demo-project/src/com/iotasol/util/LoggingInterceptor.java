package com.iotasol.util;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class LoggingInterceptor implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {

	/**
	 * Log file reference for logging the events
	 */
	private static Log log = null;

	/**
	 * Method which will be invoked to log the events before execution of any
	 * method.
	 * 
	 * @param methodname
	 *            Name of method to be logged
	 * @param methodArgs
	 *            Arguments passed to the method
	 * @param returnValue
	 *            Object returned from the method
	 * @throws Throwable
	 */
	public void before(Method methodname, Object[] methodArgs,
			Object returnValue) throws Throwable {
		// Creating log object for logging
		log = LogFactory.getLog(returnValue.getClass());
		String strLog = "method : (" + methodname.getName() + ")";
		// Log each of the method argument
		for (int i = 0; i < methodArgs.length; i++) {
			// If argument is not null
			if ((methodArgs[i] != null)) {
				// Log method name and argument value
				strLog += "'" + methodArgs[i] + "',";
			} else {
				strLog += "null, ";
			}
		}
		if (strLog.contains(",")) {
			strLog = strLog.substring(0, strLog.lastIndexOf(","));
		}
		strLog += " start";
		log.info(strLog);
	}

	/**
	 * Method which will be invoked to log the events after execution of any
	 * method
	 * 
	 * @param returnValue
	 *            Object returned from the method
	 * @param methodname
	 *            Name of method to be logged
	 * @param methodArgs
	 *            Arguments passed to the method
	 * @param target
	 *            Class where the method was called
	 * @throws Throwable
	 */
	public void afterReturning(Object returnValue, Method methodname,
			Object[] methodArgs, Object target) throws Throwable {

		// Creating log object for logging
		log = LogFactory.getLog(target.getClass());
		// Log return value of method
		if (returnValue != null) {
			log.info("end method" + methodname.getName() + "('" + returnValue
					+ "',)");
		} else {
			log.info("method" + methodname.getName() + "(null)");
		}
	}

	/**
	 * Method which will be invoked to log the events after exception has been
	 * thrown
	 * 
	 * @param method
	 *            Method in which exception occurred
	 * @param methodArgs
	 *            Arguments passed to the method
	 * @param target
	 *            Class where the method was called
	 * @param exception
	 *            Exception object thrown
	 */
	public void afterThrowing(Method method, Object[] methodArgs,
			Object target, Throwable throwable) {
		// Creating log object for logging
		log = LogFactory.getLog(target.getClass());
		Exception excep = null;
		try {
			throw throwable;
		} catch (Exception be) {
			excep = be;
			be.printStackTrace();
		} catch (Throwable bre) {
			excep = new Exception("Error occurred while processing", bre);
			bre.printStackTrace();
		}

		// Log method name and exception message
		log.error("method " + method.getName() + "exception is : "
				+ excep.getMessage());
	}

}
