package com.example.digitalbooks.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digitalbooks.R;
import com.example.digitalbooks.databinding.FragmentLibraryBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeBookDetails extends AppCompatActivity {
    // creating variables for strings,text view, image views and button.
    String title, publisher, publishedDate, description, thumbnail, previewLink, infoLink, buyLink, authors;
    int pageCount;
    private FragmentLibraryBinding binding;

    TextView titleTV, publisherTV, descTV, pageTV, publishDateTV, authorsTV;
    Button previewBtn, buyBtn;
    ImageView bookIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // initializing our views..
        titleTV = findViewById(R.id.book_title);
        authorsTV = findViewById(R.id.book_writer);
        publisherTV = findViewById(R.id.book_publisher);
        descTV = findViewById(R.id.book_description);
        pageTV = findViewById(R.id.book_pages);
        publishDateTV = findViewById(R.id.book_date);
        previewBtn = findViewById(R.id.button_preview);
        buyBtn = findViewById(R.id.button_buy);
        bookIV = findViewById(R.id.book_image);

        // getting the data which we have passed from our adapter class.
        title = getIntent().getStringExtra("title");
        authors = getIntent().getStringExtra("authors");
        publisher = getIntent().getStringExtra("publisher");
        publishedDate = getIntent().getStringExtra("publishedDate");
        description = getIntent().getStringExtra("description");
        pageCount = getIntent().getIntExtra("pageCount", 0);
        thumbnail = getIntent().getStringExtra("thumbnail");
        previewLink = getIntent().getStringExtra("previewLink");
        infoLink = getIntent().getStringExtra("infoLink");
        buyLink = getIntent().getStringExtra("buyLink");

        // after getting the data we are setting
        // that data to our text views and image view.
        titleTV.setText(title);
        authorsTV.setText(authors);
        publisherTV.setText(publisher);
        publishDateTV.setText("Published On : " + publishedDate);
        descTV.setText(description);
        pageTV.setText("No Of Pages : " + pageCount);
        String imgProtocol = thumbnail.replace("http", "https");
        Glide.with(this)
                .load(imgProtocol)
                .into(bookIV);

        // adding on click listener for our preview button.
        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previewLink.isEmpty()) {
                    // below toast message is displayed when preview link is not present.
                    Toast.makeText(HomeBookDetails.this, "No preview link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                // if the link is present we are opening
                // that link via an intent.
                Uri uri = Uri.parse(previewLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

        // initializing on click listener for buy button.
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyLink.isEmpty()) {
                    // below toast message is displaying when buy link is empty.
                    Toast.makeText(HomeBookDetails.this, "The book is out of stock", Toast.LENGTH_SHORT).show();
                    return;
                }
                // if the link is present we are opening
                // the link via an intent.
                Uri uri = Uri.parse(buyLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
    }
}