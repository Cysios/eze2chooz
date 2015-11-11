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

    private final static String EXTRA_CHOICES = "user_choices";
    private final static String EXTRA_RESULT = "result";
    private static final float SHAKE_THRESHOLD = 100; // sensitivity factor of the shaker
    private String[] Choices;
    private int nbChoices = 0;
    private float last_x, last_y, last_z;
    private long lastUpdate = 0;
    private SensorManager mSensorManager;

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
            Choices = intent.getStringArrayExtra(EXTRA_CHOICES);
            for (int i = 0; i < Choices.length; i++) {
                if (!Choices[i].equals(""))
                    nbChoices++;
            }

            // Display values
            DisplayChoice1.setText(Choices[0]);
            DisplayChoice2.setText(Choices[1]);
            DisplayChoice3.setText(Choices[2]);
            DisplayChoice4.setText(Choices[3]);
            DisplayChoice5.setText(Choices[4]);
        }
    }

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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public String getResult() {
        String result = null;
        int lower = 0;
        int higher = nbChoices;
        int random = (int) (Math.random() * (higher - lower)) + lower;
        switch (random) {
            case 0:
                result = Choices[0];
                break;
            case 1:
                result = Choices[1];
                break;
            case 2:
                result = Choices[2];
                break;
            case 3:
                result = Choices[3];
                break;
            case 4:
                result = Choices[4];
                break;
            default:
                break;
        }
        return result;
    }

}
