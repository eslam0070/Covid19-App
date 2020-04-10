package com.eso.covid19.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.eso.covid19.LocaleManager;
import com.eso.covid19.MyApplication;
import com.eso.covid19.R;

import java.util.ArrayList;

import static com.eso.covid19.LocaleManager.LANGUAGE_ARABIC;
import static com.eso.covid19.LocaleManager.LANGUAGE_ENGLISH;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViewById(R.id.en).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLocale(LANGUAGE_ENGLISH, false);
            }
        });
        findViewById(R.id.en).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return setNewLocale(LANGUAGE_ENGLISH, true);
            }
        });
        findViewById(R.id.ar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLocale(LANGUAGE_ARABIC, false);
            }
        });
        findViewById(R.id.ar).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return setNewLocale(LANGUAGE_ARABIC, true);
            }
        });

    }

    private boolean setNewLocale(String language, boolean restartProcess) {
        MyApplication.localeManager.setNewLocale(this, language);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        if (restartProcess) {
            System.exit(0);
        } else {
        }
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MyApplication.localeManager.setLocale(newBase));
    }
}
