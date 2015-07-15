package com.example.win81pro.icooking2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.VideoView;


public class Vedio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);

        TextView tname = (TextView)findViewById(R.id.namefood);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"THSarabun Bold.ttf");
        tname.setTypeface(myCustomFont);

        // Read var from Intent
        Intent intent = getIntent();
        String Namefood = intent.getStringExtra("ข้าวหน้าหมูทอด");
        // Show Data
        ShowData(Namefood);



    }
    public void ShowData(String Namefood)
    {
        final TextView tname = (TextView) findViewById(R.id.namefood);
        final WebView vdoweb = (WebView) findViewById(R.id.webView);

        // new Class DB
        final myDBClass myDb = new myDBClass(this);
        SQLiteDatabase db = myDb.getWritableDatabase();

        // Show Data
        String arrData[] = myDb.SelectDataFood(Namefood);
        if(arrData != null)
        {
            tname.setText(arrData[1]);
            vdoweb.loadUrl(arrData[8]);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vedio, menu);
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
