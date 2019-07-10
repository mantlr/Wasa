package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
	
	/**
	 * 插入用户信息
	 * @param user 用户信息数据
	 * @return 受影响的条数
	 */
	Integer addnew(User user);
	
	/**
	 * 根据用户姓名查找用户信息是否存在
	 * @param username 返回用户数据
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * 更改用户的密码
	 * @param uid 要修改密码的用户id
	 * @param password 需要修该的密码
	 * @param modifiedUser 最后修改的用户
	 * @param modifiedTime 最后修改的时间
	 * @return 返回受影响的条数
	 */
	Integer updatePassword(
			@Param("uid") Integer uid, 
			@Param("password") String password, 
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
}
