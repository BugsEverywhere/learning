package indi.simon.learning;

public class AirportMessage {

    private String airportMark;
    private String airportName;
    private String location;

    public AirportMessage(String airportMark, String airportName, String location) {
        this.airportMark = airportMark;
        this.airportName = airportName;
        this.location = location;
    }

    public String getAirportMark() {
        return airportMark;
    }

    public void setAirportMark(String airportMark) {
        this.airportMark = airportMark;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
