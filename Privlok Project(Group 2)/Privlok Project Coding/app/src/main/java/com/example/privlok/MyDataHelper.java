package com.example.privlok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataHelper  extends SQLiteOpenHelper {
    Context context;
    final static public String dbname="MyData";
    final static public int version=5;
    final static public String table_name="Privlok";
    final static public String col_1="id";
    final static public String col_2="title_name";
    final static public String col_3="user_name";
    final static public String col_4="account_number";
    final static public String col_5="password";
    final static public String query="create table "+table_name+" ( "+col_1+" integer PRIMARY KEY AUTOINCREMENT, "+col_2+" text, "+col_3+" text, "+col_4+" text, "+col_5+" text )";

    public MyDataHelper(Context context) {
        super(context, dbname, null , version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }
    public long insertRecord(String title_name, String user_name, String account_number, String password)
    {
        ContentValues cv = new ContentValues();
        cv.put(col_2,title_name);
        cv.put(col_3,user_name);
        cv.put(col_4,account_number);
        cv.put(col_5,password);
        SQLiteDatabase db = this.getWritableDatabase();
        long check = db.insert(table_name, null,cv);
        return check;
    }

    Cursor readAllData(){
        String query = " Select "+col_2+" , "+col_3+" , "+col_4+" , "+col_5+" From " + table_name;
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor= null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }


    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name, col_1+"=?", new String[]{""+row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_name);
    }

    void updateData(String row_id, String title_name, String user_name, String account_number, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(col_1,row_id);
        cv.put(col_2,title_name);
        cv.put(col_3,user_name);
        cv.put(col_4,account_number);
        cv.put(col_5,password);


        long result = db.update(table_name, cv, col_1+"=?", new String[]{""+row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
