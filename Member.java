package fitness;

public class Member extends Visitor {
	private String membershipStartDate;
	private String membershipEndDate;
	private String contactInfo;
	private String status;
	
	public String getMembershipStartDate() {
		return membershipStartDate;
	}
	public String getMembershipEndDate() {
		return membershipEndDate;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setMembershipStartDate(String membershipStartDate) {
		this.membershipStartDate = membershipStartDate;
	}
	public void setMembershipEndDate(String membershipEndDate) {
		this.membershipEndDate = membershipEndDate;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
