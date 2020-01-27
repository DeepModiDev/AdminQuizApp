package com.deepmodi.adminquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deepmodi.adminquizapp.Model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddItemActivity extends AppCompatActivity {

    Button btn_add_category;
    EditText edit_category_id,editText_category_name,editText_category_desc;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //firebase init
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Category");

        btn_add_category = findViewById(R.id.btn_upload);
        edit_category_id = findViewById(R.id.edit_category_id);
        editText_category_desc = findViewById(R.id.edit_category_desc);
        editText_category_name = findViewById(R.id.edit_category_name);

        btn_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    uploadData(edit_category_id.getText().toString(),editText_category_name.getText().toString(),editText_category_desc.getText().toString());
            }
        });
    }

    private void uploadData(String CategoryId,String CategoryName,String CategoryDesc)
    {
        Category category = new Category(CategoryId,CategoryName,CategoryDesc);
        reference.child(CategoryId).setValue(category);
        Toast.makeText(this, "Item added.", Toast.LENGTH_SHORT).show();
    }
}
