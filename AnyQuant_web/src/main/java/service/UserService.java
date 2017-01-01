package service;

import java.util.List;

import vo.UserStrategy;

public interface UserService {
	//增加用户账户
	public void addUser(String Userid,String password) throws Exception;
	//登录
	public boolean LoginUser(String Userid,String password) throws Exception;
	//返回User的关注股票列表名称
	public List<String> getAllStock(String Userid) throws Exception;
//	//添加User的关注股票
//	public void addUserStock(String stockId)throws Exception;
//	//删除User的关注股票
//	public void deleteUserStock(String stockId)throws Exception;
	//改变User的关注股票的状态
	public void changeUserStock(String stockId)throws Exception;
	//返回User的策略列表名称
	public List<String> getAllStrategy(String Userid)throws Exception;
	//返回策略的具体信息
	public UserStrategy getUserStrategy(String UserStrategyId)throws Exception;
}