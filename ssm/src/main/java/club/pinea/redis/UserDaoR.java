package club.pinea.redis;

import club.pinea.model.User;

/**
 * @author 作者 pineapple 
 * @E-mail: 2443755705@qq.com
 * @version 创建时间：2018年2月16日 下午11:31:36
 * @description 类说明
 */
public interface UserDaoR {

	/**
	 * 保存用户id
	 * @param u
	 */
	void saveUser(User u);
	
	/**
	 * 根据用户id查询用户对象
	 * @param id
	 * @return
	 */
	User selectUserById(int id);

	/**
	 * 保存手机验证码
	 * 将验证码保存在session中，并设置过期时间
	 * @param random
	 * @param code
	 * @return
	 */
	int savePCode(String random, String code);

	/**
	 * 保存图片验证码
	 * @param random
	 * @param text
	 */
	void saveVCode(String random, String text);

	/**
	 * 查询redis中的图片验证码信息
	 * @param random
	 * @return
	 */
	String selectVCode(String random);

	/**
	 * 查询手机验证码
	 * @param random
	 * @return
	 */
	String selectPCode(String random);

	/**
	 * 删除手机验证码缓存
	 * @param random
	 */
	void deletePCode(String random);

	/**
	 * 删除图片验证码缓存
	 * @param random
	 */
	void deleteVCode(String random);
	
	
}
