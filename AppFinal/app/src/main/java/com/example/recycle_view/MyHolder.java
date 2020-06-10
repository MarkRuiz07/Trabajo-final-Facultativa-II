package com.example.recycle_view;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
    ImageView mImageView;
    TextView mNombres,  mIdentificacion;

    ItemClickListener itemClickListener;
    View.OnCreateContextMenuListener onCreateContextMenuListener;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mImageView = itemView.findViewById(R.id.imgEmplead);
        this.mNombres = itemView.findViewById(R.id.TextViewNombreCompleto);
        this.mIdentificacion = itemView.findViewById(R.id.TextViewIdentificacion);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View v){
        this.itemClickListener.onItemClickListener(v, getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener=ic;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.onCreateContextMenuListener.onCreateContextMenu(menu, v, menuInfo);
    }
    public void setOnCreateContextMenuListener(View.OnCreateContextMenuListener c)
    {
        this.onCreateContextMenuListener = c;
    }
}
