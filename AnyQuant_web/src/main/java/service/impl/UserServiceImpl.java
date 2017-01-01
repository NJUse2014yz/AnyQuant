package service.impl;

import java.util.List;

import service.UserService;
import vo.UserStrategy;

public class UserServiceImpl implements UserService {
	@Override
	// 增加用户账户
	public void addUser(String Userid, String password) throws Exception {
	}

	@Override
	// 登录
	public boolean LoginUser(String Userid, String password) throws Exception {
		return false;
	}

	@Override
	// 返回User的关注股票列表名称
	public List<String> getAllStock(String Userid) throws Exception {
		return null;
	}

	@Override
	// 返回User的策略列表名称
	public List<String> getAllStrategy(String Userid) throws Exception {
		return null;
	}

	@Override
	// 返回策略的具体信息
	public UserStrategy getUserStrategy(String UserStrategyId) throws Exception {
		return null;
	}

	@Override
	public void changeUserStock(String stockId) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
