package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@RequestMapping("reg")
	public JsonResult<Void> reg(User user) {
		userService.reg(user);
		

		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("login")
	public JsonResult<User> login(String username, String password, HttpSession session) {

		// 调用业务层对象的“登录”方法，获取返回结果
		User data=userService.login(username, password);
		// 向Session中存入用户id和用户名
		session.setAttribute("uid", data.getUid());
		session.setAttribute("username", data.getUsername());
		// 返回
		return new JsonResult<>(SUCCESS, data);
	}
	

}
