package com.example.fakefilesystem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fakefilesystem.R;
import com.example.fakefilesystem.models.DisplayModel;

import java.util.List;

import static com.example.fakefilesystem.utils.StringConstants.EXT_DOCX;
import static com.example.fakefilesystem.utils.StringConstants.EXT_PDF;
import static com.example.fakefilesystem.utils.StringConstants.EXT_XML;
import static com.example.fakefilesystem.utils.StringConstants.TYPE_FILE;
import static com.example.fakefilesystem.utils.StringConstants.TYPE_FOLDER;

public class DisplayModelAdapter extends RecyclerView.Adapter<DisplayModelAdapter.DisplayModelViewHolder> {
    private List<DisplayModel> data;
    private OnNoteListener onNoteListener;

    public static class DisplayModelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView mTextView;
        public OnNoteListener onNoteListener;

        public DisplayModelViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image);
            mTextView = itemView.findViewById(R.id.item_name);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public DisplayModelAdapter(List<DisplayModel> data, OnNoteListener onNoteListener) {
        this.data = data;
        this.onNoteListener = onNoteListener;
    }

    @Override
    public DisplayModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        DisplayModelViewHolder evh = new DisplayModelViewHolder(v, onNoteListener );
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
            case TYPE_FOLDER:
                return R.drawable.folder;
            case TYPE_FILE:
                if (model.getName().contains(EXT_XML)){
                    return R.drawable.xml;
                }
                if (model.getName().contains(EXT_PDF)){
                    return R.drawable.pdf;
                }
                if (model.getName().contains(EXT_DOCX)){
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

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
