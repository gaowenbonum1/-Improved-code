package cn.gaowenbonum1.service;

import cn.gaowenbonum1.dao.UserDao;
import cn.gaowenbonum1.dao.UserDaoImple;
import cn.gaowenbonum1.vo.User;

public class UserService {

	public User login(User user) {
		UserDao dao = new UserDaoImple();
		return dao.login(user);
	}
	
	
}
