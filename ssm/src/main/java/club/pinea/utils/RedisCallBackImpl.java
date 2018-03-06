package club.pinea.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import redis.clients.jedis.Jedis;

/**
 * @author 作者 pineapple 
 * @E-mail: 2443755705@qq.com
 * @version 创建时间：2018年2月21日 下午3:53:28
 * @description 类说明
 */
public interface RedisCallBackImpl<T> extends RedisCallback<T> {

	default T doInRedis(RedisConnection conn) throws DataAccessException {
		return null;
	}
	
	T doInRedis(Jedis jedis) throws DataAccessException;
	
}
