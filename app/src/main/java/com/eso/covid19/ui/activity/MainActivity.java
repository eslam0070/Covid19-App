package com.eso.covid19.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eso.covid19.MyApplication;
import com.eso.covid19.R;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cases, tcases, death, tdeath, recover;
    TextView casesW, deathW, recoverW;
    RequestQueue requestQueue, requestQueue1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cases = findViewById(R.id.cases);
        tcases = findViewById(R.id.tcases);
        death = findViewById(R.id.death);
        tdeath = findViewById(R.id.tdeath);
        recover = findViewById(R.id.recover);

        casesW = findViewById(R.id.casesW);
        deathW = findViewById(R.id.deathW);
        recoverW = findViewById(R.id.recoverW);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.fetching));
        progressDialog.show();

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        loadDataEgypt();
        loadDataWorld();
        network();

    }

    private void loadDataEgypt(){
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://corona.lmao.ninja/countries/egypt", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String casesComes =  response.getString("cases");
                    String totalCases =  response.getString("todayCases");
                    String deaths =  response.getString("deaths");
                    String totalDeath =  response.getString("todayDeaths");
                    String recovery =  response.getString("recovered");

                    cases.setText(casesComes);
                    tcases.setText(totalCases);
                    death.setText(deaths);
                    tdeath.setText(totalDeath);
                    recover.setText(recovery);

                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "Something went wrong");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void loadDataWorld() {
        requestQueue1 = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, "https://corona.lmao.ninja/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String casesWstr =  response.getString("cases");
                    String deathsWStr =  response.getString("deaths");
                    String recoveryWStr =  response.getString("recovered");

                    casesW.setText(casesWstr);
                    deathW.setText(deathsWStr);
                    recoverW.setText(recoveryWStr);
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "Something went wrong");
            }
        });
        requestQueue.add(jsonObjectRequest1);
    }

    public void allCountries(View view) {
        startActivity(new Intent(this, AllCountries.class));
    }

    public void corona(View view) {
        startActivity(new Intent(this, Covid.class));
    }

    public void symptom(View view) {
        startActivity(new Intent(this, Symptoms.class));
    }

    public void updates(View view) {
        startActivity(new Intent(this, Updates.class));
    }

    public void helpline(View view) {
        startActivity(new Intent(this, Helpline.class));
    }

    public void about(View view) {
        startActivity(new Intent(this, About.class));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MyApplication.localeManager.setLocale(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        menu.findItem(R.id.app_bar_search).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.app_bar_settings)
            startActivity(new Intent(this,Settings.class));
        return super.onOptionsItemSelected(item);
    }

    private void network(){
        if(!isNetworkAvailable()){
            //Create an Alert Dialog
            AlertDialog.Builder Checkbuilder =new  AlertDialog.Builder(MainActivity.this);
            Checkbuilder.setIcon(R.drawable.error);
            Checkbuilder.setTitle(getString(R.string.error));
            Checkbuilder.setMessage(getString(R.string.checkyour));
            //Builder Retry Button

            Checkbuilder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Restart The Activity
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }
            });
            Checkbuilder.setNegativeButton(getString(R.string.exit), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }) ;


            AlertDialog alert=Checkbuilder.create();
            alert.show();
        } else {
            if (isNetworkAvailable()){

                Thread tr=new Thread(){
                    public  void  run(){
                        try {
                            sleep(4500);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        finally {
                        }
                    }
                };
                tr.start();

            }
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo !=null;
    }
}
