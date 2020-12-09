package com.moyu;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Contacts extends Activity {

    ListView contactsView;
    ArrayAdapter<String> adapter;
    List<MyContacts> contactsList=new ArrayList<MyContacts>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        contactsView=(ListView)findViewById(R.id.contacts_view);
        Adapter adapter=new Adapter(this, R.layout.myitem, contactsList);
        contactsView.setAdapter(adapter);
        readContacts();
    }
    private void readContacts(){
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        ContentResolver resolver = getContentResolver();
        Cursor c = resolver.query(uri, new String[] { "_id" }, null, null, null);
        try{
            while(c.moveToNext()){
                String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                uri = Uri.parse("content://com.android.contacts/contacts/" + contactId + "/data");
                Cursor cursor = resolver.query(uri, new String[] { "mimetype",
                        "data1", "data2" }, null, null, null);

                MyContacts bean = new MyContacts();
                while (cursor.moveToNext()) {
                    String data1 = cursor.getString(cursor.getColumnIndex("data1"));
                    String mimetypeId = cursor.getString(cursor
                            .getColumnIndex("mimetype"));
                    if (TextUtils
                            .equals(mimetypeId, "vnd.android.cursor.item/name")) {
                        // 姓名
                        bean.setName(data1);
                    } else if (TextUtils.equals(mimetypeId,
                            "vnd.android.cursor.item/email_v2")) {
                        // email
                        bean.setEmail(data1);
                    } else if (TextUtils.equals(mimetypeId,
                            "vnd.android.cursor.item/phone_v2")) {
                        // 电话
                        bean.setPhoneno(data1);
                    }
                }
                Bitmap photo = getContactPhoto(getApplicationContext(), contactId, R.drawable.ic_launcher);
                bean.setPhoto(photo);

                contactsList.add(bean);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            if(c!=null){
                c.close();
            }
        }
    }

    private static Bitmap getContactPhoto(Context c, String personId,
                                          int defaultIco) {
        byte[] data = new byte[0];
        Uri u = Uri.parse("content://com.android.contacts/data");
        String where = "raw_contact_id = " + personId
                + " AND mimetype ='vnd.android.cursor.item/photo'";
        Cursor cursor = c.getContentResolver()
                .query(u, null, where, null, null);
        if (cursor.moveToFirst()) {
            data = cursor.getBlob(cursor.getColumnIndex("data15"));
        }
        cursor.close();
        if (data == null || data.length == 0) {
            return BitmapFactory.decodeResource(c.getResources(), defaultIco);
        } else
            return BitmapFactory.decodeByteArray(data, 0, data.length);
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