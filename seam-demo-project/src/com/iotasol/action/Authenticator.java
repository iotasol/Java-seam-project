package com.iotasol.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Identity;

import com.iotasol.bean.UserBean;
import com.iotasol.common.CommonUtils;
import com.iotasol.common.WebConstants;
import com.iotasol.service.IUserService;
import com.iotasol.util.JSFUtil;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
@Scope(ScopeType.EVENT)
@Name("authenticator")
public class Authenticator {
	@In
	Identity identity;

	@In("#{iUserService}")
	IUserService gameUserService; 
	

	public boolean authenticate() {
		String username = identity.getUsername();
		String password = identity.getPassword();
		try {
			password = CommonUtils.encryptPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserBean user = gameUserService.loadUser(username, password);			
		if (user == null) {
			JSFUtil.addErrorMessage("Wrong Username or password!");
			return false;
		}
		identity.addRole("admin");
		JSFUtil.setSessionObject(WebConstants.SESSION_USER, user);
		JSFUtil.setSessionObject(WebConstants.SESSION_USER_NAME, user.getUsername());
		return true;
	}

	public void signOut() 
	{
		JSFUtil.setSessionObject(WebConstants.SESSION_USER, null);
		JSFUtil.inValidateSession();
		identity.logout();
		JSFUtil.redirect("../pages/login");
	
	}
}
