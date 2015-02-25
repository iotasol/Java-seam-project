package com.iotasol.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

import com.iotasol.common.CommonUtils;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class SWFUploadServlet extends HttpServlet {
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SWFUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO get messages form msg resouces
		String ret = null;
		boolean isMultipart = FileUpload.isMultipartContent(request);
		Iterator<FileItem> iter = null;
		List<FileItem> items = null;
		if (isMultipart) {
			DiskFileUpload upload = new DiskFileUpload();
			try {
				items = upload.parseRequest(request);
				iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (!item.isFormField()) {
						String itemName = item.getName();
						int IndexOf;
						if ((IndexOf = itemName.indexOf(".")) != -1) {
							ret = CommonUtils.saveFile(item, itemName, IndexOf);
							break;
						}
					}
				}

			} catch (FileUploadException e) {
				throw e;
			}
		}
		return ret;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "Success";
		try {
			result+=","+doExecute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failure";
		}

		response.getOutputStream().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "Success";
		try {
			result+=","+doExecute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failure";
		}

		response.getOutputStream().print(result);
	}

}
