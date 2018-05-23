package club.pinea.service;

import org.springframework.stereotype.Service;

import club.pinea.model.User;

/**
 * 用户服务类
 * @author Administrator
 *
 */
@Service
public interface IUserService {
	
	/**
	 * 根据主键删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);
	
	/**
	 * 根据主键更新用户信息
	 * @param record 更新的用户对象
	 * @return 返回受影响的行数
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * 根据id查询user对象
	 * @param id 用户id
	 * @return 返回查询到的user对象
	 */
	User selectUserById(int id);
	
	/**
	 * 根据名称查询user对象
	 * @param name 姓名
	 * @return 返回查询到的user对象
	 */
	User selectUserByName(String name);
	
	/**
	 * 根据邮箱查询user对象
	 * @param email 邮箱号码
	 * @return 返回查询到的user对象
	 */
	User selectUserByEmail(String email);

	/**
	 * 根据手机号码查询user对象
	 * @param phone 手机号码参数
	 * @return 返回查询到的user对象
	 */
	User selectUserByPhone(String phone);

	/**
	 * 验证用户名是否存在
	 * @param name 用户名
	 * @return 返回该用户名有多少个
	 */
	int validateUserName(String name);

	/**
	 * 验证手机号是否已经被使用
	 * @param phone
	 * @return
	 */
	int validateUserPhone(String phone);

	/**
	 * 验证邮箱是否已经被使用
	 * @param email
	 * @return
	 */
	int validateUserEmail(String email);

	/**
	 * 用户注册
	 * @param u
	 * @return
	 */
	int saveUser(User u);

	/**
	 * 更新用户密码
	 * @param u
	 * @return
	 */
	int updateUserPassword(User u);
	
}

