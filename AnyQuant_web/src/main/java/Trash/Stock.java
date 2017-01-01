package Trash;

public class Stock {
	private String id;
	private GrailName grailName;
	public Stock()
	{
		
	}
	public Stock(String id, GrailName grailName) {
		super();
		this.id = id;
		this.grailName = grailName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public GrailName getGrailName() {
		return grailName;
	}
	public void setGrailName(GrailName grailName) {
		this.grailName = grailName;
	}
	
}
