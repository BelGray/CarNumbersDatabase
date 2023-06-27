package com.example.carnumbersdatabase.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.model.Fines;

import java.util.ArrayList;
import java.util.List;

public class FineAdapter extends RecyclerView.Adapter<FineAdapter.ViewHolder> {

    private List<Fines> finesList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fine_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Fines fine = finesList.get(position);

        holder.fineText.setText(fine.getFineText());
        holder.fineAmount.setText(fine.getFineAmount());
        holder.fineDate.setText(fine.getFineDate());
        if (fine.isFineIsPayed()){
            holder.confirmFineIsPayedButton.setVisibility(View.GONE);
            holder.confirmFineIsPayedTextView.setVisibility(View.VISIBLE);
        } else {
            holder.confirmFineIsPayedButton.setVisibility(View.VISIBLE);
            holder.confirmFineIsPayedTextView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return finesList.size();
    }

    public void setFinesList(List<Fines> finesList) {
        this.finesList = finesList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView fineText, fineAmount, fineDate, confirmFineIsPayedTextView;
        final Button confirmFineIsPayedButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fineText = itemView.findViewById(R.id.fineText);
            fineAmount = itemView.findViewById(R.id.fineAmount);
            fineDate = itemView.findViewById(R.id.fineDate);
            confirmFineIsPayedButton = itemView.findViewById(R.id.confirmFineIsPayedButton);
            confirmFineIsPayedTextView = itemView.findViewById(R.id.fineIsPayedText);


            confirmFineIsPayedButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onClick(finesList.get(position));
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onClick(Fines fines);
    }

    public void setOnConfirmFineIsPayedButtonClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
