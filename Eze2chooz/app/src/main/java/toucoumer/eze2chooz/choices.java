package toucoumer.eze2chooz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class choices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choices);

        final ImageButton ButtonGoNext = (ImageButton) findViewById(R.id.imageButtonNext);
        ButtonGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choices.this, shake.class);
                startActivity(intent);
            }
        });

        final ImageButton ButtonGoHome = (ImageButton) findViewById(R.id.imageButtonHome);
        ButtonGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choices.this, home.class);
                startActivity(intent);
            }
        });

    }
}
