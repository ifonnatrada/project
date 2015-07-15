package com.example.win81pro.icooking2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.Attributes;


public class PreBookmark extends Activity {
    //List <String> ImageList;
    String arrData[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_bookmark);

        final myDBClass myDb = new myDBClass(this);
        arrData = myDb.SelectAllData();
        // listView
        final ListView showl = (ListView)findViewById(R.id.showlist);
        showl.setAdapter(new ImageAdapter(this,arrData));

        // OnClick
        showl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                Intent newActivity = new Intent(PreBookmark.this, Bookmark.class);
                newActivity.putExtra("Namefood", arrData[position][1].toString());
                startActivity(newActivity);
            }
        });
    }
    public class ImageAdapter extends BaseAdapter
    {
        private Context context;
        private String[][] lis;
        public ImageAdapter(Context c, String[][] li)
        {
            // TODO Auto-generated method stub
            context = c;
            lis = li;
        }
        public int getCount() {
            // TODO Auto-generated method stub
            return lis.length;
        }
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_column, null);
            }
            // Photo
            String strPath = "/mnt/sdcard/Pictures/"+lis[position][2].toString();
            //String strPath = "drawable-hdpi"+lis[position][2].toString();
            ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imageView3);
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            imgPhoto.setImageBitmap(bm);

            //Namefood
            TextView txtName = (TextView) convertView.findViewById(R.id.textView10);
            txtName.setPadding(0, 0, 0, 0);
            txtName.setText(lis[position][1].toString());
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pre_bookmark, menu);
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
