package com.iotasol.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.iotasol.model.User;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class UserDAO extends HibernateDaoSupport implements IUserDAO {

	public User fetchUserByForLogin(String email , String password){
		Query query = this.getSession().getNamedQuery("User.fetchUserByForLogin");
		query.setString("username", email);
		query.setString("password", password);
		Object obj = query.uniqueResult();
		if (obj == null)
			return null;
		return (User) obj;
	}
	
	public User fetchUserByUserName(String username){
		Query query=this .getSession().getNamedQuery("User.FetchUserByUsername");
		query.setString("username", username);
		Object obj=query.uniqueResult();
		if(obj==null)
			return null;
		return (User) obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> fetchAllUsers(){
		Query query = this.getSession().getNamedQuery("User.fetchUsers");
		return query.list();
	}	
	
	public void saveOrUpdateUser(User user){
		if(null == user.getId() || user.getId() == 0l){
			Long id = (Long)this.getHibernateTemplate().save(user);
			user.setId(id);
		} else
			this.getHibernateTemplate().update(user);
	}
	
	public void deleteUser(long id){
		Query query = this.getSession().createQuery("UPDATE User SET deleted=true WHERE id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public User fetchUserByUserId(long userId){
		Query query=this .getSession().getNamedQuery("User.FetchUserByUserId");
		query.setLong("id", userId);
		Object obj=query.uniqueResult();
		if(obj==null)
			return null;
		return (User) obj;
	}

}
