package club.pinea.dao;

import club.pinea.model.User;

public interface UserMapper {
	
	/**
	 * 根据主键删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 选择性的添加用户
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
	 * 根据id查询user对象
	 * @param id 用户id
	 * @return 返回查询到的user对象
	 */
    User selectByPrimaryKey(Integer id);

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
    int updateByPrimaryKey(User record);

    /**
	 * 根据名称查询user对象
	 * @param name 姓名
	 * @return 返回查询到的user对象
	 */
	User selectByName(String name);

	/**
	 * 根据邮箱查询user对象
	 * @param email 邮箱号码
	 * @return 返回查询到的user对象
	 */
	User selectByEmail(String email);

	/**
	 * 根据手机号码查询user对象
	 * @param phone 手机号码参数
	 * @return 返回查询到的user对象
	 */
	User selectByPhone(String phone);

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
	 * 更新用户密码
	 * @param u
	 * @return
	 */
	int updateUserPassword(User u);
}