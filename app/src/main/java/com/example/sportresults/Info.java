package com.example.sportresults;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class Info extends MainActivity {

    TextView infoTitle;
    TextView infoText;
    TextView infoTextCall;
    Button btnCall;

    String callNumber = "+359888888888";
//    int CALL_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        infoTitle = findViewById(R.id.infoTitle);
        infoText = findViewById(R.id.infoText);
        infoTextCall = findViewById(R.id.infoTextCall);
        btnCall = findViewById(R.id.btnCall);

        infoText.setText("Svetlin Shivarov, \n " +
                "Naval Academy, \n" +
                "School Project");
        infoTextCall.setText("Any questions? Then Call us!");

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // phone call

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+callNumber));

                if (ActivityCompat.checkSelfPermission(Info.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });
    }
//    public void askForCallPermission() {
//        ActivityCompat.requestPermissions(Info.this,
//                new String[]{Manifest.permission.CALL_PHONE},
//                CALL_PERMISSION_REQUEST_CODE);
//    }
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + callNumber));
//                startActivity(callIntent);
//            } else {
//                Toast.makeText(Info.this, "You cannot call without accepting this permission.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}