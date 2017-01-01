package pr;

import mapper.ConceptInfMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.ConceptInfPack;
import tool.JsonExchangeTool;

public class ConceptInfPr {
	private ApplicationContext applicationContext;
	private ConceptInfMapper conceptInfMapper;
	
	/**创建对象时为mapper注入对象*/
	public ConceptInfPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		conceptInfMapper=(ConceptInfMapper) applicationContext.getBean("conceptInfMapper");
	}
	
	/**创建概念表并赋初值 */
	public void initConceptInfTable() throws Exception
	{
		conceptInfMapper.createConceptInfTable();
		ConceptInfPack conceptInfPack=new ConceptInfPack();
		conceptInfPack.setConceptInfList(JsonExchangeTool.getConceptInf());
		conceptInfMapper.initConceptInfTable(conceptInfPack);
		
		conceptInfMapper.deleteConceptInf_st();
		conceptInfMapper.deleteConceptInf_single("sh603339");
		conceptInfMapper.deleteConceptInf_single("sh600656");
	}
	public static void main(String[] args)
	{
		try {
			new ConceptInfPr().initConceptInfTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
