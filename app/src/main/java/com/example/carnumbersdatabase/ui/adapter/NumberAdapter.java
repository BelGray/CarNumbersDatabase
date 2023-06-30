package com.example.carnumbersdatabase.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.model.Fines;
import com.example.carnumbersdatabase.model.Numbers;

import java.util.ArrayList;
import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder>{

    private List<Numbers> numbersList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnDeleteButtonClickListener onDeleteButtonClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.numbers_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Numbers numbers = numbersList.get(position);

        holder.numbers.setText(numbers.getPersonCarNumbers());
        holder.region.setText(String.valueOf(numbers.getPersonRegionCode()));


    }

    @Override
    public int getItemCount() {
        return numbersList.size();
    }

    public void setNumbersList(List<Numbers> numbersList){
        this.numbersList = numbersList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView numbers, region;
        final ImageButton deleteImageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numbers = itemView.findViewById(R.id.numbers);
            region = itemView.findViewById(R.id.region);
            deleteImageButton = itemView.findViewById(R.id.deleteNumbersImageButton);

            deleteImageButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (onDeleteButtonClickListener != null && position != RecyclerView.NO_POSITION) {
                    onDeleteButtonClickListener.onClick(numbersList.get(position));
                }
            });

            itemView.setOnClickListener(v -> {

                int position = getLayoutPosition();
                if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onClick(numbersList.get(position));
                }

            });

        }
    }

    public interface OnItemClickListener {
        void onClick(Numbers numbers);
    }

    public interface OnDeleteButtonClickListener {
        void onClick(Numbers numbers);
    }

    public void setOnDeleteButtonClickListener(NumberAdapter.OnDeleteButtonClickListener onDeleteButtonClickListener) {
        this.onDeleteButtonClickListener = onDeleteButtonClickListener;
    }

    public void setOnItemClickListener(NumberAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



}
