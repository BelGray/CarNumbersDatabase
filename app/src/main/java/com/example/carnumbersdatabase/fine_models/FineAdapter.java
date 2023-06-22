package com.example.carnumbersdatabase.fine_models;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.database.Fines;

import java.util.ArrayList;
import java.util.List;

public class FineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Fines> finesList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return finesList.size();
    }

    static class FineCardViewHolder extends RecyclerView.ViewHolder {
        TextView fineText, fineAmount, fineDate, fineIsPayedText;
        Button confirmFineIsPayed;
        public FineCardViewHolder(@NonNull View itemView) {
            super(itemView);
            fineText = itemView.findViewById(R.id.fineText);
            fineAmount = itemView.findViewById(R.id.fineAmount);
            fineDate = itemView.findViewById(R.id.fineDate);
            fineIsPayedText = itemView.findViewById(R.id.fineIsPayedText);
        }
    }



}
