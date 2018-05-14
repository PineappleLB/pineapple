package club.pinea.utils;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis操作模板
 * 
 * @author Administrator
 *
 */
@Service("redisTemplate")
public class RedisTemplate {

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}


	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}


	/**
	 * 进行查询操作
	 * @param callback
	 * @return
	 */
	public <T> T execute(RedisCallback<T> callback) {
		Jedis jedis = jedisPool.getResource();
		try {
			return callback.handle(jedis);
		} catch (Exception e) {
			// throw your exception
			throw new IllegalArgumentException(e);
		} finally {
			returnResource(jedis);
		}
	}

	
	public void update(RedisUpdate update){
		Jedis jedis = jedisPool.getResource();
		try {
			update.update(jedis);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 归还对象到连接池
	 * @param jedis
	 */
	@SuppressWarnings("all")
	private void returnResource(Jedis jedis) {
		if (jedis != null && jedisPool!=null) {
			jedisPool.returnResource(jedis);
		}
	}
}