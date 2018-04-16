package club.pinea.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import club.pinea.SMS.Config;
import club.pinea.model.User;
import club.pinea.redis.UserDaoR;
import redis.clients.jedis.JedisCluster;

/**
 * @author 作者 pineapple 
 * @E-mail: 2443755705@qq.com
 * @version 创建时间：2018年2月16日 下午11:32:15
 * @description 类说明
 */
@Service
public class UserDaoRImpl implements UserDaoR {

	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public void saveUser(User u) {
		//15分钟过期
		jedisCluster.setex("USER" + u.getId(), Config.TIME_OUT * 60, JSONObject.toJSONString(u));
	}
	
	@Override
	public User selectUserById(int id) {
		String str = jedisCluster.get("USER" + id);
		User u = JSONObject.parseObject(str, User.class);
		return u;
	}

	@Override
	public int savePCode(String random, String code) {
		String key = ("P_CODE"+random).replace(".", "");
		System.out.println(key);
		return jedisCluster.setex(key, Config.TIME_OUT * 60, code).equals("OK")?1:0;
	}

	@Override
	public void saveVCode(String random, String text) {
		String key = ("V_CODE"+random).replace(".", "");
		System.out.println(key);
		jedisCluster.set(key/*, Config.TIME_OUT * 60*/, text);
	}

	@Override
	public String selectVCode(String random) {
		String key = ("V_CODE"+random).replace(".", "");
		System.out.println(key);
		return jedisCluster.get(key);
	}

	@Override
	public String selectPCode(String random) {
		String key = ("P_CODE"+random).replace(".", "");
		System.out.println(key);
		return jedisCluster.get(key);
	}

	@Override
	public void deletePCode(String random) {
		String key = ("P_CODE"+random).replace(".", "");
		System.out.println(key);
		jedisCluster.del(key);
		
	}

	@Override
	public void deleteVCode(String random) {
		String key = ("V_CODE"+random).replace(".", "");
		System.out.println(key);
		jedisCluster.del(key);
	}
	
}
