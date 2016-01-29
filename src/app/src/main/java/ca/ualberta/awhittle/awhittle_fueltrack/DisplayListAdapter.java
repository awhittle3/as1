package ca.ualberta.awhittle.awhittle_fueltrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

// Strategy for displaying list items borrowed from
// http://stackoverflow.com/questions/11281952/listview-with-customized-row-layout-android
// by Sajmon on 1 July 2012
// accessed 29 January 2016
public class DisplayListAdapter extends ArrayAdapter<LogEntry>{
    public DisplayListAdapter(Context context, List<LogEntry> data){
        super(context, R.layout.entry_display, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewEntryHolder holder= null;
        LayoutInflater inflater = LayoutInflater.from(getContext());

        DecimalFormat df1 = new DecimalFormat("#.0");
        DecimalFormat df2 = new DecimalFormat("#.00");
        DecimalFormat df3 = new DecimalFormat("#.000");

        if(convertView == null){
            convertView = inflater.inflate(R.layout.entry_display, null, false);
            holder = new ViewEntryHolder(convertView){};
            convertView.setTag(holder);
        } else {
            holder = (ViewEntryHolder)convertView.getTag();
        }

        // Update all the fields with the entry data
        // Use Decimal Format to display numbers to proper decimal places
        // FIXME: Add item description in front of data text
        holder.getDateText().setText(getItem(position).getDate());
        holder.getStationText().setText(getItem(position).getStation());
        holder.getOdoText().setText(df1.format(getItem(position).getOdoReading()));
        holder.getGradeText().setText(getItem(position).getFuelGrade());
        holder.getAmountText().setText(df3.format(getItem(position).getFuelAmount()));
        holder.getUnitcostText().setText(df1.format(getItem(position).getFuelUnitCost()));
        holder.getCostText().setText(df2.format(getItem(position).getFuelCost()));


        return convertView;
    }
}
