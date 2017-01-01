package pr;

import mapper.IndustryInfMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.IndustryInfPack;
import tool.JsonExchangeTool;

public class IndustryInfPr {
	private ApplicationContext applicationContext;
	private IndustryInfMapper industryInfMapper;
	
	/**创建对象时为mapper注入对象*/
	public IndustryInfPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		industryInfMapper=(IndustryInfMapper) applicationContext.getBean("industryInfMapper");
	}
	
	/**创建行业表并赋初值 */
	public void initIndustryInfTable() throws Exception
	{
		industryInfMapper.createIndustryInfTable();
		IndustryInfPack industryInfPack=new IndustryInfPack();
		industryInfPack.setIndustryInfList(JsonExchangeTool.getIndustryInf());
		industryInfMapper.initIndustryInfTable(industryInfPack);
		
		industryInfMapper.deleteIndustryInf_st();
		industryInfMapper.deleteIndustryInf_single("sh603339");
		industryInfMapper.deleteIndustryInf_single("sz300512");
		industryInfMapper.deleteIndustryInf_single("sz002798");
		industryInfMapper.deleteIndustryInf_single("sh603959");
		industryInfMapper.deleteIndustryInf_single("sh603779");
		industryInfMapper.deleteIndustryInf_single("sh600656");
	}
	public static void main(String[] args)
	{
		try {
			new IndustryInfPr().initIndustryInfTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
