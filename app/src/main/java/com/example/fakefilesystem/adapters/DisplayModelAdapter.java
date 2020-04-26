package com.example.fakefilesystem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fakefilesystem.R;
import com.example.fakefilesystem.models.DisplayModel;

import java.util.ArrayList;

public class DisplayModelAdapter extends RecyclerView.Adapter<DisplayModelAdapter.DisplayModelViewHolder> {
    private ArrayList<DisplayModel> data;
    private final String [] availableFileExtensions = {"docx", "pdf", "xml"};

    public static class DisplayModelViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public DisplayModelViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image);
            mTextView = itemView.findViewById(R.id.item_name);
        }
    }

    public DisplayModelAdapter(ArrayList<DisplayModel> data) {
        this.data = data;
    }

    @Override
    public DisplayModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        DisplayModelViewHolder evh = new DisplayModelViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(DisplayModelViewHolder holder, int position) {
        DisplayModel currentItem = data.get(position);
        holder.mImageView.setImageResource(getImageByType(currentItem));
        holder.mTextView.setText(currentItem.getName());
    }

    private int getImageByType(DisplayModel model) {
        switch (model.getType()){
            case "FILE":
                if (model.getName().split(".")[1].equals("xml")){
                    return R.drawable.xml;
                }
                if (model.getName().split(".")[1].equals("pdf")){
                    return R.drawable.pdf;
                }
                if (model.getName().split(".")[1].equals("docx")){
                    return R.drawable.docx;
                }
                return R.drawable.file;
            default:
                return R.drawable.file;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
