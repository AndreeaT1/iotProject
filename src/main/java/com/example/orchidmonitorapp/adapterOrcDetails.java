
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

public class adapterOrcDetails extends RecyclerView.Adapter<adapterOrcDetails.ViewHolder> {

    private List<orchidDetails> details;
    private LayoutInflater mInflater;

    adapterOrcDetails(Context context, List<orchidDetails> data) {

        this.mInflater = LayoutInflater.from(context);
        this.details = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.orchid_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( adapterOrcDetails.ViewHolder holder, int position) {
        String title = details.get(position).title;
        String level = details.get(position).level;
        String comm = details.get(position).comm;
        int image = details.get(position).image;

        holder.myTextView2.setText(title);
        holder.myTextViewLevel.setText(level);
        holder.myTextViewComm.setText(comm);
        holder.myImage2.setImageResource(image);


        /*holder.myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), OrchidPage.class);
                intent.putExtra("selectedOrchid", orchids.get(position).name);
                view.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView myTextView2;
        TextView myTextViewLevel;
        TextView myTextViewComm;
        ImageView myImage2;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView2 = itemView.findViewById(R.id.titleDetails);
            myTextViewLevel = itemView.findViewById(R.id.levelDetailsTxt);
            myTextViewComm = itemView.findViewById(R.id.textComment);
            myImage2 = itemView.findViewById(R.id.detailsImage);
        }
    }

}

