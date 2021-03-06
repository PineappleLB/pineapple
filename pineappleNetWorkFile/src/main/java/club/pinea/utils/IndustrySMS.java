package club.pinea.utils;

import java.net.URLEncoder;

import club.pinea.SMS.Config;
import club.pinea.SMS.HttpUtil;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	private static String smsContent = "【菠萝云盘】您的验证码为：@phone，请于"+Config.TIME_OUT+"分钟内正确输入，如非本人操作，请忽略此条短信。";

	/**
	 * 验证码通知短信
	 */
	public static void execute(String to,String code)
	{
		String tmpSmsContent = null;
	    try{
	      tmpSmsContent = URLEncoder.encode(smsContent.replace("@phone", code), "UTF-8");
	    }catch(Exception e){
	      
	    }
	    String url = Config.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
	        + HttpUtil.createCommonParam();

	    // 提交请求
	    String result = HttpUtil.post(url, body);
	    System.out.println("result:" + System.lineSeparator() + result);
	}
}
