package br.com.jess.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.jess.festafimdeano.R;
import br.com.jess.festafimdeano.constants.FimDeAnoConstants;
import br.com.jess.festafimdeano.util.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDayLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mSecurityPreferences = new SecurityPreferences(this);
        


        this.verifyPresence();
    }

    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE);
        if (presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)){
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_confirm) {
            //Lógica de navegação

            String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE);

            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra(FimDeAnoConstants.PRESENCE, presence);

            startActivity(intent);
        }
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDayLeft;
        Button buttonConfirm;
    }
}
