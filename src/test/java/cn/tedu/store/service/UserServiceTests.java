package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMathException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UserNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	IUserService service;
	
	@Test 
	public void reg() {
		try {
			String username="赵五";
			String password="1234";
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			service.reg(user);
			System.err.println("OK!");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void  login() {
		
		try {
			String username="赵五";
			String password="1234";
			User result=service.login(username, password);
			System.err.println(result);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		} 
		
	}
	
	
	
	
}
