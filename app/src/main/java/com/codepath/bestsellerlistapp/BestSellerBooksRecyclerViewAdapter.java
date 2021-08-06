package com.codepath.bestsellerlistapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.bestsellerlistapp.models.BestSellerBook;

import org.w3c.dom.Text;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BestSellerBook} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class BestSellerBooksRecyclerViewAdapter extends RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder> {
    Context context;
    private final List<BestSellerBook> books;
    private final OnListFragmentInteractionListener mListener;

    public BestSellerBooksRecyclerViewAdapter(List<BestSellerBook> items, OnListFragmentInteractionListener listener) {
        books = items;
        mListener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_best_seller_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, final int position) {
        holder.mItem = books.get(position);
        holder.BookTitle.setText(books.get(position).title);
        holder.BookAuthor.setText(books.get(position).author);
        holder.background.setText(books.get(position).description);
        holder.ranking.setText(Integer.toString(books.get(position).rank));
        Glide.with(holder.mView.getContext()).load(books.get(position).bookImageUrl).into(holder.ivImage);
        holder.buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(books.get(position).amazonUrl));
                    holder.mView.getContext().startActivity(intent);
                    mListener.onItemClick(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView BookTitle;
        public final TextView BookAuthor;
        Button buy_button;
        TextView background;
        ImageView ivImage;
        TextView ranking;
        public BestSellerBook mItem;


        public BookViewHolder(View view) {
            super(view);
            mView = view;
            BookTitle = view.findViewById(R.id.tvTitle);
            BookAuthor = view.findViewById(R.id.tvAuthor);
            ivImage = view.findViewById(R.id.ivBook);
            background = view.findViewById(R.id.tvBackground);
            ranking = view.findViewById(R.id.tvRanking);
            buy_button = view.findViewById(R.id.buy_button);

        }

        @Override
        public String toString() {
            return BookTitle.toString() + " '" + BookAuthor.getText() + "'";
        }
    }
}
