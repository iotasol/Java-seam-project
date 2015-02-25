package com.iotasol.protocolBinder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class JSONBasicResponse 
{
	public static final String BASICNODE = "msg";
	
	public static String getBasicSuccessJSON()
	{
		JSONObject obj = null;
		try 
		{
			obj = new JSONObject();
			obj.put(BASICNODE,"");
		} catch (JSONException e){
			return ErrorCodesMessages.GENERIC_ERROR_MSG;
		}
		return obj.toString();
	}
	
	public static String getSuccessJSON(String nodeName, String message)
	{
		JSONObject obj = null;
		try 
		{
			obj = new JSONObject();
			obj.put(nodeName,message);
		} catch (JSONException e){
			return ErrorCodesMessages.GENERIC_ERROR_MSG;
		}
		return obj.toString();
	}
	
	public static String getErrorJSON(String errorMsg)
	{
		JSONObject obj = null;
		try 
		{
			obj = new JSONObject();
			obj.put(BASICNODE,errorMsg);
		} catch (JSONException e){
			return ErrorCodesMessages.GENERIC_ERROR_MSG;
		}
		return obj.toString();
	}
	
}
