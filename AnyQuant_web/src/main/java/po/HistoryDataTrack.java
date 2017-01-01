package po;
/**
 * @for 历史数据追踪记录信息
 * @member 类型 type(""), 编号id(-1)
 * @author YU Fan
 * @date 2016年5月19日
 */
public class HistoryDataTrack {
	/**类型 "s"或"i"*/
	private String type;
	/**编号*/
	private int id;
	
	public HistoryDataTrack() {
		super();
		this.type = "";
		this.id = -1;
	}
	public HistoryDataTrack(String type, int id) {
		super();
		this.type = type;
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "HistoryDataTrack [type=" + type + ", id=" + id + "]";
	}
}
