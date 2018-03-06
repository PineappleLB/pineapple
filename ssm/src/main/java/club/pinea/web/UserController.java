package club.pinea.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import club.pinea.model.User;
import club.pinea.redis.UserDaoR;
import club.pinea.service.IUserService;
import club.pinea.utils.IndustrySMS;
import club.pinea.utils.StringUtils;
import club.pinea.utils.VerifyCode;

@CrossOrigin(origins="*", maxAge=3600)
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserDaoR dao;
	
	/**
	 * 登录验证
	 * @param name 用户名
	 * @param pass 密码
	 * @param session session对象
	 * @param writer 写对象
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void userLogin(@RequestParam("name")String name,@RequestParam("pass")String pass,
			HttpSession session,PrintWriter writer) throws NoSuchAlgorithmException {
		User u = null;
		if(name.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$")) {
			//邮箱登录
			u = userService.selectUserByEmail(name);
		}else if(name.matches("1(3|5|7|8)[0-9]{9}")) {
			//手机号登录
			u = userService.selectUserByPhone(name);
		}else {
			//用户名登录
			u = userService.selectUserByName(name);
		}
		if(u != null && u.getPassword().equals(StringUtils.encode(pass+u.getToken()))) {
			session.setAttribute("user", u);
			dao.saveUser(u);
			writer.write("success");
		}else {
			writer.write("error");
		}
	}
	
	/**
	 * 获取图片验证码
	 * @param session
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping(value="/image")
	public void imageVCode(@RequestParam("random")String random,HttpSession session,HttpServletResponse resp) throws IOException {
		VerifyCode code = new VerifyCode();
		VerifyCode.output(code.getImage(), resp.getOutputStream());
		System.out.println(code.getText());
		dao.saveVCode(random,code.getText());
//		session.setAttribute("vcode", code.getText());
	}
	
	
	/**
	 * 验证图片验证码是否正确
	 * @param vcode
	 * @param session
	 * @param writer
	 */
	@RequestMapping(value="/valVCode",method=RequestMethod.POST)
	public void validateVCode(@RequestParam(value="vcode")String vcode,
			@RequestParam("random")String random,PrintWriter writer) {
		String valVCode = dao.selectVCode(random);
//		System.out.println("val="+valVCode+",code="+vcode+",bool="+vcode.equalsIgnoreCase(valVCode));
		int result = 0;
		if(vcode.equalsIgnoreCase(valVCode)) {
			result = 1;
		}else{
			result=0;
		}
		writer.print(result);
	}
	
	
	/**
	 * 验证用户名是否已经存在
	 * @param name
	 * @param writer
	 */
	@RequestMapping(value="/valName",method=RequestMethod.POST)
	public void validateName(@RequestParam("username")String name,PrintWriter writer) {
		System.out.println("请求进入了验证");
		int result = 0;
		if(name.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$")) {
			//邮箱登录
			result = userService.validateUserEmail(name);
		}else if(name.matches("1(3|5|7|8)[0-9]{9}")) {
			//手机号登录
			result = userService.validateUserPhone(name);
		}else {
			//用户名登录
			result = userService.validateUserName(name);
		}
		writer.print(result);
		System.out.println(result);
	}
	
	
	/**
	 * 发送短信验证码
	 * @param phone
	 * @param writer
	 */
	@RequestMapping(value="/sendPCode", method=RequestMethod.POST)
	public void sendPhoneCode(@RequestParam(value="phone")String phone,
			@RequestParam("random")String random, PrintWriter writer) {
		
		System.out.println(phone);
		String code = UUID.randomUUID().toString().
				replace("-", "").substring(0, 6);
		try {
			IndustrySMS.execute(phone, code);
			writer.print(dao.savePCode(random, code));//将验证码存储到redis缓存当中
		} catch (Exception e) {
			e.printStackTrace();
			writer.print(0);
		}
	}
	
	
	/**
	 * 验证手机号是否已经存在
	 * @param name
	 * @param writer
	 */
	@RequestMapping(value="/valPhone",method=RequestMethod.POST)
	public void validatePhone(@RequestParam("phone")String phone,PrintWriter writer) {
		int result = 0;
		if(!phone.matches("1(3|5|7|8)[0-9]{9}")) {
			//手机号登录
			result=0;
		}else {
			result = userService.validateUserPhone(phone);
		}
		writer.print(result);
	}
	
	/**
	 * 验证邮箱是否已经存在
	 * @param email
	 * @param writer
	 */
	@RequestMapping(value="/valEmail",method=RequestMethod.POST)
	public void validateEmail(@RequestParam("email")String email,PrintWriter writer) {
		int result = 0;
		if(email.matches("^(\\\\w)+(\\\\.\\\\w+)*@(\\\\w)+((\\\\.\\\\w+)+)$")) {
			result = userService.validateUserEmail(email);
		}else {
			result = 0;
		}
		writer.write(result);
	}

	/**
	 * 注册用户
	 * @param u 用户对象
	 * @param vcode
	 * @param pcode
	 * @param request
	 * @param writer
	 */
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public void regist(User u, @RequestParam("random")String random,
			@RequestParam("vcode")String vcode,@RequestParam("pcode")String pcode,
			PrintWriter writer) {
		if(!(vcode.equals(dao.selectVCode(random)))) {
			writer.write(0);
		}else if(!pcode.equals(dao.selectPCode(random))) {
			writer.write(0);
		}else if(userService.validateUserName(u.getUsername())==1 ||
				userService.validateUserEmail(u.getEmail())==1 || 
				userService.validateUserPhone(u.getPhone())==1) {
			writer.write(0);
		}else {
			int result = userService.saveUser(u);
			writer.write(result);
		}
	}
	
	
	/**
	 * 更新密码操作
	 * @param name
	 * @param phone
	 * @param vcode
	 * @param pcode
	 * @param pass
	 * @param writer
	 */
	public void updatePassWord(User u, @RequestParam("vcode")String vcode, @RequestParam("pcode")String pcode,
			@RequestParam("random")String random, PrintWriter writer) {
		if(dao.selectPCode(random).equalsIgnoreCase(pcode)) {
			dao.deletePCode(random);
			if(dao.selectVCode(random).equals(vcode)) {
				dao.deleteVCode(random);
				if(userService.updateUserPassword(u) > 0) {
					
				}
			}
		}
	}
	
	/**
	 * 判断手机验证码是否正确，正确返回1，否则返回0
	 * @param vcode
	 * @param req
	 * @param writer
	 * @param session
	 */
	@RequestMapping(value="valPCode",method=RequestMethod.POST)
	public void validatePCode(@RequestParam("pcode")String pcode,
			@RequestParam("random")String random, PrintWriter writer) {
		String reVCode = dao.selectPCode(random);
		if(pcode.equals(reVCode)) {
			writer.write(1);
		}else {
			writer.write(0);
		}
	}
	
}
