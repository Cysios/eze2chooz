/* Code for Activity Home
Home is the first screen of the application
it is a welcome screen where the user can start his activity
There is only one possibility for the user "Start"
*/

package toucoumer.eze2chooz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Button to go to next activity
        final Button ButtonGoToNext = (Button) findViewById(R.id.button_1to2);
        ButtonGoToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, choices.class);
                startActivity(intent);
            }
        });
    }

}
