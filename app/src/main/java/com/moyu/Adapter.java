package com.moyu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter {
    private final int ImageId;

    public Adapter(Context context, int headImage, List<MyContacts> obj) {
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

        return view;
    }

}
