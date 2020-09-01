package com.example.complaintreporting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Currency;

public class DB_handler extends AppCompatActivity {

    private Button btn_add_complaint;
    private EditText insert_title;
    private EditText insert_description;
    Button btnCapture;
    ImageView displayImage;
    MyHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b_handler);

        helper = new MyHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        btn_add_complaint=findViewById(R.id.btn_add_complaint);
        insert_title=findViewById(R.id.insert_title);
        insert_description=findViewById(R.id.insert_description);

        btn_add_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle=insert_title.getText().toString();
                String newDescription=insert_description.getText().toString();
                if (insert_title.length()!=0 && insert_description.length()!=0){
                    AddData(newTitle,newDescription);
                    insert_title.setText("");
                    insert_description.setText("");
                    Intent i=new Intent(DB_handler.this,viewComplaints.class);
                    startActivity(i);
                    finish();
                }
                else {
                    toastMessage("Incomplete Complaint");
                }
            }
        });
        /*Cursor cursor = database.rawQuery("SELECT NAME,PRICE FROM PRODUCTS", new String[]{});

        if (cursor != null) {
            cursor.moveToFirst();
        }
        StringBuilder builder = new StringBuilder();
        do {
            String name = cursor.getString(0);
            double price = cursor.getDouble(1);

            builder.append("Name: " + name + " Price: " + price+ "\n");
        } while (cursor.moveToNext());

       // TextView tv = findViewById(R.id.textData);
        //tv.setText(builder.toString());
    */
        btnCapture = findViewById(R.id.btn_capture);
        displayImage= findViewById(R.id.ImageCapture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0);
                btnCapture.setVisibility(View.GONE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap= (Bitmap)data.getExtras().get("data");
        displayImage.setImageBitmap(bitmap);
    }

    public void AddData(String newtitle,String newdescription){
        boolean insertData=helper.addData(newtitle,newdescription);

        if(insertData){
            toastMessage("Data Added Successfully");
        }
        else {
            toastMessage("Failed to Enter Data");
        }
    }

    public void toastMessage(String message){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast.makeText(getApplicationContext(), "Item: " + id + " Selected", Toast.LENGTH_LONG).show();

        return true;
    }*/

}