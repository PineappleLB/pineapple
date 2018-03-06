package club.pinea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pinea.dao.UserMapper;
import club.pinea.model.User;
import club.pinea.service.IUserService;

@Service("userService")
public class IUserServiceImpl  implements IUserService{

	@Autowired
	private UserMapper mapper;
	
	public User selectUserById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	public User selectUserByName(String name) {
		return mapper.selectByName(name);
	}

	public User selectUserByEmail(String email) {
		return mapper.selectByEmail(email);
	}

	public User selectUserByPhone(String phone) {
		return mapper.selectByPhone(phone);
	}
	
	public int validateUserName(String name) {
		return mapper.validateUserName(name);
	}

	public int updateByPrimaryKeySelective(User record) {
		return mapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int validateUserPhone(String phone) {
		return mapper.validateUserPhone(phone);
	}

	public int validateUserEmail(String email) {
		return mapper.validateUserEmail(email);
	}

	public int saveUser(User u) {
		return mapper.insert(u);
	}

	@Override
	public int updateUserPassword(User u) {
		return mapper.updateUserPassword(u);
	}



}
