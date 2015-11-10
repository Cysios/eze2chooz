/* Code for Activity shake
shake is the activity where the user shake his device to activate the random selection of the choice.
The random selection is based on the accelerometer sensor.
*/

package toucoumer.eze2chooz;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

// import for accelerometer
import android.hardware.SensorManager;
import android.hardware.Sensor;

public class shake extends AppCompatActivity implements SensorEventListener {

    // Variables are used to recover the users choices from the previous activity (choices)
    private final static String EXTRA_CHOICE1 = "user_choice1";
    private final static String EXTRA_CHOICE2 = "user_choice2";
    private final static String EXTRA_CHOICE3 = "user_choice3";
    private final static String EXTRA_CHOICE4 = "user_choice4";
    private final static String EXTRA_CHOICE5 = "user_choice5";

    //Variable is used to know how many choices are filled by the user from the previous activity
    private final static String EXTRA_NBCHOICES = "user_nbChoices";

    // Variable are used to send the result of the random selection
    private final static String EXTRA_RESULT = "result";

    // SHAKE_THRESHOLD is the sensitivity factor of the shaker to activate function getResult()
    private static final float SHAKE_THRESHOLD = 100;

    // Variables for display
    private String Choice1, Choice2, Choice3, Choice4, Choice5;

    //Variables for sensor Detection
    private float last_x, last_y, last_z;
    private long lastUpdate = 0;
    private SensorManager mSensorManager;
    //Variables for getResult() function
    private static int nbChoices = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        //accelerometer function
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);

        // Display values enter in choices
        Intent intent = getIntent();
        if (intent != null) {
            // Init TextView
            TextView DisplayChoice1 = (TextView) findViewById(R.id.textViewChoice1);
            TextView DisplayChoice2 = (TextView) findViewById(R.id.textViewChoice2);
            TextView DisplayChoice3 = (TextView) findViewById(R.id.textViewChoice3);
            TextView DisplayChoice4 = (TextView) findViewById(R.id.textViewChoice4);
            TextView DisplayChoice5 = (TextView) findViewById(R.id.textViewChoice5);
            // Get intent values
            Choice1 = intent.getStringExtra(EXTRA_CHOICE1);
            Choice2 = intent.getStringExtra(EXTRA_CHOICE2);
            Choice3 = intent.getStringExtra(EXTRA_CHOICE3);
            Choice4 = intent.getStringExtra(EXTRA_CHOICE4);
            Choice5 = intent.getStringExtra(EXTRA_CHOICE5);
            nbChoices = intent.getIntExtra(EXTRA_NBCHOICES, 0);
            // Display values
            DisplayChoice1.setText(Choice1);
            DisplayChoice2.setText(Choice2);
            DisplayChoice3.setText(Choice3);
            DisplayChoice4.setText(Choice4);
            DisplayChoice5.setText(Choice5);
        }
    }

    //Override to calculate the speed of the shock
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        float x, y, z, speed;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                //Speed calculate distance/time
                speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 1000;

                if (speed > SHAKE_THRESHOLD) {
                    Intent intent = new Intent(shake.this, result.class);
                    //put the return of the function getResult() in the EXTRA_RESULT
                    intent.putExtra(EXTRA_RESULT, getResult());
                    startActivity(intent);
                    return;
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

   @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    //Unactivate the sensor when the activity is on Pause
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    //This function do the random selection with nbChoices variables and the choices of the user.
    //She returns the result of the random selection
    public String getResult() {
        String result = null;
        int lower = 0;
        int higher = nbChoices;
        int random = (int)(Math.random() * (higher-lower)) + lower;
        switch (random) {
            case 0:
                result = Choice1;
                break;
            case 1:
                result = Choice2;
                break;
            case 2:
                result = Choice3;
                break;
            case 3:
                result = Choice4;
                break;
            case 4:
                result = Choice5;
                break;
            default:
                break;
        }
    return result;
    }

}
