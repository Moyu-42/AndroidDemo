package com.moyu;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Contacts extends Activity {

    ListView contactsView;
    ArrayAdapter<String> adapter;
    List<String> contactsList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        contactsView=(ListView)findViewById(R.id.contacts_view);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);
        readContacts();
    }
    private void readContacts(){
        Cursor cursor=null;
        try{
            //查询联系人数据
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            while(cursor.moveToNext()){
                //获取联系人姓名
                String displayName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //获取联系人的手机名
                String number=cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName+"\n"+number);

            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            if(cursor!=null){
                cursor.close();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void create(View view) {
        Intent intent = new Intent();
        intent.setClass(Contacts.this, CreateContact.class);
        startActivity(intent);
    }
    public void recodes(View view) {
        Intent intent = new Intent();
        intent.setClass(Contacts.this, Recodes.class);
        startActivity(intent);
    }
    public void jump_dial(View view) {
        Intent intent = new Intent();
        intent.setClass(Contacts.this, MainActivity.class);
        startActivity(intent);
    }
}