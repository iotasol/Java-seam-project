package com.iotasol.util;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.el.ValueBinding;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class JSFUtil {

	public static void setSessionObject(String name, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(name, object);
	}

	public static Object getSessionObject(String name) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(name);
	}

	public static Object getSessionObject(String name, ServletContext context,
			HttpServletRequest req, HttpServletResponse res) {
		FacesContext ctx = getFacesContext(context, req, res);
		return ctx.getExternalContext().getSessionMap().get(name);
	}

	public static void inValidateSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
	}

	
	public static String getRequestParam(String requestParam) {
		return ((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getParameter(requestParam);
	}

	public static Object getJsfBean(String beanName, ServletContext context,
			HttpServletRequest req, HttpServletResponse res) {
		FacesContext ctx = getFacesContext(context, req, res);
		return getJsfBean(beanName, ctx);
	}

	public static void addErrorMessage(String messageStr) {
		FacesMessage message = new FacesMessage(messageStr);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void addInfoMessage(String messageStr) {
		clearMessage();
		FacesMessage message = new FacesMessage(messageStr);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void clearMessage(){
		Iterator<FacesMessage> msgIterator = FacesContext.getCurrentInstance().getMessages();
		FacesMessage facesMessage= null;
	    while(msgIterator.hasNext())
	    {
	    	facesMessage=msgIterator.next();
	        facesMessage.setDetail("");
	        facesMessage.setSummary("");
	    }
	}

	public static Object getJsfBeanFromServlet(String beanName,ServletContext servletContext)throws Exception {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		Object object = context.getBean(beanName);
		return object;
	}
	
	public static Object getJsfBean(String beanName) {
		return getJsfBean(beanName, FacesContext.getCurrentInstance());
	}

	private static Object getJsfBean(String beanName, FacesContext context) {
		Object ret = null;
		StringBuffer buff = new StringBuffer("#{").append(beanName).append("}");
		if (context != null) 
		{			
			Application tempApplication = context.getApplication();
			if(tempApplication != null)
			{
				ValueBinding tempvaluebinding = tempApplication.createValueBinding(buff.toString());
				if(tempvaluebinding != null)
				{
					ret = tempvaluebinding.getValue(context);
				}
			}
			//ret = context.getApplication().createValueBinding(buff.toString()).getValue(context);
		}
		return ret;
	}
	
	public static void redirect(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HttpServletRequest getRequest() {
		return ((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest());
	}

	private static FacesContext getFacesContext(ServletContext context,
			HttpServletRequest req, HttpServletResponse res) {
		FacesContext ret = null;

		ret = FacesContext.getCurrentInstance();
		if (ret == null) {

			FacesContextFactory fcFactory = (FacesContextFactory) FactoryFinder
					.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
			ret = fcFactory.getFacesContext(context, req, res, getLifeCycle());
		}
		return ret;
	}

	private static Lifecycle getLifeCycle() {
		LifecycleFactory lFactory = (LifecycleFactory) FactoryFinder
				.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
		return lFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
	}
	

}
