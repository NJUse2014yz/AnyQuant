package vo;

/**
 * 买入、卖出信号
 * */
public class TradeSignal {
	/**该股全部卖出信号的买入、卖出评分*/
	public QuotaAnalysis sell;
	/**剩余资金全部买入该股的买入、卖出评分*/
	public QuotaAnalysis buy;
	
	public TradeSignal() {
		this.sell = new QuotaAnalysis(10,80,"");
		this.buy = new QuotaAnalysis(80,10,"");
	}
	public TradeSignal(QuotaAnalysis sell, QuotaAnalysis buy) {
		this.sell = sell;
		this.buy = buy;
	}
	public QuotaAnalysis getSell() {
		return sell;
	}
	public void setSell(QuotaAnalysis sell) {
		this.sell = sell;
	}
	public QuotaAnalysis getBuy() {
		return buy;
	}
	public void setBuy(QuotaAnalysis buy) {
		this.buy = buy;
	}
	@Override
	public String toString() {
		return "TradeSignal [sell=" + sell + ", buy=" + buy + "]";
	}
}
