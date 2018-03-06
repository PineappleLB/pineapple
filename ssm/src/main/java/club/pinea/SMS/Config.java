package club.pinea.SMS;

/**
 * 系统常量
 */
public class Config
{
	/**
	 * url前半部分
	 */
	public static final String BASE_URL = "https://api.miaodiyun.com/20150822";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String ACCOUNT_SID = "e9d9df05723f451f887adcabdbf65d62";

	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String AUTH_TOKEN = "9313ef59d31a44f9bf5ff5ecab5b5d8e";

	/**
	 * 响应数据类型, JSON或XML
	 */
	public static final String RESP_DATA_TYPE = "json";
	
	/**
	 * 短信的过期时间
	 */
	public static final int TIME_OUT = 10;
}