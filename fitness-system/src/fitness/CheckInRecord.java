package fitness;

public class CheckInRecord {
    private String checkInID,checkInTime,checkOutTime,date;
    
    public String getCheckInID(){
        return checkInID;
    }
    
    public String getcheckInTime(){
        return checkInTime;
    }
    
    public String getCheckOutID(){
        return checkOutTime;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setCheckInID(String checkInID) {
        this.checkInID=checkInID;
    }
    
    public void setCheckInTime(String checkInTime) {
        this.checkInTime=checkInTime;
    }
    
    public void setCheckOutID(String checkOutTime) {
        this.checkOutTime=checkOutTime;
    }
    
    public void setDate(String date) {
        this.date=date;
    }
}