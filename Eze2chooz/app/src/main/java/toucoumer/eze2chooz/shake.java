package toucoumer.eze2chooz;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
// import for accelerometer
import android.hardware.SensorManager;
import android.hardware.Sensor;

import java.util.Random;


public class shake extends AppCompatActivity implements SensorEventListener {

    final String EXTRA_CHOICE1 = "user_choice1";
    final String EXTRA_CHOICE2 = "user_choice2";
    final String EXTRA_CHOICE3 = "user_choice3";
    final String EXTRA_CHOICE4 = "user_choice4";
    final String EXTRA_CHOICE5 = "user_choice5";
    final String EXTRA_RESULT = "result";
    final int nbChoice=0;

    //Sensor variables
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final double SHAKE_THRESHOLD =100;// sensitivity factor of the shaker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        //accelerometer function
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor , SensorManager.SENSOR_DELAY_NORMAL);

        //acceleration=(TextView)findViewById(R.id.acceleration)

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


        final ImageButton ButtonGoHome = (ImageButton) findViewById(R.id.imageButtonHome);
        ButtonGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shake.this, home.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                //Speed calculate distance/time

                double speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 1000;

                if (speed > SHAKE_THRESHOLD) {

                    Intent intent = new Intent(shake.this, result.class);
                    intent.putExtra(EXTRA_RESULT, getResult());
                    startActivity(intent);


                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    public String getResult(){
        Random r= new Random();
        int i = r.nextInt()%5;

        switch (i) {
            case 0:
                return EXTRA_CHOICE1;
            case 1:
                return EXTRA_CHOICE2;
            case 2:
                return EXTRA_CHOICE3;
            case 3:
                return EXTRA_CHOICE4;
            case 4:
                return EXTRA_CHOICE5;
            default:
                break;
        }

        return null;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }




}
