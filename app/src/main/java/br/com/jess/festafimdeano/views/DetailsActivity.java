package br.com.jess.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import br.com.jess.festafimdeano.R;
import br.com.jess.festafimdeano.constants.FimDeAnoConstants;
import br.com.jess.festafimdeano.util.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkPerticipate = findViewById(R.id.check_participate);

        this.mViewHolder.checkPerticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.check_participate){
            //Lógica do CheckBox - Adicionar informação ao SharedPreferences;
            if (this.mViewHolder.checkPerticipate.isChecked()){
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
            }
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)){
                this.mViewHolder.checkPerticipate.setChecked(true);
            } else {
                this.mViewHolder.checkPerticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkPerticipate;
    }
}
