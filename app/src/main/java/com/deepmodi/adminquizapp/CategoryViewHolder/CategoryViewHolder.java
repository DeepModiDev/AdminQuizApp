package com.deepmodi.adminquizapp.CategoryViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.deepmodi.adminquizapp.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView categoryName;
    public TextView categoryDesc;
    public TextView categoryId;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        categoryName = itemView.findViewById(R.id.container_category_name);
        categoryDesc = itemView.findViewById(R.id.container_category_desc);
        categoryId = itemView.findViewById(R.id.container_category_id);

    }
}
