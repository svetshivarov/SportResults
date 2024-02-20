package com.example.sportresults;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private static final String EXTRA_MESSAGE = "";


    TextView SportResultsTitle;
    TextView Welcome;
    ImageView basketballImage;
    Button FootballButton;
    Button BasketballButton;
    RelativeLayout rl;

    String message = "";

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SportResultsTitle = findViewById(R.id.SportResultsTitle);
        Welcome = findViewById(R.id.Welcome);
        basketballImage = findViewById(R.id.basketballImage);
        FootballButton = findViewById(R.id.FootballButton);
        BasketballButton = findViewById(R.id.BasketballButton);
        rl = findViewById(R.id.rl);

        registerForContextMenu(BasketballButton);
        registerForContextMenu(FootballButton);

        // Buttons
        FootballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Football.class);
                startActivity(i);
            }
        });
        BasketballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Basketball.class);
                startActivity(i);
            }
        });

        // light sensor
//        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//
//        Sensor lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        if(lightSensor != null){
//            Toast.makeText(MainActivity.this, "Sensor.TYPE_LIGHT Available", Toast.LENGTH_SHORT).show();
//            mySensorManager.registerListener(
//                    lightSensorListener,
//                    lightSensor,
//                    SensorManager.SENSOR_DELAY_NORMAL);
//
//        } else {
//            Toast.makeText(MainActivity.this, "Sensor.TYPE_LIGHT NOT Available", Toast.LENGTH_SHORT).show();
//        }
    }

//    private final SensorEventListener lightSensorListener
//            = new SensorEventListener(){
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//             TODO Auto-generated method stub
//            rl.setBackgroundColor(getResources().getColor(R.color.dark_blue));
//        }
//
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
//                Toast.makeText(MainActivity.this, "textLIGHT_reading.setText(\"LIGHT: \" + event.values[0]", Toast.LENGTH_SHORT).show();
//            }
//        }
//    };



    // Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.football:
//                Toast.makeText(getApplicationContext(), "Футбол", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, Football.class);
                startActivity(i);
                break;

            case R.id.basketball:
//                Toast.makeText(getApplicationContext(), "Баскетбол", Toast.LENGTH_SHORT).show();

                i = new Intent(MainActivity.this, Basketball.class);
                startActivity(i);
                break;

            case R.id.favourites:
//                Toast.makeText(getApplicationContext(), "Любими", Toast.LENGTH_SHORT).show();

                i = new Intent(MainActivity.this, Favourites.class);
                startActivity(i);
                break;

            case R.id.info:
//                Toast.makeText(getApplicationContext(),"Инфо",Toast.LENGTH_SHORT).show();

                i = new Intent(MainActivity.this, Info.class);
                startActivity(i);
                break;

            case R.id.login:
//                Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();

                i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                break;

            case R.id.register:
//                Toast.makeText(getApplicationContext(), "Register", Toast.LENGTH_SHORT).show();

                i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                break;

            case R.id.home:
//                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();

                i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    // Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addToFavourites:
//                Toast.makeText(getApplicationContext(), "Add to Favourites", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, Favourites.class);
                message = (String) ((Button) findViewById(R.id.BasketballButton)).getText();
                i.putExtra("message", message);
                i.setClass(getApplicationContext(), Favourites.class);
                startActivity(i);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    CharSequence name = "AddToFavouritesNotificationChannel";
                    String description = "Notification for adding to favourites ";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel("1", name, importance);
                    channel.setDescription(description);
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "1")
                            .setSmallIcon(R.drawable.ic_baseline_favorite_24)
                            .setContentTitle("Added to Favourites")
                            .setContentText(message + " is added to Favourites ")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_sports_basketball_24));

                    NotificationManagerCompat.from(MainActivity.this).notify(0, builder.build());
                    }
                break;
                    default:
                    return super.onContextItemSelected(item);
                }
        return super.onContextItemSelected(item);
        }

        // запазване на предадените стойности в Любими
    @Override
    protected void onPause()
    {
        super.onPause();

        // Store values between instances here
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();  // Put the values from the UI

        editor.putString("message", message); // value to store
        // Commit to storage
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPrefs();

        //...Now update your objects with preference values
    }

    private void getPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String myPref = preferences.getString("saved", message);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        // etc.
        savedInstanceState.putString("MyString", message);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        String myString = savedInstanceState.getString("MyString");
    }

    // swipe
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if(x1 < x2){

                    // left to right swipe
                Intent i = new Intent(MainActivity.this, Basketball.class);
                startActivity(i);

            }else if(x1 > x2){

                    // right to left swipe
                Intent i = new Intent(MainActivity.this, Football.class);
                startActivity(i);
            }
            break;
        }
        return false;
    }

}