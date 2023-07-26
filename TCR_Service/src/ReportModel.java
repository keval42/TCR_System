
public class ReportModel {
	private int id;
	private String title;
	private String details;
	private String incident;
	private String image;
	private int fine;
	private String charges;
	private String evidence;
	private String incidentOfficer;
	private String created;
	
	public ReportModel(int id, String title, String details, String incident, String image, int fine, String charges,
			String evidence, String incidentOfficer, String created) {
		super();
		this.id = id;
		this.title = title;
		this.details = details;
		this.incident = incident;
		this.image = image;
		this.fine = fine;
		this.charges = charges;
		this.evidence = evidence;
		this.incidentOfficer = incidentOfficer;
		this.created = created;
	}
	
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getEvidence() {
		return evidence;
	}
	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}
	public String getIncidentOfficer() {
		return incidentOfficer;
	}
	public void setIncidentOfficer(String incidentOfficer) {
		this.incidentOfficer = incidentOfficer;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
}
