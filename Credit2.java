package com.example.win81pro.icooking2;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.io.File;


public class Credit2 extends Activity {
    ProgressBar Progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit2);
        Progress = (ProgressBar) findViewById(R.id.progressBar);
        Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run(){
                finish();
                Intent goMain = new Intent(getApplicationContext(),Profile.class);
                startActivity(goMain);
            }
        }, 10000);

        File fileExt = new File(getExternalFilesDir(null).getAbsolutePath() + "/bots");
       // if (!fileExt.exists()) {
         if (true){
            ZipFileExtraction extract = new ZipFileExtraction();

            try {
                extract.unZipIt(getAssets().open("aibottest.zip"), getExternalFilesDir(null).getAbsolutePath() + "/"); // แก้ไข bot
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_credit2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
