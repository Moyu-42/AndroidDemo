package com.moyu;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Contacts extends Activity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        et = (EditText)this.findViewById(R.id.editText1);
    }

    public void jump_dial(View view) {
        Intent intent = new Intent();
        intent.setClass(Contacts.this, MainActivity.class);
        startActivity(intent);
    }
    public void jump_recodes(View view) {
        Intent intent = new Intent();
        intent.setClass(Contacts.this, Recodes.class);
        startActivity(intent);
    }
}
