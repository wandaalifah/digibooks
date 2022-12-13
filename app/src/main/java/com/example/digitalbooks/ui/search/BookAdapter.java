package com.example.digitalbooks.ui.search;

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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    // creating variables for arraylist and context.
    private ArrayList<BookInfo> bookInfoArrayList;
    private Context mContext;

    // creating constructor for array list and context.
    public BookAdapter(ArrayList<BookInfo> bookInfoArrayList, Context mContext) {
        this.bookInfoArrayList = bookInfoArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // inside on bind view holder method we are
        // setting ou data to each UI component.
        BookInfo bookInfo = bookInfoArrayList.get(position);
        holder.nameTV.setText(bookInfo.getTitle());
        holder.publisherTV.setText(bookInfo.getPublisher());
        holder.authorsTV.setText(bookInfo.getAuthors());

        // below line is use to set image from URL in our image view.
        String imgProtocol = bookInfo.getThumbnail().replace("http", "https");
        Glide.with(mContext)
                .load(imgProtocol)
                .into(holder.bookIV);

        // below line is use to add on click listener for our item of recycler view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click listener method we are calling a new activity
                // and passing all the data of that item in next intent.
                Intent i = new Intent(mContext, BookDetails.class);
                i.putExtra("title", bookInfo.getTitle());
                i.putExtra("subtitle", bookInfo.getSubtitle());
                i.putExtra("authors", bookInfo.getAuthors());
                i.putExtra("publisher", bookInfo.getPublisher());
                i.putExtra("publishedDate", bookInfo.getPublishedDate());
                i.putExtra("description", bookInfo.getDescription());
                i.putExtra("pageCount", bookInfo.getPageCount());
                i.putExtra("thumbnail", bookInfo.getThumbnail());
                i.putExtra("previewLink", bookInfo.getPreviewLink());
                i.putExtra("infoLink", bookInfo.getInfoLink());
                i.putExtra("buyLink", bookInfo.getBuyLink());

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
        return bookInfoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // below line is use to initialize
        // our text view and image views.
        TextView nameTV, publisherTV, pageCountTV, dateTV, authorsTV;
        ImageView bookIV;

        ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVBookTitle);
            authorsTV = itemView.findViewById(R.id.idTVAuthor);
            publisherTV = itemView.findViewById(R.id.idTVpublisher);
            pageCountTV = itemView.findViewById(R.id.idTVNoOfPages);
            dateTV = itemView.findViewById(R.id.idTVPublishDate);
            bookIV = itemView.findViewById(R.id.idIVbook);
        }
    }
}
