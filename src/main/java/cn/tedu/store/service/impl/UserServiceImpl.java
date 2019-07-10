package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMathException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		// 根据参数user中的getUsername()获取尝试注册的用户名
		String name = user.getUsername();
		// 根据以上用户名查询用户数据
		User result = mapper.findByUsername(name);
		// 判断查询结果是否不为null
		if (result != null) {
			// 是：用户名已经被占用，抛出UsernameDuplicateException
			throw new UsernameDuplicateException("添加用户失败，用户名已存在！");
		}
		// 用户名未被占用，允许注册
		// 向参数user中补全属性：盐值
		String salt = UUID.randomUUID().toString().toUpperCase();
		user.setSalt(salt);
		// 取出参数user中的原始密码
		String password = user.getPassword();
		// 将原始密码加密
		String Md5Password = getMd5Password(password, salt);
		// 向参数user中补全属性：加密后的密码
		user.setPassword(Md5Password);
		// 向参数user中补全属性：isDelete-0
		user.setIsDelete(0);
		// 向参数user中补全属性：4项日志
		Date date = new Date();
		user.setCreatedUser(name);
		user.setCreatedTime(date);
		user.setModifiedUser(name);
		user.setModifiedTime(date);
		// 执行注册
		Integer rows = mapper.addnew(user);
		if (rows != 1) {
			throw new InsertException("添加用户失败，出现未知异常");
		}
	}

	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMathException {
		// 根据参数username执行查询
		User result=mapper.findByUsername(username);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("登陆失败，用户名不存在！");
		}
		// 判断查询结果中的isDelete是否为1
		if(result.getIsDelete()==1) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("登陆失败，用户名不存在！");
		}
		// 从查询结果中获取盐值
		String salt=result.getSalt();
		// 基于参数password和盐值执行加密
		String Md5Password=getMd5Password(password, salt);
		// 判断以上加密结果与查询结果中的password是否不匹配
		if(!Md5Password.equals(result.getPassword())) {
			// 是：抛出PasswordNotMatchException
			throw new PasswordNotMathException("登陆失败，密码不匹配！");
		}
		// 将查询结果中的password设置为null
		result.setPassword(null);
		// 将查询结果中的salt设置为null
		result.setSalt(null);
		// 将查询结果中的isDelete设置为null
		result.setIsDelete(null);
		// 返回查询结果
		return result;
		
	}

	private String getMd5Password(String password, String salt) {
		// 加密规则：使用“salt+password+salt”作为消息，执行3次摘要运算
		String str = salt + password + salt;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}

}
