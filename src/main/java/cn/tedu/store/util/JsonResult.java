package cn.tedu.store.util;

/**
 * 响应结果的类
 * @author M.ant
 *
 * @param <T>
 */
public class JsonResult<T> {
	
	private Integer state;
	private String  message;
	private T data;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	public JsonResult(Integer state) {
		super();
		this.state = state;
	}
	public JsonResult(String message) {
		super();
		this.message = message;
	}
	
	public JsonResult(Throwable e) {
		super();
		this.message = e.getMessage();
	}
	
	
	
}
