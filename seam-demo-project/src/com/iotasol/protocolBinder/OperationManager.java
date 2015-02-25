package com.iotasol.protocolBinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.iotasol.util.JSFUtil;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class OperationManager {
	private static OperationManager _opManager;
	

	/**
	 * private constructor (Singleton Pattern).
	 */
	private OperationManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method which checks if the object has been made and returns the object if
	 * already initialized else instantiates the object and returns it.
	 */
	public static OperationManager getInstance() {
		if (_opManager == null) {
			synchronized (OperationManager.class) {
				if (_opManager == null) {
					_opManager = new OperationManager();
				}
			}
		}
		return _opManager;
	}

	/**
	 * Method to select the protocol binder depending upon the operation type.
	 * The Protocol binder will write the response XML and return the XML as
	 * string to getResponse() which will then write the xml to the response.
	 */
	public String getResponse(ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		/*
		 * Checking if the request is a MultiPart request (File Upload request)
		 */

		boolean isMultipartContent = ServletFileUpload
				.isMultipartContent(request);
		Map<String, String> mapRequestParams = new HashMap<String, String>();
		List<String> listfiles = new ArrayList<String>();
		if (isMultipartContent) {
			try {
				multipartRequestResponse(servletContext, request,
						mapRequestParams, listfiles);
			} catch (Exception exception) {
				exception.printStackTrace();
				/*
				 * File Upload exception occurred while processing the request.
				 * Returning the error response XML
				 */
				return JSONBasicResponse.getErrorJSON(ErrorCodesMessages.GENERIC_ERROR_MSG);
			}
			
		}
		IGeneralBinder iGeneralBinder=null;
		try{

		simpleRequestResponse(servletContext, request, mapRequestParams);
		iGeneralBinder = (IGeneralBinder) JSFUtil
				.getJsfBeanFromServlet(request
						.getParameter(RequestParam.OPERATION), servletContext);
		}
		catch (Exception e) {
			return JSONBasicResponse.getErrorJSON(ErrorCodesMessages.GENERIC_ERROR_MSG);
		}
		try {
			return iGeneralBinder.getResponseJson(request, mapRequestParams);
		} catch (Exception ex) {
			return JSONBasicResponse.getErrorJSON(ErrorCodesMessages.GENERIC_ERROR_MSG);
		}
	}

	public void multipartRequestResponse(ServletContext servletContext,
			HttpServletRequest request, Map<String, String> mapRequestParams,
			List<String> listfiles) throws Exception {

		/*FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> formFields = upload.parseRequest(request);
		Iterator<FileItem> iterator = formFields.iterator();

		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();
			if (fileItem.isFormField()) {
				mapRequestParams.put(fileItem.getFieldName(), fileItem
						.getString());
				continue;
			}

			String file = fileItem.getName();
			int indexOf = file.indexOf(".");
			if (indexOf != -1) {
				Random randomGenerator = new Random();
				int tempFileAppender = Math.abs(randomGenerator.nextInt());
				String domainName = file.substring(indexOf);
				String fileName = file.substring(0, indexOf);
				String finalFileName = ApplicationProperties.UPLOAD_FOLDER
						+ fileName + "_"
						+ UploadFileTypes.getTypeFromName(file) + "_"
						+ tempFileAppender + domainName;
				File savedFile = new File(ApplicationProperties.FOLDER
						+ finalFileName);
				 Saving the file to the hard drive 
				fileItem.write(savedFile);
				listfiles.add(finalFileName);
			}
		}*/
	}

	public void simpleRequestResponse(ServletContext servletContext,
			HttpServletRequest request, Map<String, String> mapRequestParams)
			throws IOException {
		String key = "";
		for (Enumeration<?> e = request.getParameterNames(); e
				.hasMoreElements();) {
			key = (String) e.nextElement();
			mapRequestParams.put(key, request.getParameter(key));
		}
	}

}
