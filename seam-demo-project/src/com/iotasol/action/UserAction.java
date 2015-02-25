package com.iotasol.action;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.iotasol.bean.UserBean;
import com.iotasol.common.CommonUtils;
import com.iotasol.dto.UserDTO;
import com.iotasol.model.User;
import com.iotasol.service.IUserService;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *@
 */
@Scope(ScopeType.EVENT)
@Name("userAction")
public class UserAction {
	
	@In("#{iUserService}")
	IUserService userService; 

	@In(create = true, required = false)
	private UserDTO userDTO;

	/************************************************
	 * Method called on loading of the listing page *
	 ************************************************/
	public void onLoad() {
		if (userDTO.isPostback())
			return;
		userDTO.setPostback(true);
		initializeDTO();
		loadAllUsers();
	}

	/*******************************************
	 * Clearing the User and the list of Users *
	 *******************************************/
	public void initializeDTO() {
		userDTO.setId(0);
		userDTO.setUserBean(new UserBean());
		userDTO.setUserListBean(null);
	}
	
	/******************************************************
	 * Saving and Updating User *
	 ******************************************************/
	public void saveUser(){
		User user = null;
		userDTO.setError(false);
		if(userDTO.getUserBean().getId() == 0l)
			user = userService.loadUserByUsername(userDTO.getUserBean().getUsername());
		if(CommonUtils.chechNotNull(user)){
			userDTO.setError(true);
			userDTO.setErrorMessage("Username already exists");
			return;
		}
		userService.saveOrUpdateUser(userDTO);
		initializeDTO();
		loadAllUsers();
	}
	
	/************************
	 * deletion of the user *
	 ************************/
	public void deleteUser(){
		userService.deleteUser(userDTO.getId());
		initializeDTO();
		loadAllUsers();
	}
	
	/**********************
	 * Fetching all Users *
	 **********************/
	public void loadAllUsers(){
		userService.fetchAllUsers(userDTO);
	}
	
	/**********************************
	 * Fetching one user based on its id *
	 **********************************/
	public void loadUser(){
		userDTO.setUserBean(userService.loadUserByUserId(userDTO));
	}
	
	/*****************************************
	 * Clearing the userbean and the user id *
	 *****************************************/
	public void clearUser(){
		userDTO.setId(0);
		userDTO.setUserBean(new UserBean());
	}

}
