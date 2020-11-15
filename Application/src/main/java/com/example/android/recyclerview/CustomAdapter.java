package com.example.android.recyclerview;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.common.converters.LocalDateTimeConverter;
import com.example.android.common.logger.Log;
import com.example.android.common.models.Entry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;

import lombok.Getter;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<Entry> mDataSet;

    public void setEntries(List<Entry> newEntries) {
        if (mDataSet == null) {
            mDataSet = newEntries;
        } else {
            mDataSet.clear();
            mDataSet.addAll(newEntries);
        }
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewAuthor;
        private final TextView textViewPublished;
        private final TextView textViewUpdated;
        private final TextView textViewSummary;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
            textViewAuthor = (TextView) v.findViewById(R.id.textViewAuthor);
            textViewPublished = (TextView) v.findViewById(R.id.textViewPublished);
            textViewUpdated = (TextView) v.findViewById(R.id.textViewUpdated);
            textViewSummary = (TextView) v.findViewById(R.id.textViewSummary);
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet List<Entry> containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(List<Entry> dataSet) {
        mDataSet = dataSet;
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager).
     * Get element from dataset at this position and replace the contents of the view
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Title
        viewHolder.getTextViewTitle().setText(mDataSet.get(position).getTitle());

        // Author
        viewHolder.getTextViewAuthor().setText("Author: " + mDataSet.get(position).getAuthor());

        // Published Date
        Date publishedDate = mDataSet.get(position).getPublished();
        if (publishedDate != null) {
            LocalDateTime publishedLocalDate = LocalDateTimeConverter
                    .convertToLocalDateTimeViaInstant(publishedDate);
            viewHolder.getTextViewPublished().setText("Published: " +
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(publishedLocalDate));
        }

        // Updated Date
        Date updatedDate = mDataSet.get(position).getUpdated();
        if (updatedDate != null) {
            LocalDateTime updatedLocalDate = LocalDateTimeConverter
                    .convertToLocalDateTimeViaInstant(updatedDate);
            viewHolder.getTextViewUpdated().setText("Updated: " +
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(updatedLocalDate));
        }

        // Summary
        String summary = mDataSet.get(position).getSummary();
        if (summary != null)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                viewHolder.getTextViewSummary().setText(
                        Html.fromHtml(summary, Html.FROM_HTML_MODE_LEGACY));
            } else {
                viewHolder.getTextViewSummary().setText(
                        Html.fromHtml(summary));
            }
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
