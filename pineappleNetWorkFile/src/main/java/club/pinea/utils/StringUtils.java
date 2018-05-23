package club.pinea.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * @author 作者 pineapple 
 * @E-mail: 2443755705@qq.com
 * @version 创建时间：2018年2月15日 上午10:32:45
 * @description 类说明
 */
@SuppressWarnings("restriction")
public class StringUtils {

	/**
	 * MD5加密
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encode(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = str.getBytes();
		byte[] encode = md.digest(bytes);
		return new BASE64Encoder().encode(encode);
	}
	
}
