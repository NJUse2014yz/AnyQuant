package pr;

import mapper.IndiceInfMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IndiceInfPr {
	private ApplicationContext applicationContext;
	private IndiceInfMapper indiceInfMapper;
	
	/**创建对象时为mapper注入对象*/
	public IndiceInfPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		indiceInfMapper=(IndiceInfMapper) applicationContext.getBean("indiceInfMapper");
	}
	
	/**创建地域表并赋初值 */
	public void initIndiceInfTable() throws Exception
	{
		indiceInfMapper.createIndiceInfTable();
		indiceInfMapper.initIndiceInfTable();
	}	
}
