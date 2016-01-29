package ca.ualberta.awhittle.awhittle_fueltrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import java.text.DecimalFormat;
import java.util.List;

// Strategy for displaying list items borrowed from
// http://stackoverflow.com/questions/11281952/listview-with-customized-row-layout-android
// by Sajmon on 1 July 2012
// accessed 29 January 2016
public class DisplayListAdapter extends ArrayAdapter<LogEntry>{
    private int selectedRadioIndex = -1;
    private RadioButton selectedRadio;

    public DisplayListAdapter(Context context, List<LogEntry> data){
        super(context, R.layout.entry_display, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewEntryHolder holder= null;
        LayoutInflater inflater = LayoutInflater.from(getContext());

        // For String formatting
        DecimalFormat df1 = new DecimalFormat("#.0");
        DecimalFormat df2 = new DecimalFormat("#.00");
        DecimalFormat df3 = new DecimalFormat("#.000");

        // Create the holder
        if(convertView == null){
            convertView = inflater.inflate(R.layout.entry_display, null, false);
            holder = new ViewEntryHolder(convertView){};
            convertView.setTag(holder);
        } else {
            holder = (ViewEntryHolder)convertView.getTag();
        }

        // Update all the fields with the entry data
        // Use Decimal Format to display numbers to proper decimal places
        String date = getContext().getString(R.string.date) + getItem(position).getDate();
        String station = getContext().getString(R.string.station) + getItem(position).getStation();
        String odoReading = getContext().getString(R.string.odo)
                + df1.format(getItem(position).getOdoReading()) + getContext().getString(R.string.km);
        String grade = getContext().getString(R.string.grade) + getItem(position).getFuelGrade();
        String amount = getContext().getString(R.string.amount) +
                df3.format(getItem(position).getFuelAmount()) + getContext().getString(R.string.litres);
        String unitcost = getContext().getString(R.string.unitcost)
                + df1.format(getItem(position).getFuelUnitCost())
                + getContext().getString(R.string.unitcost_format);
        String cost = getContext().getString(R.string.cost) + getContext().getString(R.string.dollar)
                + df2.format(getItem(position).getFuelCost());

        holder.getDateText().setText(date);
        holder.getStationText().setText(station);
        holder.getOdoText().setText(odoReading);
        holder.getGradeText().setText(grade);
        holder.getAmountText().setText(amount);
        holder.getUnitcostText().setText(unitcost);
        holder.getCostText().setText(cost);

        // Strategy for radio button behaviour borrowed from
        // http://stackoverflow.com/questions/7329856/how-to-use-radiogroup-in-listview-custom-adapter
        // by Inon Stelman on 14 September 2011
        // Accessed 29 January 2016
        // When a button is selected, unselect others
        holder.getRadio().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (position != selectedRadioIndex && selectedRadio != null) {
                    selectedRadio.setChecked(false);
                }

                selectedRadioIndex = position;
                selectedRadio = (RadioButton) v;
            }
        });


        if(selectedRadioIndex != position){
            holder.getRadio().setChecked(false);
        }else{
            holder.getRadio().setChecked(true);
            if(selectedRadio != null && holder.getRadio() != selectedRadio){
                selectedRadio = holder.getRadio();
            }
        }

        return convertView;
    }

    /**
     * Returns the index of the selected radio button
     */
    public int getSelectedIndex() {
        return selectedRadioIndex;
    }
}
