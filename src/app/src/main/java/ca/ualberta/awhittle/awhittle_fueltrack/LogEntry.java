package ca.ualberta.awhittle.awhittle_fueltrack;


public class LogEntry {
    private String date;
    private String station;
    private double odoReading;
    private String fuelGrade;
    private double fuelAmount;
    private double fuelUnitCost;
    private double fuelCost;

    public LogEntry(String date, String station, double reading, String grade, double amount,
                    double unitCost) {
        this.date = date;
        this.station = station;
        this.odoReading = reading;
        this.fuelGrade = grade;
        this.fuelAmount = amount;
        this.fuelUnitCost = unitCost;
        updateFuelCost();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public double getOdoReading() {
        return odoReading;
    }

    public void setOdoReading(double reading) {
        this.odoReading = reading;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String grade) {
        this.fuelGrade = grade;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double amount) {
        this.fuelAmount = amount;
        updateFuelCost();
    }

    public double getFuelUnitCost() {
        return fuelUnitCost;
    }

    public void setFuelUnitCost(double unitCost) {
        this.fuelUnitCost = unitCost;
        updateFuelCost();
    }

    public double getFuelCost() {
        return fuelCost;
    }

    private void updateFuelCost(){
        fuelCost = fuelAmount * (fuelUnitCost/100.0);
    }
}
