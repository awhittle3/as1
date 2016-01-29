package ca.ualberta.awhittle.awhittle_fueltrack;


import java.util.ArrayList;
import java.util.List;

public class LogList {
    private List<LogEntry> logList;

    public LogList() {
        this.logList = new ArrayList<>();
    }

    public List<LogEntry> getLogList() {
        return logList;
    }

    public void add(LogEntry entry){
        logList.add(entry);
    }

    public LogEntry get(int index){
        return logList.get(index);
    }

    public double getTotalCost(){
        double sum = 0.0;
        for(LogEntry entry : logList){
            sum += entry.getFuelCost();
        }
        return sum;
    }
}
