package com.example.privlok;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddPasswords extends AppCompatActivity {
    private TextInputLayout textInputTitle;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputAccount;
    private TextInputLayout textInputUsername;
    //EditText textInputTitle,textInputPassword,textInputAccount,textInputUsername;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passwords);
        MyDataHelper mydb=new MyDataHelper(this);
        textInputTitle = findViewById(R.id.text_input_title);
        textInputPassword = findViewById(R.id.text_input_password);
        textInputAccount = findViewById(R.id.text_input_account);
        textInputUsername = findViewById(R.id.text_input_username);
        save = findViewById(R.id.btnInsert);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),textInputTitle.getEditText().getText().toString(),Toast.LENGTH_LONG).show();

                if(!validateTitle() && !validatePassword() && !validateAccount() && !validateUsername()) {

                    long check= mydb.insertRecord(textInputTitle.getEditText().getText().toString(),textInputUsername.getEditText().getText().toString(),textInputAccount.getEditText().getText().toString(),textInputPassword.getEditText().getText().toString());
                    if(check == -1)
                    {
                        //displayMessage("Error", "Record not inserted");
                        Toast.makeText(getApplicationContext(),"Record not inserted",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        //displayMessage("Info","Record is inserted Successfully");
                        Toast.makeText(getApplicationContext(),"Record is inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                }
            else{
                Toast.makeText(getApplicationContext(),"Please record insert first",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    /*public void displayMessage(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddPasswords.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/

    private boolean validateTitle() {
        String Title = textInputTitle.getEditText().getText().toString().trim();

        if(Title.isEmpty()){
            textInputTitle.setError("Field can't be empty");
            return true;
        }
        else {
            textInputTitle.setError(null);
            return false;
        }
    }

    private boolean validatePassword() {
        String Password = textInputPassword.getEditText().getText().toString().trim();

        if(Password.isEmpty()){
            textInputPassword.setError("Field can't be empty");
            return true;
        } else {
            textInputPassword.setError(null);
            return false;
        }
    }

    private boolean validateAccount() {
        String Account = textInputAccount.getEditText().getText().toString().trim();

        if(Account.length() > 16){
            textInputAccount.setError("Invalid Account Number");
            return true;
        } else {
            textInputAccount.setError(null);
            return false;
        }
    }

    private boolean validateUsername() {
        String Username = textInputUsername.getEditText().getText().toString().trim();

        if(Username.length() > 20){
            textInputUsername.setError("Username is too long");
            return true;
        } else {
            textInputUsername.setError(null);
            return false;
        }
    }


    /*public  void saveInput(View v) {
        if(!validateTitle() | !validatePassword() | !validateAccount() | !validateUsername()) {
            return;
        }
        /*String input = "Title: " + textInputTitle.getEditText().getText().toString();
        input += "\n";
        Toast.makeText(getApplicationContext(),"Data is saved Successfully",Toast.LENGTH_LONG).show();
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_and_secure_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }*/

}