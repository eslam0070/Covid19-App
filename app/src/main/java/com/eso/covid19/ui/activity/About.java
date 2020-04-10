package com.eso.covid19.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.eso.covid19.R;
import com.shashank.sony.fancyaboutpagelib.FancyAboutPage;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        FancyAboutPage fancyAboutPage=findViewById(R.id.fancyaboutpage);
        fancyAboutPage.setCover(R.drawable.cover); //Pass your cover image
        fancyAboutPage.setName("Eslam Gamal(إسلام جمال)");
        fancyAboutPage.setDescription("Android Developer exp 2year");
        fancyAboutPage.setAppIcon(R.mipmap.ic_launcher); //Pass your app icon image
        fancyAboutPage.setAppName("About This App");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.0");
        fancyAboutPage.setAppDescription("This app built for providing all live updates of CORONA VIRUS.\n\n" +
                "Major motive of this app to spread only true facts and any related news!\n\n"+
                "As per recent announcement by our Egyptian police for lockdown in their home, we also requested to all the people to quarantine yourself at your home and STAY HEALTHY AND SAFE.");
        fancyAboutPage.addEmailLink("eslam.gamal0114@gmail.com");     //Add your email id
        fancyAboutPage.addFacebookLink("https://www.facebook.com/EslamGamal4");  //Add your facebook address url
        fancyAboutPage.addGitHubLink("https://github.com/eslam0070");

        network();
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
            AlertDialog.Builder Checkbuilder =new  AlertDialog.Builder(About.this);
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
