package com.example.volleyprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;

public class MainActivity extends AppCompatActivity {
    public TextView name1,gender1,dob1,nationality1,about1,tag;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1 = findViewById(R.id.name1);
        gender1 = findViewById(R.id.gender1);
        dob1 = findViewById(R.id.dob1);
        nationality1 = findViewById(R.id.nationality1);
        about1 = findViewById(R.id.about1);
        tag = findViewById(R.id.tag);
        mQueue = Volley.newRequestQueue(this);

        jsonParse();

    }
    private void jsonParse() {
        String url = "http://112.196.33.91/dating_app/api/profile?user_id=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("profile_data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject profile = jsonArray.getJSONObject(i);
                        String name = profile.getString("name");
                        String gender = profile.getString("gender");
                        String dob = profile.getString("dob");
                        String nationality = profile.getString("nationality");
                        String about = profile.getString("about");

                    //     Array a = (Array) interests.getJSONArray("tags");

                    //    txt.append(name + "," + gender + "," + dob + "," + nationality + "," + about + ",");

//                        name = response.getString(name);
//                        gender = response.getString(gender);
//                        dob = response.getString(dob);
//                        nationality = response.getString(nationality);
//                        about = response.getString(about);
                        name1.setText(name);
                        gender1.setText(gender);
                        dob1.setText(dob);
                        nationality1.setText(nationality);
                        about1.setText(about);


                        JSONArray array=profile.getJSONArray("interests");
                        String arr[]=new String[array.length()];
                        for(int j=0;j<array.length();j++)
                        {
                            JSONObject  jsonObject=array.getJSONObject(j);
                            arr[j]=jsonObject.getString("tags");


                        }
                      tag.setText(arr[0]);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mQueue.add(request);
    }

    }

