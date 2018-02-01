package com.nam.color_picker;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mRedView;
    private TextView mGreenView;
    private TextView mBlueView;
    private TextView mResultView;

    private SeekBar mRedSeek;
    private SeekBar mGreenSeek;
    private SeekBar mBlueSeek;

    private int mRed;
    private int mGreen;
    private int mBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


        mRedView = findViewById(R.id.textViewRed);
        mGreenView = findViewById(R.id.textViewGreen);
        mBlueView = findViewById(R.id.textViewBlue);
        mResultView = findViewById(R.id.textViewResult);

        mRedSeek = findViewById(R.id.seekBarRed);
        mGreenSeek = findViewById(R.id.seekBarGreen);
        mBlueSeek = findViewById(R.id.seekBarBlue);

        mRedSeek.setProgress(100);
        mGreenSeek.setProgress(100);
        mBlueSeek.setProgress(100);

        mRed = 255;
        mGreen = 255;
        mBlue = 255;

        ChangeResultColor();

        mRedSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRed = ChangeColor(mRedView, 0xFF0000, 16, progress);
                ChangeResultColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mGreenSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mGreen = ChangeColor(mGreenView, 0x00FF00, 8, progress);
                ChangeResultColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mBlueSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBlue = ChangeColor(mBlueView, 0x0000FF, 0, progress);
                ChangeResultColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private int ChangeColor(View view, int color, int shift, int progress){

        progress = (progress * 0xFF) / 100;

        int retcol = progress;

        int resultcolor = color & (progress << shift) | 0xFF000000;

        view.setBackgroundColor(resultcolor);

        return retcol;
    }

    private void ChangeResultColor(){

        int color = (mRed << 16) | (mGreen << 8) | (mBlue);

        mResultView.setBackgroundColor(color | 0xFF000000);

        mResultView.setText(getResources().getString(R.string.result_text) + " " + String.format("%06X", color));



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
