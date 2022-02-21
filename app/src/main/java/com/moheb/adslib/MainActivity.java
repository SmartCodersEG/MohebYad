package com.moheb.adslib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import butterknife.BindView;
import moheb.privatead.Pmob;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.padview)
    LinearLayout padview;
    Pmob PadMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Personal Ads Code //
        padview = findViewById(R.id.padview);
        // Code For Showing Ads In This Activity
        PadMain = new Pmob(this, padview, "https://raw.githubusercontent.com/SmartCodersEG/Tdata/main/AppNews.json"
        );
    }
}