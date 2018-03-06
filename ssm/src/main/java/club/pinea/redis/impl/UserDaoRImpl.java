package club.pinea.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import club.pinea.SMS.Config;
import club.pinea.model.User;
import club.pinea.redis.UserDaoR;
import club.pinea.utils.RedisTemplate;

/**
 * @author 作者 pineapple 
 * @E-mail: 2443755705@qq.com
 * @version 创建时间：2018年2月16日 下午11:32:15
 * @description 类说明
 */
@Service
public class UserDaoRImpl implements UserDaoR {

	@Autowired
	private RedisTemplate jedis;
	
	@Override
	public void saveUser(User u) {
		//15分钟过期
		jedis.update((jedis)->{
//			jedis.opsForValue().set(u.getId()+"", JSONObject.toJSONString(u),900,TimeUnit.SECONDS);
			jedis.setex("user"+u.getId(), Config.TIME_OUT * 60, JSONObject.toJSONString(u));
		});
	}
	
	@Override
	public User selectUserById(int id) {
		String str = jedis.execute((jedis) -> {
			return jedis.get("user"+id);
		});
		User u = JSONObject.parseObject(str, User.class);
		return u;
	}

	@Override
	public int savePCode(String random, String code) {
		return jedis.execute((jedis) -> {
			return jedis.setex("pcode"+random, Config.TIME_OUT * 60, code).equals("OK")?1:0;
		});
	}

	@Override
	public void saveVCode(String random, String text) {
		jedis.update((jedis)->{
			jedis.setex("vcode"+random, Config.TIME_OUT * 60, text);
		});
		
	}

	@Override
	public String selectVCode(String random) {
		return jedis.execute((jedis) -> {
			return jedis.get("vcode"+random);
		});
	}

	@Override
	public String selectPCode(String random) {
		return jedis.execute((jedis) -> {
				return jedis.get("pcode"+random);
			});
	}

	@Override
	public void deletePCode(String random) {
		jedis.update((jedis)->{
			jedis.del("pcode"+random);
		});
		
	}

	@Override
	public void deleteVCode(String random) {
		jedis.update((jedis) -> {
			jedis.del("vcode"+random);
		});
		
	}
	
}
