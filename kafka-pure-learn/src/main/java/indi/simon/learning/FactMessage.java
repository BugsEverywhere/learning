package indi.simon.learning;

public class FactMessage {

    private long userId;
    private String userName;
    private long orderId;
    private String sourceAirportMark;
    private String desAirportMark;
    private String airlineCompanyShortName;
    private int airlineNum;
    private String startTime;

    public FactMessage(long userId, String userName, long orderId, String sourceAirportMark, String desAirportMark, String airlineCompanyShortName, int airlineNum, String startTime) {
        this.userId = userId;
        this.userName = userName;
        this.orderId = orderId;
        this.sourceAirportMark = sourceAirportMark;
        this.desAirportMark = desAirportMark;
        this.airlineCompanyShortName = airlineCompanyShortName;
        this.airlineNum = airlineNum;
        this.startTime = startTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getSourceAirportMark() {
        return sourceAirportMark;
    }

    public void setSourceAirportMark(String sourceAirportMark) {
        this.sourceAirportMark = sourceAirportMark;
    }

    public String getDesAirportMark() {
        return desAirportMark;
    }

    public void setDesAirportMark(String desAirportMark) {
        this.desAirportMark = desAirportMark;
    }

    public String getAirlineCompanyShortName() {
        return airlineCompanyShortName;
    }

    public void setAirlineCompanyShortName(String airlineCompanyShortName) {
        this.airlineCompanyShortName = airlineCompanyShortName;
    }

    public int getAirlineNum() {
        return airlineNum;
    }

    public void setAirlineNum(int airlineNum) {
        this.airlineNum = airlineNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
