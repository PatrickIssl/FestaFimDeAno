package com.advanceddigitalschool.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.advanceddigitalschool.festafimdeano.R;
import com.advanceddigitalschool.festafimdeano.constants.FimDeAnoConstats;
import com.advanceddigitalschool.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        //datas
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this. mViewHolder.textDaysLeft.setText(daysLeft);


    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_confirm){

            String presence = this.mSecurityPreferences.getstoredstring(FimDeAnoConstats.PRESENCE_KEY);

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstats.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }

    private void verifyPresence(){
        //não confirmado,sim,não
        String presence = this.mSecurityPreferences.getstoredstring(FimDeAnoConstats.PRESENCE_KEY);
        if(presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        }else if (presence.equals(FimDeAnoConstats.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        }else{
            this.mViewHolder.buttonConfirm.setText((R.string.nao));
        }
    }

    private int getDaysLeft(){
        //data de hj
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        //dia maximo do ano
        Calendar calendarlastday = Calendar.getInstance();
        int dayMax = calendarlastday.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;

    }


    private static class ViewHolder{
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }

}
