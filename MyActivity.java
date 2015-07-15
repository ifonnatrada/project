package com.example.win81pro.icooking2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MyActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TextView t1 = (TextView)findViewById(R.id.textView11);
        TextView t2 = (TextView)findViewById(R.id.textView12);
        TextView t3 = (TextView)findViewById(R.id.textView13);
        TextView t4 = (TextView)findViewById(R.id.textView14);
        TextView t5 = (TextView)findViewById(R.id.textView15);
        TextView t6 = (TextView)findViewById(R.id.textView16);
        TextView t7 = (TextView)findViewById(R.id.textView17);
        TextView t8 = (TextView)findViewById(R.id.textView18);
        TextView t9 = (TextView)findViewById(R.id.textView19);
        TextView t10 = (TextView)findViewById(R.id.textView20);
        TextView t11 = (TextView)findViewById(R.id.textView21);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"THSarabun Bold.ttf");
        t1.setTypeface(myCustomFont);
        t2.setTypeface(myCustomFont);
        t3.setTypeface(myCustomFont);
        t4.setTypeface(myCustomFont);
        t5.setTypeface(myCustomFont);
        t6.setTypeface(myCustomFont);
        t7.setTypeface(myCustomFont);
        t8.setTypeface(myCustomFont);
        t9.setTypeface(myCustomFont);
        t10.setTypeface(myCustomFont);
        t11.setTypeface(myCustomFont);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
