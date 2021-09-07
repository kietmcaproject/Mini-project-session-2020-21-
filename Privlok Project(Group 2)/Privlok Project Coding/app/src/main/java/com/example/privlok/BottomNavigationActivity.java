package com.example.privlok;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BottomNavigationActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;


    MyDataHelper myDb;
    ArrayList<String> array_title,array_username,array_account,array_password;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        recyclerView= findViewById(R.id.recyclerview);
        fab= findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddPasswords.class);
                startActivity(i);
            }
        });
        myDb= new MyDataHelper(BottomNavigationActivity.this);
        array_title = new ArrayList<>();
        array_username = new ArrayList<>();
        array_account = new ArrayList<>();
        array_password = new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(BottomNavigationActivity.this,this, array_title, array_username, array_account,array_password);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BottomNavigationActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDb.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data found", Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){
                array_title.add(cursor.getString(0));
                array_username.add(cursor.getString(1));
                array_account.add(cursor.getString(2));
                array_password.add(cursor.getString(3));
            }

        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDataHelper myDB = new MyDataHelper(BottomNavigationActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(BottomNavigationActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}