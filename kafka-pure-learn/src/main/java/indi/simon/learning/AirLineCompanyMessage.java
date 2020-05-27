package indi.simon.learning;

public class AirLineCompanyMessage {

    private String airLineCompanyShortName;
    private String airLineCompany;

    public AirLineCompanyMessage(String airLineCompanyShortName, String airLineCompany) {
        this.airLineCompanyShortName = airLineCompanyShortName;
        this.airLineCompany = airLineCompany;
    }

    public String getAirLineCompanyShortName() {
        return airLineCompanyShortName;
    }

    public void setAirLineCompanyShortName(String airLineCompanyShortName) {
        this.airLineCompanyShortName = airLineCompanyShortName;
    }

    public String getAirLineCompany() {
        return airLineCompany;
    }

    public void setAirLineCompany(String airLineCompany) {
        this.airLineCompany = airLineCompany;
    }
}
