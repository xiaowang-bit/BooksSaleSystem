package com.zhc.service;

import com.xxq.model.User;
import com.zhc.dao.UserDao;

public class UserService {
	UserDao userDao = new UserDao();
	
	//注册用户
	public boolean addUser(User user) {
		if(!userDao.isExist(user.getId())){//不存在，可以注册
			return userDao.addUser(user);
		}else{
			return false;
		}
	}
	
	//注销用户
	public boolean deleteUser(String id) {
		if(userDao.isExist(id)) {//有此用户，可以进行注销
			return userDao.DeleteUser(id);
		}else {
			return false;
		}
	}
	
	//修改用户信息
	public boolean updateUser(User user) {
		if(userDao.isExist(user.getId())) {//有此用户，可以修改信息
			return userDao.UpdateUser(user);
		}else {
			return false;
		}
	}
	
	//查询某一用户详细信息
	public User queryUserById(String id) {
		return userDao.queryUserById(id);
	}
}
