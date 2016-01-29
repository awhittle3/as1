package ca.ualberta.awhittle.awhittle_fueltrack;

import java.util.ArrayList;
import java.util.List;

public class LogList {
    private List<LogEntry> list;

    LogList() {
        this.list = new ArrayList<>();
    }

    LogList(List<LogEntry> list){
        this.list = list;
    }

    public List<LogEntry> getList() {
        return list;
    }

    public void add(LogEntry entry){
        list.add(entry);
    }

    public LogEntry get(int index){
        return list.get(index);
    }

    public void set(int index, LogEntry entry){
        list.set(index, entry);
    }

    public double getTotalCost(){
        double sum = 0.0;
        for(LogEntry entry : list){
            sum += entry.getFuelCost();
        }
        return sum;
    }
}
