package com.example.win81pro.icooking2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Cook extends TabActivity {
    TabHost mTabHost;
    String arrData[];
    String arrData1[];
    SQLiteDatabase db;
    ImageButton bookmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        bookmark = (ImageButton)findViewById(R.id.bookmark);

        TextView tname = (TextView) findViewById(R.id.namefood);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "THSarabun Bold.ttf");
        tname.setTypeface(myCustomFont);

        // Read var from Intent
        Intent intent = getIntent();
        String Namefood = intent.getStringExtra("Namefood");
        ShowData(Namefood);

        Resources res = getResources();
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("").setIndicator("", res.getDrawable(R.drawable.ic_tab_img)).setContent(R.id.tab1));

        Intent intent1 = new Intent().setClass(this, Material.class);
        mTabHost.addTab(mTabHost.newTabSpec("").setIndicator("", res.getDrawable(R.drawable.ic_tab_mat)).setContent(intent1));

        Intent intent2 = new Intent().setClass(this, Howto.class);
        mTabHost.addTab(mTabHost.newTabSpec("").setIndicator("", res.getDrawable(R.drawable.ic_tab_how)).setContent(intent2));

        Intent intent3 = new Intent().setClass(this, Vedio.class);
        mTabHost.addTab(mTabHost.newTabSpec("").setIndicator("", res.getDrawable(R.drawable.ic_tab_video)).setContent(intent3));
        mTabHost.setCurrentTab(0);

    }

        public void onClick_bookmark(View v) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cook.this);
            // Setting Dialog Message
            alertDialog.setMessage("คุณต้องการจะบันทึกเมนูอาหาร");
            alertDialog.setPositiveButton("ไม่ใช่",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (DeleteData()) {
                                bookmark.setBackgroundResource(R.drawable.unbookmark);
                               dialog.cancel();
                            }
                        }
                    });
            alertDialog.setNegativeButton("ใช่",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                              if (SaveData()){
                                  bookmark.setBackgroundResource(R.drawable.bookmark);
                              }
                        }
                    });
            alertDialog.show();
        }


    public void ShowData(String Namefood)
    {
        final TextView tName = (TextView) findViewById(R.id.namefood);
        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        String arrTemp[] = myDb.SelectData(Namefood);
        // Show Data
        String arrData[] = myDb.SelectDataFood(Namefood);
        if(arrData != null)
        {
            tName.setText(arrData[1]);
            String strPath = "/mnt/sdcard/Pictures/"+arrData[7];
            ImageView imgPhoto = (ImageView) findViewById(R.id.imagefood);
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            Bitmap resizedbitmap = Bitmap.createBitmap(bm);
            imgPhoto.setImageBitmap(resizedbitmap);
        }

        /*if (arrTemp[1] != null) {
            bookmark.setBackgroundResource(R.drawable.bookmark);
        }
        else {
            bookmark.setBackgroundResource(R.drawable.unbookmark);
        }*/
    }

    //Save Bookmark
    public boolean SaveData() {
        String strNamefood = ""+arrData[1];
        String strPath = ""+arrData[7];
        String strComment = " "+arrData[3];
        String strVote = " "+arrData[4];
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        //new Class DB
        myDBClass myDb = new myDBClass(this);
        arrData1 = myDb.SelectData(strNamefood);
        SQLiteDatabase db = myDb.getWritableDatabase();
      if (arrData1[1] == null) {

          //Save Data
          long saveStatus = myDb.InsertBookmark(strNamefood, strPath, "", "0");
          if (saveStatus <= 0) {
              ad.setMessage("Error!!");
              ad.show();
              return false;
          }
          Toast.makeText(Cook.this, "บันทึกเมนูอาหารแล้ว ", Toast.LENGTH_SHORT).show();
      }
          return true;

    }

    //Save Bookmark
    public boolean DeleteData() {
        String strNamefood = ""+arrData[1];
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        //new Class DB
        myDBClass myDb = new myDBClass(this);
        long x = myDb.DeleteData(strNamefood);
       // SQLiteDatabase db = myDb.getWritableDatabase();

        Toast.makeText(Cook.this, "ลบเมนูอาหารออกแล้ว ", Toast.LENGTH_SHORT).show();

        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cook, menu);
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
