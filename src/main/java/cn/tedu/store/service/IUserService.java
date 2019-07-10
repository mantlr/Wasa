package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMathException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

/**
 * 业务层接口
 * @author M.ant
 *
 */
public interface IUserService {
	
	/**
	 *  用户注册功能
	 * @param user 用户添加的信息
	 * @throws UsernameDuplicateException 用户名已经存在
	 */
	void reg(User user) throws UsernameDuplicateException,InsertException;
	
	/**
	 * 用户登录功能
	 * @param username	登陆的用户姓名
	 * @param password	用户的密码
	 * @throws UserNotFoundException 用户找不到异常
	 * @throws PasswordNotMathException 密码不匹配异常
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMathException;
	
}
