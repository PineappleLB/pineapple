package club.pinea.utils;

import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 作者 pineapple 
 * @E-mail: 2443755705@qq.com
 * @version 创建时间：2018年2月21日 下午3:59:48
 * @description 类说明
 */
public class RedisTemplateExt<K,V> extends RedisTemplate<K, V>{
	
	private JedisPool jedisPools;

	public JedisPool getJedisPools() {
		return jedisPools;
	}


	public void setJedisPools(JedisPool jedisPools) {
		this.jedisPools = jedisPools;
	}

	public <T> T execute(RedisCallBackImpl<T> action, boolean exposeConnection, boolean pipeline) throws Exception {
		Jedis jedis = jedisPools.getResource();
		try {
			return action.doInRedis(jedis);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
	}
	
	public <T> T execute(RedisCallBackImpl<T> action){
		try {
			return execute(action, false, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 归还对象到连接池
	 * @param jedis
	 */
	@SuppressWarnings("all")
	private void returnResource(Jedis jedis) {
		if (jedis != null && jedisPools!=null) {
			jedisPools.returnResource(jedis);
		}
	}
}
