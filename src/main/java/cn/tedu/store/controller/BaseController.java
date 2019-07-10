package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMathException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
import cn.tedu.store.util.JsonResult;

/**
 * 控制器的基类
 * @author M.ant
 *
 */
public abstract class BaseController {
	
	/**
	 * 操作成功时的响应状态代号
	 */
	protected static final Integer SUCCESS = 2000;
	
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}

	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}

	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult<Void> handleException(Throwable e){
		
		JsonResult<Void> jr=new JsonResult<>(e);
		jr.setMessage(e.getMessage());
		
		if (e instanceof UsernameDuplicateException) {
			jr.setState(4000);
		} else if (e instanceof UserNotFoundException) {
			jr.setState(4001);
		} else if (e instanceof PasswordNotMathException) {
			jr.setState(4002);
		} else if (e instanceof InsertException) {
			jr.setState(5000);
		}
		return jr;
	}
	
}
