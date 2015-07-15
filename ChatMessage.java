package com.example.win81pro.icooking2;

import android.app.Activity;
import android.content.Intent;

public class ChatMessage extends Activity{
    public boolean right;
    public String message;

    public ChatMessage(){
        super();
    }

    public ChatMessage(boolean right, String message) {
        super();

        this.right = right;
        this.message = message;
    }

}