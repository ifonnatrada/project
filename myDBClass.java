package com.example.win81pro.icooking2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Win8.1PRO on 31/1/2558.
 */

public class myDBClass extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mydatabase.db";

    // Table Name
    private static final String TABLE_PROFILE = "Profile";
    private static final String TABLE_SETDATA = "Setdata";
    private static final String TABLE_FOOD = "Food";
    private static final String TABLE_BOOKMARK = "Bookmark";


    public myDBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PROFILE +
                "(IDProfile INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Name  TEXT NOT NULL," +
                " Age INTEGER NOT NULL," +
                " Gender INTEGER);");

        db.execSQL("CREATE TABLE " + TABLE_SETDATA +
                "(IDSetdata INTEGER PRIMARY KEY AUTOINCREMENT," +
                "typelike1 Varchar(10)," +
                "typelike2 Varchar(10)," +
                "typelike3 Varchar(10)," +
                "typelike4 Varchar(10)," +
                "nationlike1 Varchar(10)," +
                "nationlike2 Varchar(10)," +
                "nationlike3 Varchar(10)," +
                "nationlike4 Varchar(10)," +
                "allergic1 Varchar(10)," +
                "allergic2 Varchar(10)," +
                "allergic3 Varchar(10)," +
                "allergic4 Varchar(10)," +
                "allergic5 Varchar(10)," +
                "allergic6 Varchar(10));");

        db.execSQL("CREATE TABLE " + TABLE_FOOD +
                "(IDFood INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Namefood TEXT," +
                "Typefood Text(20)," +
                "Nationfood Text(20)," +
                "Materialmain Text(30)," +
                "Materialdetail TEXT," +
                "Howmake TEXT," +
                "Picture Varchar(100)," +
                "Video Varchar(100));");


        db.execSQL("CREATE TABLE " + TABLE_BOOKMARK +
                "(IDBookmark INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Namefood TEXT," +
                "Picture Varchar(100)," +
                "Comment TEXT," +
                "Vote NUMERIC);");

        Log.d("CREATE TABLE", "Create Table Successfully.");
    }

    // InsertProfile
    public long InsertProfile(String strName, String strAge, String strGender) {
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data
            ContentValues Val = new ContentValues();
            Val.put("Name", strName);
            Val.put("Age", strAge);
            Val.put("Gender", strGender);
            long rows = db.insert(TABLE_PROFILE, null, Val);
            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }
    }


    // InsertBookmark
    public long InsertBookmark(String strNamefood,String strPath,String strComment,String strVote){
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data
            ContentValues Val = new ContentValues();
            Val.put("Namefood", strNamefood);
            Val.put("Picture", strPath);
            Val.put("Comment", strComment);
            Val.put("Vote", strVote);
            long rows = db.insert(TABLE_BOOKMARK, null, Val);
            db.close();
            return rows; // return rows inserted.
        } catch (Exception e) {
            return -1;
        }
    }

    public long InsertSetting(String strtypelike1,String strtypelike2,String strtypelike3,String strtypelike4,
                              String strnationlike1,String strnationlike2,String strnationlike3,String strnationlike4,
                              String strallergic1,String strallergic2,String strallergic3,String strallergic4,String strallergic5,
                              String strallergic6) {
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            ContentValues Val = new ContentValues();

            Val.put("typelike1", strtypelike1);
            Val.put("typelike2", strtypelike2);
            Val.put("typelike3", strtypelike3);
            Val.put("typelike4", strtypelike4);
            Val.put("nationlike1", strnationlike1);
            Val.put("nationlike2", strnationlike2);
            Val.put("nationlike3", strnationlike3);
            Val.put("nationlike4", strnationlike4);
            Val.put("allergic1", strallergic1);
            Val.put("allergic2", strallergic2);
            Val.put("allergic3", strallergic3);
            Val.put("allergic4", strallergic4);
            Val.put("allergic5", strallergic5);
            Val.put("allergic6", strallergic6);

            long rows = db.insert(TABLE_SETDATA, null, Val);
            db.close();
            return rows; // return rows inserted.
        } catch (Exception e) {
            return -1;
        }

    }
    // Select Data
    public String[] SelectDataSet(String strIDSetdata) {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            Cursor cursor = db.query(TABLE_SETDATA, new String[] { "*" },"IDSetdata=?",
                    new String[] { String.valueOf(strIDSetdata) }, null, null, null, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                    arrData[4] = cursor.getString(4);
                    arrData[5] = cursor.getString(5);
                    arrData[6] = cursor.getString(6);
                    arrData[7] = cursor.getString(7);
                    arrData[8] = cursor.getString(8);
                    arrData[9] = cursor.getString(9);
                    arrData[10] = cursor.getString(10);
                    arrData[11] = cursor.getString(11);
                    arrData[12] = cursor.getString(12);
                    arrData[13] = cursor.getString(13);
                    arrData[14] = cursor.getString(14);

                }
            }
            cursor.close();
            db.close();
            return arrData;
        }catch (Exception e) {
            return null;
        }
    }
    // Select Data
    public String[] SelectData(String strNamefood) {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            Cursor cursor = db.query(TABLE_BOOKMARK, new String[] { "*" },"Namefood=?",
                    new String[] { String.valueOf(strNamefood) }, null, null, null, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];

                    /***
                     *  0 = IDFood
                     *  1 = Namefood
                     *  2 = Picture
                     *  3 = Comment
                     *  4 = Vote
                     */
                   // arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                    arrData[4] = cursor.getString(4);

                }
            }
            cursor.close();
            db.close();
            return arrData;
        }catch (Exception e) {
            return null;
        }
    }

    // Select All Data
    public String[][] SelectAllData() {
        // TODO Auto-generated method stub
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT  * FROM " + TABLE_BOOKMARK;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];

                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }
    // Select All Data
    public String[][] SelectAllDatafood() {
        // TODO Auto-generated method stub
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT  * FROM " + TABLE_FOOD;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];

                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }

        // Show All Data
    public ArrayList<HashMap<String, String>> SelectAllDataBookmark() {
        // TODO Auto-generated method stub
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT  * FROM " + TABLE_BOOKMARK;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("IDFood", cursor.getString(0));
                        map.put("Namefood", cursor.getString(1));
                        map.put("Picture", cursor.getString(2));
                        map.put("Comment", cursor.getString(3));
                        map.put("Vote", cursor.getString(4));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;
        } catch (Exception e) {
            return null;
        }
    }

    // Select Data Food
    public String[] SelectDataFood(String strNamefood) {
    // TODO Auto-generated method stub
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            Cursor cursor = db.query(TABLE_FOOD, new String[] { "*" },"Namefood=?",
            new String[] { String.valueOf(strNamefood) }, null, null, null, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                /*
                *  0 = IDFood
                *  1 = Namefood
                *  5 = Materialdetail
                *  6 = Howmake
                *  7 = Picture
                *  8 = Video
                */
                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[4] = cursor.getString(4);
                    arrData[5] = cursor.getString(5);
                    arrData[6] = cursor.getString(6);
                    arrData[7] = cursor.getString(7);
                    arrData[8] = cursor.getString(8);
                }
            }
            cursor.close();
            db.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }
    // Show All Data Food
    public ArrayList<HashMap<String, String>> SelectAllDataFood() {
    // TODO Auto-generated method stub
        try {
            ArrayList<HashMap<String, String>> MyArrList1 = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT  * FROM " + TABLE_FOOD;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("Namefood", cursor.getString(1));
                        map.put("Materialdetail", cursor.getString(5));
                        map.put("Howmake", cursor.getString(6));
                        map.put("Picture", cursor.getString(7));
                        map.put("Video", cursor.getString(8));

                        MyArrList1.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList1;
        } catch (Exception e) {
            return null;
        }
    }

    // Update Data
    public long UpdateSetting(String strIDSetdata,String strtypelike1,String strtypelike2,String strtypelike3,String strtypelike4,
                              String strnationlike1,String strnationlike2,String strnationlike3,String strnationlike4,
                              String strallergic1,String strallergic2,String strallergic3,String strallergic4,String strallergic5,
                              String strallergic6) {
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();

            Val.put("IDSetdata", strIDSetdata);
            Val.put("typelike1", strtypelike1);
            Val.put("typelike2", strtypelike2);
            Val.put("typelike3", strtypelike3);
            Val.put("typelike4", strtypelike4);
            Val.put("nationlike1", strnationlike1);
            Val.put("nationlike2", strnationlike2);
            Val.put("nationlike3", strnationlike3);
            Val.put("nationlike4", strnationlike4);
            Val.put("allergic1", strallergic1);
            Val.put("allergic2", strallergic2);
            Val.put("allergic3", strallergic3);
            Val.put("allergic4", strallergic4);
            Val.put("allergic5", strallergic5);
            Val.put("allergic6", strallergic6);

            long rows = db.update(TABLE_SETDATA, Val, " IDSetdata = ?",
                    new String[] { String.valueOf(strIDSetdata) });
            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }
    }
    // Update Data
    public long UpdateData(String strNamefood,String strPath,String strComment, float strVote) {
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();
            Val.put("Namefood", strNamefood);
            Val.put("Picture", strPath);
            Val.put("Comment", strComment);
            Val.put("Vote", strVote);
            long rows = db.update(TABLE_BOOKMARK, Val, " Namefood = ?",
                    new String[] { String.valueOf(strNamefood) });
            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }
    }

    // Delete Data
    public long DeleteData(String strNamefood) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            long rows = db.delete(TABLE_BOOKMARK, "Namefood = ?",
                    new String[] { String.valueOf(strNamefood) });

            db.close();
            return rows; // return rows deleted.

        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETDATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARK);

        // Re Create on method  onCreate
        onCreate(db);
    }
    public void open() {

    }



}