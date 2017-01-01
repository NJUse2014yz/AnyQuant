package mapper;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import po.DatePack;
import po.HistoryData;
import po.HistoryDataPack;
import po.HistoryDataTrack;
import po.RealTimeData;
import po.RealTimeDataPack;
import po.RealTimeDataTrack;

/**
 * @table realtimedata_${sid} realtimedata_${iid} realtimetrack
 * @po realtimedata
 * @author YU Fan
 * @date 2016年5月9日
 */
public interface RealTimeDataMapper {
	/**创建实时数据表*/
	public void createRealTimeDataTable(String sid) throws Exception;
	/**删除实时数据表*/
	public void dropRealTimeDataTable(String sid) throws Exception;

	/**初始化实时数据表*/
	public void initRealTimeDataTable(String sid) throws Exception;
	/**批量初始化实时数据表*/
	public void initRealTimeData_all(List<String> list) throws Exception;
	
	/**插入实时数据-单条*/
	public void insertRealTimeData_single(RealTimeDataPack realTimeDataPack) throws Exception;
	/**插入实时数据-多表多条*/
	public void insertRealTimeData_all(RealTimeDataPack realTimeDataPack) throws Exception;
	
	/**删除实时数据-=time*/
	public void deleteRealTimeData_e_time(DatePack datePack) throws Exception;
	
	/**查找实时数据date*/
	public Date selectRealTimeDataDate(String siid) throws Exception;
	/**查找实时数据最近时间*/
	public Time selectRealTimeDataTime_max(String siid) throws Exception;

	/**查找实时数据-all*/
	public List<RealTimeData> selectRealTimeData_all(DatePack datePack) throws Exception;
	/**查找实时数据-=time*/
	public RealTimeData selectRealTimeData_e_time(DatePack datePack) throws Exception;
	/**查找实时数据->=time*/
	public List<RealTimeData> selectRealTimeData_ge_time(DatePack datePack) throws Exception;
	/**查找实时数据-<=time*/
	public List<RealTimeData> selectRealTimeData_le_time(DatePack datePack) throws Exception;
	/**查找实时数据->time*/
	public List<RealTimeData> selectRealTimeData_g_time(DatePack datePack) throws Exception;
	/**查找实时数据-<time*/
	public List<RealTimeData> selectRealTimeData_l_time(DatePack datePack) throws Exception;
	
	/**查找实时数据-><time*/
	public List<RealTimeData> selectRealTimeData_b_time(DatePack datePack) throws Exception;

}
