package com.deepmodi.adminquizapp;


import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.deepmodi.adminquizapp.Model.Category;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    Button btn_add_category,btn_select_image;
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
        btn_select_image = findViewById(R.id.btn_select_image);


        btn_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        if(!edit_category_id.getText().toString().isEmpty() &&
                                !editText_category_name.getText().toString().isEmpty() && !editText_category_desc.getText().toString().isEmpty())
                        {
                            uploadData(edit_category_id.getText().toString(), editText_category_name.getText().toString(), editText_category_desc.getText().toString());
                        }
                        else{
                            Toast.makeText(AddItemActivity.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                        }
            }
        });

        btn_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionForStorageAndCamera();
            }
        });
    }

    private void uploadData(String CategoryId,String CategoryName,String CategoryDesc)
    {
        Category category = new Category(CategoryId,CategoryName,CategoryDesc);
        reference.child(CategoryId).setValue(category);
        Toast.makeText(this, "Item added.", Toast.LENGTH_SHORT).show();
    }

    private void permissionForStorageAndCamera()
    {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if(report.areAllPermissionsGranted())
                            {
                                Toast.makeText(AddItemActivity.this, "Thank you for granting all permissions.", Toast.LENGTH_SHORT).show();
                            }

                            if(report.isAnyPermissionPermanentlyDenied())
                            {
                                showSettingDialog();
                            }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(AddItemActivity.this, "Error in granting.", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void showSettingDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
        builder.setTitle("Request for permissions");
        builder.setMessage("All permissions are needed.");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    openSettings();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                        .show();
    }

    private void openSettings()
    {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",getPackageName(),null);
        intent.setData(uri);
        startActivityForResult(intent,101);
    }
}
