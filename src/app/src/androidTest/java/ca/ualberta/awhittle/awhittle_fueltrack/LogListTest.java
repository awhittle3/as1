package ca.ualberta.awhittle.awhittle_fueltrack;


import android.test.ActivityInstrumentationTestCase2;

public class LogListTest extends ActivityInstrumentationTestCase2 {

    public LogListTest() {
        super(LogListTest.class);
    }

    // Test the add and get method
    public void testAddGet(){
        LogEntry entry = new LogEntry("2016-01-01", "Esso", 12345.6, "Premium", 20.005, 70.5);
        LogList logList = new LogList();
        logList.add(entry);
        assertSame(entry, logList.get(0));
    }

    // Test the set method
    public void testSet(){
        LogEntry entry1 = new LogEntry("2016-01-01", "Esso", 12345.6, "Premium", 20.005, 70.5);
        LogEntry entry2 = new LogEntry("2016-01-02", "Costco", 20000.6, "Premium", 20.005, 80.2);
        LogList logList = new LogList();
        logList.add(entry1);
        logList.set(0, entry2);

        // Index 0 should be entry2 now
        assertSame(entry2, logList.get(0));

    }

    // Test the getTotalCost method
    public void testGetTotalCost(){
        double litres1 = 20.003;
        double litres2 = 30.002;
        double rate1 = 70.5;
        double rate2 = 89.2;
        LogEntry entry1 = new LogEntry("2016-01-01", "Esso", 12345.6, "Premium", litres1, rate1);
        LogEntry entry2 = new LogEntry("2016-01-01", "Costco", 20000.0, "Premium", litres2, rate2);

        LogList logList = new LogList();

        // Total cost of empty list should be 0
        assertEquals(logList.getTotalCost(), 0.0);

        logList.add(entry1);


        // Because of double rounding errors, if the difference is smaller than 1e-8, we pass
        assertTrue(Math.abs(litres1 * rate1 / 100.0 - logList.getTotalCost()) < 1e-8);
        assertTrue(entry1.getFuelCost() - logList.getTotalCost() < 1e-8);

        logList.add(entry2);

        // Sum of costs should be equal to total cost
        assertTrue(Math.abs(litres1 * rate1 / 100.0 + litres2 * rate2 / 100.0
                - logList.getTotalCost()) < 1e-8);
    }
}
