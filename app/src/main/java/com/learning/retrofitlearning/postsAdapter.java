package com.learning.retrofitlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class postsAdapter extends ArrayAdapter<posts> {

    private Context context;
    private List<posts> postsList;

    public postsAdapter(@NonNull Context context, List<posts> postsList) {
        super(context, R.layout.display_row, postsList);
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.display_row,parent,Boolean.parseBoolean(null));

        TextView userIDTV = convertView.findViewById(R.id.userIDTV);
        TextView idTV = convertView.findViewById(R.id.idTV);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView textTV = convertView.findViewById(R.id.bodyTV);

        userIDTV.setText(String.valueOf(postsList.get(position).getUserId()));
        idTV.setText(String.valueOf(postsList.get(position).getId()));
        titleTV.setText(postsList.get(position).getTitle());
        textTV.setText(postsList.get(position).getBody());

        return convertView;

    }
}
