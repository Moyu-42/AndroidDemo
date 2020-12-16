package com.moyu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter {
    private final int ImageId;

    public ContactsAdapter(Context context, int headImage, List<MyContacts> obj) {
        super(context, headImage, obj);
        ImageId = headImage;//这个是传入我们自己定义的界面
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyContacts myContacts = (MyContacts) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(ImageId, parent, false);

        LinearLayout linearLayout = view.findViewById(R.id.layout_1);

        ImageView photo = view.findViewById(R.id.photo);
        TextView name = view.findViewById(R.id.name);
        TextView phone = view.findViewById(R.id.phoneno);
        TextView email = view.findViewById(R.id.Email);

        photo.setImageBitmap(myContacts.getPhoto());
        name.setText(myContacts.getName());
        phone.setText(myContacts.getPhoneno());
        email.setText(myContacts.getEmail());

//        final ImageButton btn = (ImageButton)view.findViewById(R.id.dial);
//        btn.setTag(position);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phoneno = v.findViewById(R.id.phoneno).toString();
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneno));
//                startActivity(intent);
//            }
//        });

        return view;
    }

}
