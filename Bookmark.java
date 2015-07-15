package com.example.win81pro.icooking2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.Typeface;
import android.media.Rating;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Bookmark extends Activity {
    String allergic[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        allergic = new String[14];

        TextView tn = (TextView)findViewById(R.id.namefood);
        TextView tv = (TextView)findViewById(R.id.textView);
        EditText et = (EditText)findViewById(R.id.editText);
        Button bt = (Button)findViewById(R.id.button);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"THSarabun Bold.ttf");
        tn.setTypeface(myCustomFont);
        tv.setTypeface(myCustomFont);
        bt.setTypeface(myCustomFont);
        et.setTypeface(myCustomFont);

       // Read var from Intent
        Intent intent = getIntent();
        final String Namefood = intent.getStringExtra("Namefood");

       // Show Data
        ShowData(Namefood);

        // btnSave (Save)
        final Button save = (Button) findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 // If Save Complete
                if(UpdateData(Namefood))
                {
                }
            }
        });
    }
    public void ShowData(String Namefood)
    {
        final TextView tName = (TextView) findViewById(R.id.namefood);
        final EditText ecomment = (EditText) findViewById(R.id.editText);
        final RatingBar rating = (RatingBar) findViewById(R.id.ratingBar2);


        // new Class DB
        final myDBClass myDb = new myDBClass(this);
        SQLiteDatabase db = myDb.getWritableDatabase();
        // Show Data
        String arrData[] = myDb.SelectData(Namefood);

        if(arrData != null)
        {
            tName.setText(arrData[1]);
            ecomment.setText(arrData[3]);
            rating.setRating(Integer.parseInt(arrData[4]));
            String strPath = "/mnt/sdcard/Pictures/"+arrData[2];
            ImageView imgPhoto = (ImageView) findViewById(R.id.imageView4);
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            Bitmap resizedbitmap = Bitmap.createBitmap(bm);
            imgPhoto.setImageBitmap(resizedbitmap);
        }
        String arrData1[] = myDb.SelectDataFood(Namefood);
        String allergic[] = myDb.SelectDataSet("1");
        String warningText = "";
        for (int i = 9; i <= 14; i++ ) {
            if (arrData1[4].equals(allergic[i])) {
                warningText = "เมนูนี้มีวัตถุดิบที่เป็น "+allergic[i].toString();
           }
        }
        if (!warningText.equals("")) {
            Toast.makeText(this, warningText, Toast.LENGTH_LONG).show();
         }
    }
    public boolean UpdateData(String Namefood) {
        final myDBClass myDb1 = new myDBClass(this);
        String arrData[] = myDb1.SelectData(Namefood);
        final EditText ecomment = (EditText) findViewById(R.id.editText);
        String strPath = ""+arrData[2];
        final RatingBar rating = (RatingBar) findViewById(R.id.ratingBar2);
        rating.setNumStars(0);
        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        // Check Name
        if(ecomment.getText().length() == 0)
        {
            ad.setMessage("กรุณาแสดงความคิดเห็น ");
            ad.show();
            ecomment.requestFocus();
            return false;
        }

        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Save Data
        long saveStatus = myDb.UpdateData(Namefood, strPath, ecomment.getText().toString(), rating.getRating());
        if(saveStatus <=  0)
        {
            ad.setMessage("Error!! ");
            ad.show();
            return false;
        }
        Toast.makeText(Bookmark.this,"บันทึกข้อมูลแล้ว ",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bookmark, menu);
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
