package com.iotasol.dao;

import java.util.List;

import com.iotasol.model.User;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public interface IUserDAO {
	
	User fetchUserByForLogin(String user , String password);
	User fetchUserByUserName(String username);
    List<User> fetchAllUsers();
    void saveOrUpdateUser(User user);
    void deleteUser(long id);
    User fetchUserByUserId(long userId);
}
