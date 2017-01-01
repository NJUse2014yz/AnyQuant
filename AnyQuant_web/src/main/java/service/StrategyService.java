package service;

import java.util.List;

import vo.StrategyRecInf;
import vo.UserStrategy;
import vo.UserStrategyProperty;

public interface StrategyService {
	public List<StrategyRecInf> getRecStrategy(String id) throws Exception;
	public UserStrategy createUserStrategy(String UserName,UserStrategyProperty property);
}
