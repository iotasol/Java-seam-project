package com.iotasol.dto;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.annotations.Name;

import com.iotasol.bean.UserBean;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
@Name("userDTO")
public class UserDTO implements Serializable {
	private int id;
	private UserBean userBean;
	private List<UserBean> userListBean;
	private boolean postback;
	private String newPassword;
	private String oldPassword;
	private String username;
	private boolean error;
	private String errorMessage;
	
	/**
	 * @return the chkLogin
	 */
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the chkForgot
	 */
	
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * @return the userName
	 */
	
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the gameUserBean
	 */
	
	/**
	 * @param listSize the listSize to set
	 */
	
	/**
	 * @return the postback
	 */
	public boolean isPostback() {
		return postback;
	}
	/**
	 * @param postback the postback to set
	 */
	public void setPostback(boolean postback) {
		this.postback = postback;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public List<UserBean> getUserListBean() {
		return userListBean;
	}
	public void setUserListBean(List<UserBean> userListBean) {
		this.userListBean = userListBean;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
