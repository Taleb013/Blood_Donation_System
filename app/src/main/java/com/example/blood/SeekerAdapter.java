package com.example.blood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class SeekerAdapter extends ArrayAdapter<String> {

    public SeekerAdapter(@NonNull Context context, @NonNull ArrayList<String> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.seeker_item, parent, false);
        }

        // Populate data in each list item
        String currentData = getItem(position);
        TextView textView = convertView.findViewById(R.id.seekerItemText);
        textView.setText(currentData);

        return convertView;
    }
}
