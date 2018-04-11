package club.pinea.test;

import java.security.NoSuchAlgorithmException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import club.pinea.model.User;
import club.pinea.redis.UserDaoR;
import club.pinea.service.IUserService;
import club.pinea.utils.RedisCallback;
import club.pinea.utils.RedisTemplate;
import club.pinea.utils.VerifyCode;
import redis.clients.jedis.Jedis;

/**
 * @author 作者 pineapple 
 * @E-mail: 2443755705@qq.com
 * @version 创建时间：2018年2月15日 上午10:29:25
 * @description 类说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@SuppressWarnings("all")
public class Test {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private RedisTemplate redisClusterTemplate;
	
	@Autowired
	private UserDaoR dao;
	
	
	@org.junit.Test
	public void test() throws NoSuchAlgorithmException {
//		String uuid = UUID.randomUUID().toString().replace("-", "");
//		String password = StringUtils.encode("123456"+uuid);
//		System.out.println(uuid+","+password);
		VerifyCode code = new VerifyCode();
		code.getImage();
		System.out.println(code.getText());
	}
	
	@org.junit.Test
	public void test1() {
		User u = service.selectUserByEmail("2443755705@qq.com");
		System.out.println(service);
		System.out.println(u);
		System.out.println(JSONObject.toJSONString(u));
	}
	
	@org.junit.Test
	public void test3() {
		String result = redisClusterTemplate.execute((jedis) -> {
				return jedis.set("123", "123");
		});
		System.out.println(result + "test------------------------");
	}
	
}
