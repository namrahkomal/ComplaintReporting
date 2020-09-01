package com.example.complaintreporting;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbname="mydb";
    public static final String TABLE_NAME1="USER_COMPLAINTS";
    public static final String TABLE_NAME2="registeruser";
    public static final String COL_1="ID";
    public static final String COL_2="username";
    public static final String COL_3="password";
    private static final int version=1;

    public MyHelper(Context context){
        super(context,dbname,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //complaints table
        String sql="CREATE TABLE USER_COMPLAINTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Description TEXT)";
        db.execSQL(sql);
        //login table
        db.execSQL("CREATE TABLE registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);

    }
    public long addUser(String user, String password){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", user );
        contentValues.put("password", password );
        long res=db.insert("registeruser", null, contentValues);
        db.close();
        return res;
    }
    public boolean checkUser(String username, String password){
        String[] columns={ COL_1 };
        SQLiteDatabase db= getReadableDatabase();
        String selection = COL_2 + "=?"+"and"+COL_3+"=?";
        String[] selectionargs={ username, password};

        Cursor cursor = db.query(TABLE_NAME2,// Selecting Table
                new String[]{COL_1},//Selecting columns want to query
                COL_2 + "=?" + " AND " + COL_3 + "=?",
                new String[]{username,password},//Where clause
                null, null, null);

        //Cursor cursor = new db.query(TABLE_NAME,columns,selection,selectionargs,null,null,null);
        int count=cursor.getCount();
        cursor.close();
        db.close();

        if(count>0){
            return true;
        }
        else{
            return false;
        }
        //Cursor cursor = new db.query(TABLE_NAME, columns,selection,selectionargs,null,null,null);


    }
    /*private void insertData(String name, String descrition, double price, SQLiteDatabase database){

        values.put("NAME",name);
        values.put("DESCRIPTION",descrition);
        values.put("PRICE",price);
        database.insert("PRODUCTS", null,values);
    }*/
    public boolean addData(String title, String description){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("Title",title);
        values.put("Description",description);
        long result= database.insert("USER_COMPLAINTS",null,values);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getlistContents(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * from "+TABLE_NAME1;
        Cursor data =db.rawQuery(query,null);

        return data;
    }


}
