package com.example.digitalbooks.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digitalbooks.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeBookAdapter extends RecyclerView.Adapter<HomeBookAdapter.ViewHolder> {
    // creating variables for arraylist and context.
    private ArrayList<HomeBookInfo> homeBookInfoArrayList;
    private Context mContext;

    // creating constructor for array list and context.
    public HomeBookAdapter(ArrayList<HomeBookInfo> homeBookInfoArrayList, Context mContext) {
        this.homeBookInfoArrayList = homeBookInfoArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HomeBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeBookAdapter.ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBookAdapter.ViewHolder holder, int position) {

        // inside on bind view holder method we are
        // setting ou data to each UI component.
        HomeBookInfo homeBookInfo = homeBookInfoArrayList.get(position);
        holder.nameTV.setText(homeBookInfo.getTitle());
        holder.authorTV.setText (homeBookInfo.getAuthors());
        holder.publisherTV.setText(homeBookInfo.getPublisher());

        // below line is use to set image from URL in our image view.
        String imgProtocol = homeBookInfo.getThumbnail().replace("http", "https");
        Glide.with(mContext)
                .load(imgProtocol)
                .into(holder.bookIV);

        // below line is use to add on click listener for our item of recycler view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click listener method we are calling a new activity
                // and passing all the data of that item in next intent.
                Intent i = new Intent(mContext, HomeBookDetails.class);
                i.putExtra("title", homeBookInfo.getTitle());
                i.putExtra("subtitle", homeBookInfo.getSubtitle());
                i.putExtra("authors", homeBookInfo.getAuthors());
                i.putExtra("publisher", homeBookInfo.getPublisher());
                i.putExtra("publishedDate", homeBookInfo.getPublishedDate());
                i.putExtra("description", homeBookInfo.getDescription());
                i.putExtra("pageCount", homeBookInfo.getPageCount());
                i.putExtra("thumbnail", homeBookInfo.getThumbnail());
                i.putExtra("previewLink", homeBookInfo.getPreviewLink());
                i.putExtra("infoLink", homeBookInfo.getInfoLink());
                i.putExtra("buyLink", homeBookInfo.getBuyLink());

                // after passing that data we are
                // starting our new  intent.
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // inside get item count method we
        // are returning the size of our array list.
        return homeBookInfoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // below line is use to initialize
        // our text view and image views.
        TextView nameTV, publisherTV, authorTV;
        ImageView bookIV;

        ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.book_title);
            publisherTV = itemView.findViewById(R.id.book_publisher);
            authorTV = itemView.findViewById(R.id.book_writer);
            bookIV = itemView.findViewById(R.id.book_image);
        }
    }
}
