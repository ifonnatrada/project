package com.example.win81pro.icooking2;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import org.alicebot.ab.utils.IOUtils;
import org.nazt.lexto.LongLexTo;
import org.alicebot.ab.Bot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;


public class ChatBubbleActivity extends TabActivity {
    TabHost mTabHost;
    Bot bot;
    org.alicebot.ab.Chat chatSession;

    private static final String TAG = "ChatActivity";
    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;

    Intent intent;
    private boolean side = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Button bt = (Button)findViewById(R.id.buttonSend);
        EditText ct = (EditText)findViewById(R.id.chatText);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"THSarabun Bold.ttf");
        bt.setTypeface(myCustomFont);
        ct.setTypeface(myCustomFont);

        Resources res = getResources();
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("แชท").setIndicator("",res.getDrawable(R.drawable.ic_tab_chat)).setContent(R.id.tab1));

        Intent intent1 = new Intent().setClass(ChatBubbleActivity.this, PreBookmark.class);
        mTabHost.addTab(mTabHost.newTabSpec("").setIndicator("",res.getDrawable(R.drawable.ic_tab_bookmark)).setContent(intent1));

        Intent intent2 = new Intent().setClass(ChatBubbleActivity.this, Setting.class);
        mTabHost.addTab(mTabHost.newTabSpec("").setIndicator("",res.getDrawable(R.drawable.ic_tab_setting)).setContent(intent2));

        Intent intent3 = new Intent().setClass(ChatBubbleActivity.this, MyActivity.class);
        mTabHost.addTab(mTabHost.newTabSpec("").setIndicator("",res.getDrawable(R.drawable.ic_tab_info)).setContent(intent3));
        mTabHost.setCurrentTab(0);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        listView = (ListView) findViewById(R.id.listView1);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_chat_singlemessage);



        listView.setAdapter(chatArrayAdapter);
        chatText = (EditText) findViewById(R.id.chatText);
        chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        final String path = getExternalFilesDir(null).getAbsolutePath();
        bot = new Bot("testbot",path); // 1. แก้บอท ชื่อบอท
        chatSession = new org.alicebot.ab.Chat(bot); //สวัสดีค่ะ

       /* AssetManager ass = getAssets();
        try {
            InputStream is = ass.open("lexitron.txt");
            Scanner scanner = new Scanner(is).useDelimiter("\\A");

            String[] dict = scanner.next().split("\n");

            String input = "กรนกรมกรมการไก่ย่างไก่ต้มไก่ทอดไส้อั่วไฮโซ"; // ดึงข้อความจาก text หน้าแชท เอามาไส่ใน
            LongLexTo lexicons = new LongLexTo(); // <<< input
            for(String s : dict)
                lexicons.addDict(s);

            //Log.d("Lex",">" + dict);
            lexicons.wordInstance(input);

            Log.d("TEST", ">>"+lexicons.getIndexList().toString());
            int begin = lexicons.first();
            int end = 0;
            String output = "";
            while (lexicons.hasNext()){
               end = lexicons.next();
               String s = input.substring(begin,end);
                begin = end;
                Log.d("Lex", ">" + s);
                output += s + " ";
            }

            Log.d("RESULT", output); // output ส่งให้ AIML

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    private boolean sendChatMessage(){
        String request = chatText.getText().toString();  //ดึงข้อมูลมาจาก chat text
        String response = chatSession.multisentenceRespond(request); //ส่งไปให้ บอท ประมวลผล
        chatArrayAdapter.add(new ChatMessage(true, chatText.getText().toString())); //แสดงผลข้อความ
        chatArrayAdapter.add(new ChatMessage(false,response)); //แสดงผลลัพที่ได้จากการคำนวน
        chatText.setText("");  //เคลียบอททิ้ง
        side = !side;
        return true;
    }
}