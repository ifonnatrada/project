package com.example.win81pro.icooking2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.view.View.OnClickListener;

public class Setting extends Activity {
    String checked1,checked2,checked3,checked4,checked5,checked6,checked7,
            checked8,checked9,checked10,checked11,checked12,checked13,checked14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView t2 = (TextView)findViewById(R.id.textView2);
        TextView t3 = (TextView)findViewById(R.id.textView3);
        TextView t4 = (TextView)findViewById(R.id.textView4);
        CheckBox ch1 = (CheckBox)findViewById(R.id.cktype1);
        CheckBox ch2 = (CheckBox)findViewById(R.id.cktype2);
        CheckBox ch3 = (CheckBox)findViewById(R.id.cktype3);
        CheckBox ch4 = (CheckBox)findViewById(R.id.cktype4);
        CheckBox ch5 = (CheckBox)findViewById(R.id.checknation1);
        CheckBox ch6 = (CheckBox)findViewById(R.id.checknation2);
        CheckBox ch7 = (CheckBox)findViewById(R.id.checknation3);
        CheckBox ch8 = (CheckBox)findViewById(R.id.checknation4);
        CheckBox ch9 = (CheckBox)findViewById(R.id.checkallergic1);
        CheckBox ch10 = (CheckBox)findViewById(R.id.checkallergic2);
        CheckBox ch11 = (CheckBox)findViewById(R.id.checkallergic3);
        CheckBox ch12 = (CheckBox)findViewById(R.id.checkallergic4);
        CheckBox ch13 = (CheckBox)findViewById(R.id.checkallergic5);
        CheckBox ch14 = (CheckBox)findViewById(R.id.checkallergic6);
        Button bt1 = (Button)findViewById(R.id.btsavesetting);
        Button bt2 = (Button)findViewById(R.id.btcancle);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"THSarabun Bold.ttf");
        t2.setTypeface(myCustomFont);
        t3.setTypeface(myCustomFont);
        t4.setTypeface(myCustomFont);
        ch1.setTypeface(myCustomFont);
        ch2.setTypeface(myCustomFont);
        ch3.setTypeface(myCustomFont);
        ch4.setTypeface(myCustomFont);
        ch5.setTypeface(myCustomFont);
        ch6.setTypeface(myCustomFont);
        ch7.setTypeface(myCustomFont);
        ch8.setTypeface(myCustomFont);
        ch9.setTypeface(myCustomFont);
        ch10.setTypeface(myCustomFont);
        ch11.setTypeface(myCustomFont);
        ch12.setTypeface(myCustomFont);
        ch13.setTypeface(myCustomFont);
        ch14.setTypeface(myCustomFont);
        bt1.setTypeface(myCustomFont);
        bt2.setTypeface(myCustomFont);

        myDBClass myDb = new myDBClass(this);
        SQLiteDatabase db = myDb.getWritableDatabase();
        db.close();

        ShowData();

        // btnSave (Save)
        final Button save = (Button) findViewById(R.id.btsavesetting);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               if(UpdateSetting()){
               }
            }
        });
       }

    public boolean UpdateSetting() {
        final myDBClass myDb1 = new myDBClass(this);
        String arrData[] = myDb1.SelectDataSet("1");

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        CheckBox chk1 = (CheckBox) findViewById(R.id.cktype1);
        if (chk1.isChecked()){
            checked1 = "ต้ม";
        }
        else{
            checked1 = "";
        }

        CheckBox chk2 = (CheckBox) findViewById(R.id.cktype2);
            if (chk2.isChecked()){
                checked2 = "นึ่ง";
            }
            else{
                checked2 = "";
            }


        CheckBox chk3 = (CheckBox) findViewById(R.id.cktype3);
        if (chk3.isChecked()){
            checked3 = "ทอด";
        }
        else{
            checked3 = "";
        }

          CheckBox chk4 = (CheckBox) findViewById(R.id.cktype4);
        if (chk4.isChecked()){
            checked4 = "ผัด";
        }
        else{
            checked4 = "";
        }

            CheckBox chk5 = (CheckBox) findViewById(R.id.checknation1);
        if (chk5.isChecked()){
            checked5 = "ไทย";
        }
        else{
            checked5 = "";
        }

         CheckBox chk6 = (CheckBox) findViewById(R.id.checknation2);
        if (chk6.isChecked()){
            checked6 = "จีน";
        }
        else{
            checked6 = "";
        }

            CheckBox chk7 = (CheckBox) findViewById(R.id.checknation3);
        if (chk7.isChecked()){
            checked7 = "ญี่ปุ่น";
        }
        else{
            checked7 = "";
        }

           CheckBox chk8 = (CheckBox) findViewById(R.id.checknation4);
        if (chk8.isChecked()){
            checked8 = "อิตาเลียน";
        }
        else{
            checked8 = "";
        }

             CheckBox chk9 = (CheckBox) findViewById(R.id.checkallergic1);
        if (chk9.isChecked()){
            checked9 = "หมู";
        }
        else{
            checked9 = "";
        }

             CheckBox chk10 = (CheckBox) findViewById(R.id.checkallergic2);
        if (chk10.isChecked()){
            checked10 = "ไก่";
        }
        else{
            checked10 = "";
        }

            CheckBox chk11 = (CheckBox) findViewById(R.id.checkallergic3);
        if (chk11.isChecked()){
            checked11 = "ปลา";
        }
        else{
            checked11 = "";
        }

             CheckBox chk12 = (CheckBox) findViewById(R.id.checkallergic4);
        if (chk12.isChecked()){
            checked12 = "เนื้อ";
        }
        else{
            checked12 = "";
        }

            CheckBox chk13 = (CheckBox) findViewById(R.id.checkallergic5);
        if (chk13.isChecked()){
            checked13 = "กุ้ง";
        }
        else{
            checked13 = "";
        }

             CheckBox chk14 = (CheckBox) findViewById(R.id.checkallergic6);
        if (chk14.isChecked()){
            checked14 = "ไม่แพ้";
        }
        else{
            checked14 = "";
        }

            //new Class DB
       final myDBClass myDb = new myDBClass(this);
        //Save Data
        long saveStatus = myDb.UpdateSetting("1", checked1, checked2, checked3,
                checked4, checked5, checked6, checked7, checked8, checked9,
                checked10, checked11, checked12, checked13, checked14);
        if (saveStatus <= 0) {
            ad.setMessage("Error!!");
            ad.show();
            return false;
        }
        Toast.makeText(Setting.this, "บันทึกข้อมูลแล้ว ", Toast.LENGTH_SHORT).show();
            return true;
        }

    public void onClick_canclesetting(View v) {
            ShowData();
    }
    public void ShowData()
    {
        final CheckBox chk1 = (CheckBox) findViewById(R.id.cktype1);
        final CheckBox chk2 = (CheckBox) findViewById(R.id.cktype2);
        final CheckBox chk3 = (CheckBox) findViewById(R.id.cktype3);
        final CheckBox chk4 = (CheckBox) findViewById(R.id.cktype4);
        final CheckBox chk5 = (CheckBox) findViewById(R.id.checknation1);
        final CheckBox chk6 = (CheckBox) findViewById(R.id.checknation2);
        final CheckBox chk7 = (CheckBox) findViewById(R.id.checknation3);
        final CheckBox chk8 = (CheckBox) findViewById(R.id.checknation4);
        final CheckBox chk9 = (CheckBox) findViewById(R.id.checkallergic1);
        final CheckBox chk10 = (CheckBox) findViewById(R.id.checkallergic2);
        final CheckBox chk11 = (CheckBox) findViewById(R.id.checkallergic3);
        final CheckBox chk12 = (CheckBox) findViewById(R.id.checkallergic4);
        final CheckBox chk13 = (CheckBox) findViewById(R.id.checkallergic5);
        final CheckBox chk14 = (CheckBox) findViewById(R.id.checkallergic6);

         myDBClass myDb = new myDBClass(this);
        SQLiteDatabase db = myDb.getWritableDatabase();

        // Show Data
        String arrData[] = myDb.SelectDataSet("1");
         if(arrData[1].toString().equals("ต้ม")){
            chk1.setChecked(true);
        }
        if(arrData[2].toString().equals("นึ่ง")){
            chk2.setChecked(true);
        }
        if(arrData[3].toString().equals("ทอด")){
            chk3.setChecked(true);
        }
        if(arrData[4].toString().equals("ผัด")){
            chk4.setChecked(true);
        }
        if(arrData[5].toString().equals("ไทย")){
            chk5.setChecked(true);
        }
        if(arrData[6].toString().equals("จีน")){
            chk6.setChecked(true);
        }
        if(arrData[7].toString().equals("ญี่ปุ่น")){
            chk7.setChecked(true);
        }
        if(arrData[8].toString().equals("อิตาเลียน")){
            chk8.setChecked(true);
        }

        if(arrData[9].toString().equals("หมู")){
            chk9.setChecked(true);
        }
        if(arrData[10].toString().equals("ไก่")){
            chk10.setChecked(true);
        }
        if(arrData[11].toString().equals("ปลา")){
            chk11.setChecked(true);
        }
        if(arrData[12].toString().equals("เนื้อ")){
            chk12.setChecked(true);
        }
        if(arrData[13].toString().equals("กุ้ง")){
            chk13.setChecked(true);
        }
        if(arrData[14].toString().equals("ไม่แพ้")){
            chk14.setChecked(true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting, menu);
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
