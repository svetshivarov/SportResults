package com.example.sportresults;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class Football extends MainActivity {

    TextView footballTitle;
    TextView footballText;
    TextView footballStandingsText;
    Button FootballBtnResults;
    Button FootballBtnStandings;

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        footballTitle = findViewById(R.id.footballTitle);
        footballText = findViewById(R.id.footballText);
        footballStandingsText = findViewById(R.id.footballStandingsText);
        FootballBtnResults = findViewById(R.id.FootballBtnResults);
        FootballBtnStandings = findViewById(R.id.FootballBtnStandings);

        FootballBtnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                // RequestQueue queue = Volley.newRequestQueue(Basketball.this);
                //String url ="https://apiv2.allsportsapi.com/football/?met=Leagues&APIkey=cb80513c76ea30f82c8875663c8b6ae96995b9c1811e02fcb0095736cd5505bb";
                String url ="http://api.isportsapi.com/sport/football/schedule/basic?api_key=YIdxWnLRPvqiOvWg&leagueId=1437";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("data");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject results = jsonArray.getJSONObject(i);

                                        String leagueName = results.getString("leagueName");
                                        String homeName = results.getString("homeName");
                                        int homeScore = results.getInt("homeScore");
                                        String awayName = results.getString("awayName");
                                        int awayScore = results.getInt("awayScore");

                                        footballStandingsText.setVisibility(View.INVISIBLE);
                                        footballText.setVisibility(View.VISIBLE);
                                        footballText.append("League: " + leagueName + "\n " +
                                                homeName + " : " + awayName + "\n" +
                                                String.valueOf(homeScore) + " : " + String.valueOf(awayScore) + "\n\n");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                MySingleton.getInstance(Football.this).addToRequestQueue(request);
            }
        });

        FootballBtnStandings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url ="http://api.isportsapi.com/sport/football/standing/league?api_key=YIdxWnLRPvqiOvWg&leagueId=1639";
                String url ="https://apiv2.allsportsapi.com/football/?&met=Topscorers&leagueId=207&APIkey=cb80513c76ea30f82c8875663c8b6ae96995b9c1811e02fcb0095736cd5505bb";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("result");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject results = jsonArray.getJSONObject(i);

                                        int player_place = results.getInt("player_place");
                                        String player_name = results.getString("player_name");
                                        String team_name = results.getString("team_name");
                                        int goals = results.getInt("goals");


                                        footballText.setVisibility(View.INVISIBLE);
                                        footballStandingsText.setVisibility(View.VISIBLE);
                                        footballStandingsText.append("League: Italian Serie A" + "\n " +
                                                player_place + ". " + player_name + ", " + team_name + "\n" +
                                                "Goals: " + goals + "\n\n");
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(Football.this, "Error", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                    Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                MySingleton.getInstance(Football.this).addToRequestQueue(request);
            }
        });
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
                    //nadolu
                    Intent i = new Intent(Football.this, MainActivity.class);
                    startActivity(i);
                }else if(x1 > x2){
                    // right to left swipe
                    // nagore
                    Intent i = new Intent(Football.this, Basketball.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}