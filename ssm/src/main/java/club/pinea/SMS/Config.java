package club.pinea.SMS;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 系统常量
 */
public class Config
{
	/**
	 * url前半部分
	 */
	public static String BASE_URL = "https://api.miaodiyun.com/20150822";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static String ACCOUNT_SID = "e9d9df05723f451f887adcabdbf65d62";

	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static String AUTH_TOKEN = "9313ef59d31a44f9bf5ff5ecab5b5d8e";

	/**
	 * 响应数据类型, JSON或XML
	 */
	public static String RESP_DATA_TYPE = "json";
	
	/**
	 * 短信的过期时间 /分钟
	 */
	public static int TIME_OUT = 10;
	
	/**
	 * redis缓存中存储已登录的用户信息key
	 */
	public static String LOGIN_USER_INFO_KEY_PREV = "pinea_USER";
	
	/**
	 * redis缓存中手机号验证码存储key
	 */
	public static String PHONE_CODE_INFO_KEY_PREV = "club.pinea_PHONE";
	
	/**
	 * redis缓存中图片验证码存储key
	 */
	public static String PIC_CODE_INFO_KEY_PREV = "club.pinea.PICTURE";
	
	/**
	 * 加载属性的配置文件
	 */
	private static Properties props;
	
	static {
		props = new Properties();
		try {
			String str = null;
			props.load(new FileInputStream("classpath:applicationConfig.properties"));
			if(!props.isEmpty()) {
				str = props.getProperty("SMS_BASE_URL");
				if(str != null && str.length() > 0) {
					BASE_URL = str;
				}
				str = props.getProperty("SMS_ACCOUNT_SID");
				if(str != null && str.length() > 0) {
					ACCOUNT_SID = str;
				}
				str = props.getProperty("SMS_AUTH_TOKEN");
				if(str != null && str.length() > 0) {
					AUTH_TOKEN = str;
				}
				str = props.getProperty("SMS_RESP_DATA_TYPE");
				if(str != null && str.length() > 0) {
					RESP_DATA_TYPE = str;
				}
				str = props.getProperty("SMS_TIME_OUT");
				if(str != null && str.length() > 0) {
					TIME_OUT = Integer.parseInt(str);
				}
				str = props.getProperty("LOGIN_USER_INFO_KEY_PREV");
				if(str != null && str.length() > 0) {
					LOGIN_USER_INFO_KEY_PREV = str;
				}
				str = props.getProperty("PHONE_CODE_INFO_KEY_PREV");
				if(str != null && str.length() > 0) {
					PHONE_CODE_INFO_KEY_PREV = str;
				}
				str = props.getProperty("PIC_CODE_INFO_KEY_PREV");
				if(str != null && str.length() > 0) {
					PIC_CODE_INFO_KEY_PREV = str;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}