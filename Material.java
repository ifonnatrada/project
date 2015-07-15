package com.example.win81pro.icooking2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Material extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        TextView tname = (TextView)findViewById(R.id.namefood);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"THSarabun Bold.ttf");
        tname.setTypeface(myCustomFont);
        TextView tname1 = (TextView)findViewById(R.id.material);
        tname1.setTypeface(myCustomFont);

        // Read var from Intent
        Intent intent = getIntent();
        String Namefood = intent.getStringExtra("ข้าวหน้าหมูทอด");
        // Show Data
        ShowData(Namefood);
    }
    public void ShowData(String Namefood)
    {
        final TextView tname = (TextView) findViewById(R.id.namefood);
        final TextView tmaterial = (TextView) findViewById(R.id.material);
        // new Class DB
        final myDBClass myDb = new myDBClass(this);
        // Show Data
        String arrData[] = myDb.SelectDataFood("ข้าวหน้าหมูทอด");
        if(arrData != null)
        {
            tname.setText(arrData[1]);
            tmaterial.setText(arrData[5]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_material, menu);
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
