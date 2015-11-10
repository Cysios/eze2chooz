package toucoumer.eze2chooz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class result extends AppCompatActivity {

    private final static String EXTRA_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        TextView DisplayChoice1 = (TextView) findViewById(R.id.textView_Res);
        DisplayChoice1.setText(intent.getStringExtra(EXTRA_RESULT));

        //Button to come back app' home
        final ImageButton ButtonGoHome = (ImageButton) findViewById(R.id.imageButtonHome);
        ButtonGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(result.this, home.class);
                startActivity(intent);
            }
        });
        //Button to go to the repository on github.
        final ImageButton ButtonGoGit = (ImageButton) findViewById(R.id.imageButtonGit);
        ButtonGoGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Hopetech/eze2chooz"));
                startActivity(intent);
            }
        });

    }
}
