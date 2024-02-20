package com.example.sportresults;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Basketball extends MainActivity {

    TextView basketballTitle;
    TextView basketballText;
    TextView basketballStandingsText;
    Button BasketballBtnResults;
    Button BasketballBtnStandings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);

        basketballTitle = findViewById(R.id.basketballTitle);
        basketballText = findViewById(R.id.basketballText);
        basketballStandingsText = findViewById(R.id.basketballStandingsText);
        BasketballBtnResults = findViewById(R.id.BasketballBtnResults);
        BasketballBtnStandings = findViewById(R.id.BasketballBtnStandings);

        BasketballBtnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                // RequestQueue queue = Volley.newRequestQueue(Basketball.this);
                // String url ="https://allsportsapi.com/api/basketball/?met=Livescore&APIkey=cb80513c76ea30f82c8875663c8b6ae96995b9c1811e02fcb0095736cd5505bb";
                String url ="http://api.isportsapi.com/sport/basketball/liveanimation/schedule?api_key=YIdxWnLRPvqiOvWg ";

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

                                        basketballStandingsText.setVisibility(View.INVISIBLE);
                                        basketballText.setVisibility(View.VISIBLE);
                                        basketballText.append("League: " + leagueName + "\n " +
                                                homeName + " : " + awayName + "\n" +
                                                homeScore + " : " + awayScore + "\n\n");
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

                MySingleton.getInstance(Basketball.this).addToRequestQueue(request);
            }
        });

        BasketballBtnStandings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String url ="https://allsportsapi.com/api/basketball/?met=Livescore&APIkey=cb80513c76ea30f82c8875663c8b6ae96995b9c1811e02fcb0095736cd5505bb";
                String url ="http://api.isportsapi.com/sport/basketball/standing/league?api_key=YIdxWnLRPvqiOvWg&leagueId=111";

                JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("data");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject standings = jsonArray.getJSONObject(i);

                                        String leagueName = standings.getString("leagueName");
                                        String teamName = standings.getString("teamName");
                                        String homeWin = standings.getString("homeWin");
                                        String homeLoss = standings.getString("homeLoss");
                                        String awayWin = standings.getString("awayWin");
                                        String awayLoss = standings.getString("awayLoss");
                                        int totalRank = standings.getInt("totalRank");

                                        basketballText.setVisibility(View.INVISIBLE);
                                        basketballStandingsText.setVisibility(View.VISIBLE);
                                        basketballStandingsText.append("League: " + leagueName + "\n " +
                                                totalRank + ". " + teamName + "\n" +
                                                "Home Wins: " + homeWin + ", " + "Home Losses: " + homeLoss + "\n" +
                                                "Away Wins: " +awayWin + ", " + "Away Losses: " + awayLoss + "\n\n");
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

                MySingleton.getInstance(Basketball.this).addToRequestQueue(request1);
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
                    Intent i = new Intent(Basketball.this, Football.class);
                    startActivity(i);
                }else if(x1 > x2){
                    // right to left swipe
                    // nagore
                    Intent i = new Intent(Basketball.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}