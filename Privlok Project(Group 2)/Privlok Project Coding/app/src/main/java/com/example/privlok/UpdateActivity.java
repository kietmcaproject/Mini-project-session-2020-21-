package com.example.privlok;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateActivity extends AppCompatActivity {
    private TextInputLayout textUpdateTitle;
    private TextInputLayout textUpdatePassword;
    private TextInputLayout textUpdateAccount;
    private TextInputLayout textUpdateUsername;

    Button update_button, delete_button;
    String id, title, username, account, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        textUpdateTitle = findViewById(R.id.text_update_title);
        textUpdatePassword = findViewById(R.id.text_update_password);
        textUpdateAccount = findViewById(R.id.text_update_account);
        textUpdateUsername = findViewById(R.id.text_update_username);
        update_button = findViewById(R.id.btnUpdate);
        delete_button = findViewById(R.id.btnDelete);


        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDataHelper myDB=new MyDataHelper(UpdateActivity.this);
                title = textUpdateTitle.getEditText().getText().toString().trim();
                username = textUpdateUsername.getEditText().getText().toString().trim();
                account = textUpdateAccount.getEditText().getText().toString().trim();
                password = textUpdatePassword.getEditText().getText().toString().trim();
                myDB.updateData(id, title, username, account, password);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("username") && getIntent().hasExtra("account") &&
                getIntent().hasExtra("password")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            username = getIntent().getStringExtra("username");
            account = getIntent().getStringExtra("account");
            password = getIntent().getStringExtra("password");
            //Setting Intent Data
            textUpdateTitle.getEditText().setText(title);
            textUpdateUsername.getEditText().setText(username);
            textUpdateAccount.getEditText().setText(account);
            textUpdatePassword.getEditText().setText(password);
            //Log.d("nirmit", title+" "+username+" "+account+" "+password);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDataHelper myDB = new MyDataHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
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