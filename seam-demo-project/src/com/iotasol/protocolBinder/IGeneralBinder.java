package com.iotasol.protocolBinder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public interface IGeneralBinder {
	String getResponseJson(HttpServletRequest request,
			Map<String, String> mapParams)throws Exception;
	
	void validateRequiredParams(HttpServletRequest request,
			Map<String, String> mapParams)throws Exception;

}
