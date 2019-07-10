package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
	
	@Autowired
	UserMapper mapper;
	
	@Test
	public void addnew() {
		String username="张三";
		String password="1234";
		String phone="18538738838";
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		Integer rows=mapper.addnew(user);
		System.err.println("rows:"+rows);
	}
	
	@Test
	public void findByUsername() {
		String username="张三";
		User user=mapper.findByUsername(username);
		System.err.println(user);
	}
	
	
	
	
	
}
