
public class TrafficInfraction {
	private int id;
	private String title;
	private String fine_amount;
	private String punishment;
	private String created;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFine_amount() {
		return fine_amount;
	}
	public void setFine_amount(String fine_amount) {
		this.fine_amount = fine_amount;
	}
	public String getPunishment() {
		return punishment;
	}
	public void setPunishment(String punishment) {
		this.punishment = punishment;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public TrafficInfraction(int id, String title, String fine_amount, String punishment, String created) {
		super();
		this.id = id;
		this.title = title;
		this.fine_amount = fine_amount;
		this.punishment = punishment;
		this.created = created;
	}
	
	
}
