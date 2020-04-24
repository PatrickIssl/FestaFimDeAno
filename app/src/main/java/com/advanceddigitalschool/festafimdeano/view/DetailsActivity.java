package com.advanceddigitalschool.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.advanceddigitalschool.festafimdeano.R;
import com.advanceddigitalschool.festafimdeano.constants.FimDeAnoConstats;
import com.advanceddigitalschool.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private  ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = (CheckBox) findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.check_participate){

            if(this.mViewHolder.checkParticipate.isChecked()) {
                //salvar a presença
                this.mSecurityPreferences.storeString(FimDeAnoConstats.PRESENCE_KEY, FimDeAnoConstats.CONFIRMATION_YES);
            } else{
                    //salvar a ausencia
                this.mSecurityPreferences.storeString(FimDeAnoConstats.PRESENCE_KEY, FimDeAnoConstats.CONFIRMATION_NO);
            }


        }
    }

    private void loadDataFromActivity(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String presence = extras.getString(FimDeAnoConstats.PRESENCE_KEY);
            if(presence != null && presence.equals(FimDeAnoConstats.CONFIRMATION_YES)){
                this.mViewHolder.checkParticipate.setChecked(true);
            }else{
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }


    private  static class ViewHolder{

        CheckBox checkParticipate;
    }
}
