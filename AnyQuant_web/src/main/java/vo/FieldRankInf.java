package vo;

import java.util.ArrayList;
import java.util.List;

import po.BlockHistoryData;

public class FieldRankInf {
	// 板块名称
	public String cname;
	// 指数
	public int index;
	//总成交额
	public Double sumAmount;
	//基数
	public Double basesum;

	public List<BlockHistoryData> stockranks;

	public FieldRankInf() {
		cname = null;
		index = 0;
		stockranks = null;
	}

	public FieldRankInf(String cname, int index, Double sumAmount) {
		super();
		this.cname = cname;
		this.index = index;
		this.sumAmount = sumAmount;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public Double getBasesum() {
		return basesum;
	}

	public void setBasesum(Double basesum) {
		this.basesum = basesum;
	}

	public List<BlockHistoryData> getStockranks() {
		return stockranks;
	}

	public void setStockranks(ArrayList<BlockHistoryData> stockranks) {
		this.stockranks = stockranks;
	}
}
