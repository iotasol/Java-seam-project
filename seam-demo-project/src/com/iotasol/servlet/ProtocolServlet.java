/**
 * 
 */
package com.iotasol.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotasol.protocolBinder.OperationManager;

/**
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class ProtocolServlet extends HttpServlet 
{
	private static final long serialVersionUID = ProtocolServlet.class
	.getCanonicalName().hashCode();
	
	private ServletContext servletContext;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		servletContext = servletConfig.getServletContext();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{		
		String output = null;
		
		/* Calling the Operation Manager which will decide
		 * the operation type and call the corresponding
		 * operation binder and set the return response 
		 * generated in http response.*/
		OperationManager operationManager = OperationManager.getInstance();
		output = operationManager.getResponse(servletContext, request, response);
		
		/*
		 * Returning the xml to the HTTP servlet response
		 */		
		PrintWriter out = response.getWriter();
		out.print(output);
		out.close();
	}

}
