package com.example.orchidmonitorapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterMainPage extends RecyclerView.Adapter<adapterMainPage.ViewHolder> {

    private List<Orchid> orchids;
    private LayoutInflater mInflater;

    adapterMainPage(Context context, List<Orchid> data) {

        this.mInflater = LayoutInflater.from(context);
        this.orchids = data;

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.orchid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( adapterMainPage.ViewHolder holder, int position) {
        String name = orchids.get(position).name;
        String description = orchids.get(position).description;
        int image = orchids.get(position).image;

        holder.myTextView.setText(name);
        holder.myTextViewDesc.setText(description);
        holder.myImage.setImageResource(image);

        holder.myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), OrchidPage.class);
                intent.putExtra("selectedOrchid", orchids.get(position).name);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orchids.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView myTextView;
        TextView myTextViewDesc;
        ImageView myImage;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.orchidName);
            myTextViewDesc = itemView.findViewById(R.id.orchidDesc);
            myImage = itemView.findViewById(R.id.orchidImage);
        }
    }

}
