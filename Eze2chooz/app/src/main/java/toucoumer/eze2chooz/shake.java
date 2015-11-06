package toucoumer.eze2chooz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class shake extends AppCompatActivity {

    final String EXTRA_CHOICE1 = "user_choice1";
    final String EXTRA_CHOICE2 = "user_choice2";
    final String EXTRA_CHOICE3 = "user_choice3";
    final String EXTRA_CHOICE4 = "user_choice4";
    final String EXTRA_CHOICE5 = "user_choice5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        Intent intent = getIntent();
        TextView DisplayChoice1 = (TextView) findViewById(R.id.textViewChoice1);
        TextView DisplayChoice2 = (TextView) findViewById(R.id.textViewChoice2);
        TextView DisplayChoice3 = (TextView) findViewById(R.id.textViewChoice3);
        TextView DisplayChoice4 = (TextView) findViewById(R.id.textViewChoice4);
        TextView DisplayChoice5 = (TextView) findViewById(R.id.textViewChoice5);

        if (intent != null) {
            DisplayChoice1.setText(intent.getStringExtra(EXTRA_CHOICE1));
            DisplayChoice2.setText(intent.getStringExtra(EXTRA_CHOICE2));
            DisplayChoice3.setText(intent.getStringExtra(EXTRA_CHOICE3));
            DisplayChoice4.setText(intent.getStringExtra(EXTRA_CHOICE4));
            DisplayChoice5.setText(intent.getStringExtra(EXTRA_CHOICE5));
        }

        final ImageButton ButtonGoToNext = (ImageButton) findViewById(R.id.imageButtonNext);
        ButtonGoToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shake.this, result.class);
                startActivity(intent);
            }
        });

        final ImageButton ButtonGoHome = (ImageButton) findViewById(R.id.imageButtonHome);
        ButtonGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shake.this, home.class);
                startActivity(intent);
            }
        });

    }
}
