package ca.ualberta.awhittle.awhittle_fueltrack;


import android.test.ActivityInstrumentationTestCase2;

public class LogEntryTest extends ActivityInstrumentationTestCase2{

    public LogEntryTest() {
        super(LogEntryTest.class);
    }

    public void testUpdateFuelCost(){
        double litres = 20.129;
        double rate = 70.6;
        LogEntry entry = new LogEntry("2016-01-01", "Esso", 12345.6, "Premium", litres, rate);

        // Cost should be right when LogEntry constructed
        // Because of double rounding errors, if the difference is smaller than 1e-8, we pass
        assertTrue(Math.abs(litres * rate / 100.0 - entry.getFuelCost()) < 1e-8);

        // Setting amount should update cost
        litres += 15.678;
        entry.setFuelAmount(litres);


        assertTrue(Math.abs(litres * rate / 100.0 - entry.getFuelCost()) < 1e-8);

        // Setting rate should update cost
        rate *= 1.2;
        entry.setFuelUnitCost(rate);

        assertTrue(Math.abs(litres * rate / 100.0 - entry.getFuelCost()) < 1e-8);
    }
}
