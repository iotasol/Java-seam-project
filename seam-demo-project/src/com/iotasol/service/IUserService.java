package com.iotasol.service;

import com.iotasol.bean.UserBean;
import com.iotasol.dto.UserDTO;
import com.iotasol.model.User;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public interface IUserService {
	
	UserBean loadUser( String userName,String password);
	void fetchAllUsers(UserDTO userDTO);
	UserBean loadUserByUserId(UserDTO userDTO);
	void saveOrUpdateUser(UserDTO userDTO);
	void deleteUser(long id);
	User loadUserByUsername(String username);
}
