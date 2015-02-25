package com.iotasol.service;

import java.util.ArrayList;
import java.util.List;

import com.iotasol.bean.UserBean;
import com.iotasol.common.CommonUtils;
import com.iotasol.dao.IUserDAO;
import com.iotasol.dto.UserDTO;
import com.iotasol.model.User;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class UserService implements IUserService {
	private IUserDAO userDAO;

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/**
	 * Loading User based on username and password
	 * @see com.iotasol.service.IUserService#loadUser(java.lang.String, java.lang.String)
	 */
	public UserBean loadUser(String userName, String password) {
		User user = userDAO.fetchUserByForLogin(userName, password);
		if (user == null)
			return null;
		return convertToUserBean(user);
	}
	
	/****************************
	 * loading User by Username *
	 ****************************/
	public User loadUserByUsername(String username){
		return userDAO.fetchUserByUserName(username);
	}
	
	/*************************
	 * Load User based on Id *
	 *************************/
	public UserBean loadUserByUserId(UserDTO userDTO){
		return convertToUserBean(userDAO.fetchUserByUserId(userDTO.getId()));
	}
	
	/*********************
	 * Loading all Users *
	 *********************/
	public void fetchAllUsers(UserDTO userDTO){
		List<User> list = userDAO.fetchAllUsers();
		List<UserBean> beanList = new ArrayList<UserBean>();
		if(CommonUtils.chkListEmpty(list)){
			userDTO.setUserListBean(null);
			return;
		}
		for(User user : list){
			beanList.add(convertToUserBean(user));
		}
		userDTO.setUserListBean(beanList);
	}
	
	/****************************
	 * Saving and Updating User *
	 ****************************/
	public void saveOrUpdateUser(UserDTO userDTO){
		UserBean userBean = userDTO.getUserBean();
		if(!CommonUtils.compareTwoString(userBean.getPassword(), userBean.getOldPassword())){
			try {
				userBean.setPassword(CommonUtils.encryptPassword(userBean
						.getPassword()));
			} catch (Exception e) {
				userDTO.setError(true);
				userDTO.setErrorMessage("Error occured, while encrypting password");
				return;
			}	
		}
		userDAO.saveOrUpdateUser(convertToUserModel(userBean));
	}
	
	/***************************
	 * Delete User based on id *
	 ***************************/
	public void deleteUser(long id){
		userDAO.deleteUser(id);
	}

	/***********************************
	 * Private method starts from here *
	 ***********************************/
	
	/*************************************
	 * Converting User Model to UserBean *
	 * @param User Model				 *
	 * @return UserBean					 *
	 *************************************/
	private UserBean convertToUserBean(User user) {
		UserBean userBean = new UserBean();
		userBean.setUsername(user.getUsername());
		userBean.setId(user.getId());
		userBean.setPassword(user.getPassword());
		userBean.setOldPassword(user.getPassword());
		userBean.setFirstName(user.getFirstName());
		userBean.setLastName(user.getLastName());
		userBean.setEmail(user.getEmail());
		userBean.setDeleted(user.isDeleted());
		return userBean;
	}
	
	/*************************************
	 * Converting UserBean to User Model *
	 * @param UserBean					 *
	 * @return User Model				 *
	 *************************************/
	private User convertToUserModel(UserBean userBean){
		User user = new User();
		user.setId(userBean.getId());
		user.setUsername(userBean.getUsername());
		user.setFirstName(userBean.getFirstName());
		user.setLastName(userBean.getLastName());
		user.setPassword(userBean.getPassword());
		user.setEmail(userBean.getEmail());
		user.setDeleted(userBean.isDeleted());
		return user;
	}
	
	/****************************
	 * Private method ends here *
	 ****************************/

}
