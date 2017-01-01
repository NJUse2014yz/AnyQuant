package vo;

public class QuotaAnalysis {
	/**买入评分*/
	public double scoreIn;
	/**卖出评分*/
	public double scoreOut;
	/**文本分析*/
	public String message;
	
	public QuotaAnalysis() {
		this.scoreIn = 0;
		this.scoreOut = 0;
		this.message = "";
	}
	public QuotaAnalysis(double scoreIn, double scoreOut, String message) {
		this.scoreIn = scoreIn;
		this.scoreOut = scoreOut;
		this.message = message;
	}

	public double getScoreIn() {
		return scoreIn;
	}
	public void setScoreIn(double scoreIn) {
		this.scoreIn = scoreIn;
	}
	public double getScoreOut() {
		return scoreOut;
	}
	public void setScoreOut(double scoreOut) {
		this.scoreOut = scoreOut;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "QuotaAnlysis [scoreIn=" + scoreIn + ", scoreOut=" + scoreOut
				+ ", message=\n" + message + "]";
	}
}
