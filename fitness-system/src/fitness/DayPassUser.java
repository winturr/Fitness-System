package fitness;

public class DayPassUser extends Visitor {
	public DayPassUser(String visitorID, String name, String visitorType, String visitDate) {
		super(visitorID, name, visitorType);
		this.visitDate = visitDate;
	}
	private String visitDate;
	
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
}
