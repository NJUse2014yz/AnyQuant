package bl;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vo.QuotaAnalysis;
import mapper.QuotaDataMapper;

/**
 * 所有指标分析的父类
 * @author YU Fan
 * @date 2016年6月9日
 */
public abstract class QuotaAnalyse {
	protected ApplicationContext applicationContext;
	protected QuotaDataMapper quotaDataMapper;
	protected String type;
	protected String siid;
	protected Date enddate;
	protected QuotaAnalysis analysis;
	
	public QuotaAnalyse(String siid)
	{
		this.applicationContext=new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		this.quotaDataMapper=(QuotaDataMapper) applicationContext.getBean("quotaDataMapper");
		this.type="s";
		this.siid=siid;
		this.enddate=new Date(Calendar.getInstance().getTimeInMillis());
		this.analysis=new QuotaAnalysis();
	}
	public QuotaAnalyse(String type,String siid)
	{
		this.applicationContext=new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		this.quotaDataMapper=(QuotaDataMapper) applicationContext.getBean("quotaDataMapper");
		this.type=type;
		this.siid=siid;
		this.enddate=new Date(Calendar.getInstance().getTimeInMillis());
		this.analysis=new QuotaAnalysis();
	}
	public QuotaAnalyse(String siid,Date enddate)
	{
		this.applicationContext=new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		this.quotaDataMapper=(QuotaDataMapper) applicationContext.getBean("quotaDataMapper");
		this.type="s";
		this.siid=siid;
		this.enddate=enddate;
		this.analysis=new QuotaAnalysis();
	}
	public QuotaAnalyse(String type,String siid,Date enddate)
	{
		this.applicationContext=new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		this.quotaDataMapper=(QuotaDataMapper) applicationContext.getBean("quotaDataMapper");
		this.type=type;
		this.siid=siid;
		this.enddate=enddate;
		this.analysis=new QuotaAnalysis();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSiid() {
		return siid;
	}
	public void setSiid(String siid) {
		this.siid = siid;
	}
	public QuotaAnalysis getAnalysis() {
		return analysis;
	}
	public void setAnalysis(QuotaAnalysis analysis) {
		this.analysis = analysis;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public abstract QuotaAnalysis pro_quo() throws Exception;
}
