package toucoumer.eze2chooz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        final Button ButtonGoToNext = (Button) findViewById(R.id.button_1to2);
        ButtonGoToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, choix_des_voeux.class);
                startActivity(intent);
            }
        });
    }

}
