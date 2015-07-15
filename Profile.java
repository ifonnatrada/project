package com.example.win81pro.icooking2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Profile extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView t5 = (TextView)findViewById(R.id.textView5);
        TextView t6 = (TextView)findViewById(R.id.textView6);
        TextView t7 = (TextView)findViewById(R.id.textView7);
        TextView t8 = (TextView)findViewById(R.id.textView8);
        EditText e6 = (EditText)findViewById(R.id.editText_name);
        EditText e7 = (EditText)findViewById(R.id.editText_age);
        RadioButton rg = (RadioButton)findViewById(R.id.Male);
        RadioButton rg2 = (RadioButton)findViewById(R.id.Female);
        Button bt = (Button)findViewById(R.id.btsave);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"THSarabun Bold.ttf");
        t5.setTypeface(myCustomFont);
        t6.setTypeface(myCustomFont);
        t7.setTypeface(myCustomFont);
        t8.setTypeface(myCustomFont);
        e6.setTypeface(myCustomFont);
        e7.setTypeface(myCustomFont);
        rg.setTypeface(myCustomFont);
        rg2.setTypeface(myCustomFont);
        bt.setTypeface(myCustomFont);

        myDBClass myDb = new myDBClass(this);
        SQLiteDatabase db = myDb.getWritableDatabase();

        String test = "SELECT * FROM Profile;";
        Cursor cu = db.rawQuery(test,null);
        if (cu.moveToFirst()){
            if (cu.getString(1) != null){
                Intent newActivity = new Intent(this,ChatBubbleActivity.class);
                startActivity(newActivity);
            }
        }

        // btnSave (Save)
        final Button save = (Button) findViewById(R.id.btsave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if (SaveProfile()) {
                    // Open Form Main
                    Intent newActivity = new Intent(Profile.this,ChatBubbleActivity.class);
                    startActivity(newActivity);
                }
            }
        });
      }

    //Save Button
    public boolean SaveProfile() {

        final EditText tName = (EditText) findViewById(R.id.editText_name);
        final EditText tAge = (EditText) findViewById(R.id.editText_age);
        //final RadioButton RGender = (RadioButton) findViewById(R.id.rgGroup);
        String RGender;

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        // Check Name
        if (tName.getText().length() < 2 || tName.getText().length() > 8 ){
            adb.setTitle("คำเตือน")
                    .setIcon(getResources().getDrawable(R.drawable.warnning))
                    .setMessage("กรุณาใส่ชื่อเล่นอย่างน้อย 2 ตัวอักษร")
                    .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            tName.requestFocus();
                        }
                    })
                    .show();
            return false;
        }
        // Check Age
        if (tAge.getText().length() == 0|| tAge.getText().length() >4 ){
            adb.setTitle("คำเตือน")
                    .setIcon(getResources().getDrawable(R.drawable.warnning))
                    .setMessage("กรุณาใส่อายุ")
                    .setPositiveButton("ตกลง",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
                            tAge.requestFocus();
                        }
                    })
                    .show();
            tName.requestFocus();
            return false;
        }

        //check gender male or female
        RadioGroup rgSex = (RadioGroup) findViewById(R.id.rgGroup);
        if (rgSex.getCheckedRadioButtonId()== R.id.Male) {
            //MyActivity.profile.setSex("ชาย");
            RGender= "ผู้ชาย";
        } else {
            //MyActivity.profile.setSex("หญิง");
            RGender = "ผู้หญิง";
        }

        //new Class DB
        final myDBClass myDb = new myDBClass(this);

        //Save Data
        long saveStatus = myDb.InsertProfile(tName.getText().toString(),
                tAge.getText().toString(),
                RGender);
        if (saveStatus <= 0) {
            ad.setMessage("Error!!");
            ad.show();
            return false;
        }
           Toast.makeText(Profile.this, "บันทึกข้อมูลส่วนตัวเรียบร้อยแล้ว ", Toast.LENGTH_SHORT).show();
            return true;
        }

    private void setSex(String male) {
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */


}

