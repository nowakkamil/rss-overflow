package com.example.android.recyclerview;

import com.example.android.common.logger.Log;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final TextView textViewLink;
        private final TextView textViewAuthor;
        private final TextView textViewPubDate;
        private final TextView textViewCategory;

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
            textViewDescription = (TextView) v.findViewById(R.id.textViewDescription);
            textViewLink = (TextView) v.findViewById(R.id.textViewLink);
            textViewAuthor = (TextView) v.findViewById(R.id.textViewAuthor);
            textViewPubDate = (TextView) v.findViewById(R.id.textViewPubDate);
            textViewCategory = (TextView) v.findViewById(R.id.textViewCategory);
        }

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public TextView getTextViewDescription() {
            return textViewDescription;
        }

        public TextView getTextViewLink() {
            return textViewLink;
        }

        public TextView getTextViewAuthor() {
            return textViewAuthor;
        }

        public TextView getTextViewPubDate() {
            return textViewPubDate;
        }

        public TextView getTextViewCategory() {
            return textViewCategory;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextViewTitle().setText(mDataSet[position]);

        // Uncomment below to dynamically update rss feed entry row
//        viewHolder.getTextViewDescription().setText(mDataSet[position]);
//        viewHolder.getTextViewLink().setText(mDataSet[position]);
//        viewHolder.getTextViewAuthor().setText(mDataSet[position]);
//        viewHolder.getTextViewPubDate().setText(mDataSet[position]);
//        viewHolder.getTextViewCategory().setText(mDataSet[position]);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
