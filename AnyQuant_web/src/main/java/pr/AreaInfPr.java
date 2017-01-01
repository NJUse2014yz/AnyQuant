package pr;

import mapper.AreaInfMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.AreaInfPack;
import tool.JsonExchangeTool;

public class AreaInfPr {
	private ApplicationContext applicationContext;
	private AreaInfMapper areaInfMapper;
	
	/**创建对象时为mapper注入对象*/
	public AreaInfPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		areaInfMapper=(AreaInfMapper) applicationContext.getBean("areaInfMapper");
	}
	
	/**创建地域表并赋初值 */
	public void initAreaInfTable() throws Exception
	{
		areaInfMapper.createAreaInfTable();
		AreaInfPack areaInfPack=new AreaInfPack();
		areaInfPack.setAreaInfList(JsonExchangeTool.getAreaInf());
		areaInfMapper.initAreaInfTable(areaInfPack);
		
		areaInfMapper.deleteAreaInf_st();
		areaInfMapper.deleteAreaInf_single("sh603339");
		areaInfMapper.deleteAreaInf_single("sz300512");
		areaInfMapper.deleteAreaInf_single("sz002798");
		areaInfMapper.deleteAreaInf_single("sh603959");
		areaInfMapper.deleteAreaInf_single("sh603779");
		areaInfMapper.deleteAreaInf_single("sh603131");
		areaInfMapper.deleteAreaInf_single("sh601611");
		areaInfMapper.deleteAreaInf_single("sz300513");
		areaInfMapper.deleteAreaInf_single("sz002800");
		areaInfMapper.deleteAreaInf_single("sz300516");
		areaInfMapper.deleteAreaInf_single("sz002799");
		areaInfMapper.deleteAreaInf_single("sh603737");
	}	
	public static void main(String[] args)
	{
		try {
			new AreaInfPr().initAreaInfTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
