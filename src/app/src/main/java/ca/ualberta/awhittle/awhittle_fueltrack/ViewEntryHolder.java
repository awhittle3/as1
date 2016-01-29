package ca.ualberta.awhittle.awhittle_fueltrack;

import android.view.View;
import android.widget.TextView;

// Strategy for displaying list items borrowed from
// http://stackoverflow.com/questions/11281952/listview-with-customized-row-layout-android
// by Sajmon on 1 July 2012
// accessed 29 January 2016
public class ViewEntryHolder {
    private View entry;
    private TextView dateText = null;
    private TextView stationText = null;
    private TextView odoText = null;
    private TextView gradeText = null;
    private TextView amountText = null;
    private TextView unitcostText = null;
    private TextView costText = null;


    public ViewEntryHolder(View entry){
        this.entry = entry;
    }

    public TextView getDateText(){
        if(this.dateText == null){
            this.dateText = (TextView) entry.findViewById(R.id.text_date);
        }
        return this.dateText;
    }

    public TextView getStationText(){
        if(this.stationText == null){
            this.stationText = (TextView) entry.findViewById(R.id.text_station);
        }
        return this.stationText;
    }

    public TextView getOdoText(){
        if(this.odoText == null){
            this.odoText = (TextView) entry.findViewById(R.id.text_odo);
        }
        return this.odoText;
    }

    public TextView getGradeText(){
        if(this.gradeText == null){
            this.gradeText = (TextView) entry.findViewById(R.id.text_grade);
        }
        return this.gradeText;
    }

    public TextView getAmountText(){
        if(this.amountText == null){
            this.amountText = (TextView) entry.findViewById(R.id.text_amount);
        }
        return this.amountText;
    }

    public TextView getUnitcostText(){
        if(this.unitcostText == null){
            this.unitcostText = (TextView) entry.findViewById(R.id.text_unitcost);
        }
        return this.unitcostText;
    }

    public TextView getCostText(){
        if(this.costText == null){
            this.costText = (TextView) entry.findViewById(R.id.text_cost);
        }
        return this.costText;
    }
}
