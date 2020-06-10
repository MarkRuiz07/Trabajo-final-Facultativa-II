package com.example.recycle_view;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public interface ItemClickListener {
    void onItemClickListener(View v , int position);
}

